package com.wildadventures.mscomments.services;

import com.wildadventures.mscomments.controllers.dto.CommentDto;
import com.wildadventures.mscomments.controllers.dto.WriterDTO;
import com.wildadventures.mscomments.dao.entity.Comment;
import com.wildadventures.mscomments.dao.repository.CommentsRepository;
import com.wildadventures.mscomments.exceptions.TechnicalException;
import com.wildadventures.mscomments.proxies.AdventureProxy;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Value("${wildadventures.comments.keycloak.serverUrl}")
    private String keycloakServerUrl;

    @Value("${wildadventures.comments.keycloak.realm}")
    private String keycloakRealm;

    @Value("${wildadventures.comments.keycloak.clientId}")
    private String keycloakClientId;

    @Value("${wildadventures.comments.keycloak.clientSecret}")
    private String keycloakClientSecret;

    @Value("${wildadventures.comments.keycloak.username}")
    private String keycloakUsername;

    @Value("${wildadventures.comments.keycloak.password}")
    private String keycloakPassword;


    @Autowired
    private CommentsRepository commentRepo;

    @Autowired
    private AdventureProxy adventureProxy;

    private UsersResource getKeycloakUserResource(){
        return Keycloak.getInstance(
                keycloakServerUrl,
                keycloakRealm,
                keycloakUsername,
                keycloakPassword,
                keycloakClientId,
                keycloakClientSecret)
            .realm(keycloakRealm)
            .users();
    }

    private void checkWriteCommentContext(Comment comment) throws TechnicalException {
        if (comment.getId() == null) {
            throw new TechnicalException("Seems that there's no user nor adventure");
        }

        // On vérifie que l'utilisateur existe
        try {
            getKeycloakUserResource().get(comment.getId().getUserId());
        } catch (NotFoundException exception) {
            throw new TechnicalException("Seems that user doesn't exist.");
        }

        // On vérifie que l'aventure existe
        if (adventureProxy.getOneAdventure(comment.getId().getAdventureId()) == null) {
            throw new TechnicalException("Seems that adventure doesn't exist.");
        }
    }

    @Transactional
    public Comment writeNewComment(Comment comment) throws TechnicalException {
        checkWriteCommentContext(comment);
        // On détermine le numéro du commentaire
        final int maxCommentId = commentRepo.getMaxCommentIdByAdventureAndUser(
                comment.getId().getAdventureId(),
                comment.getId().getUserId()
        ).orElse(0);
        comment.getId().setCommentId(maxCommentId + 1);

        // Enregistrement du commentaire
        try {
            return commentRepo.save(comment);
        } catch (Exception e){
            throw new TechnicalException("Cannot write new comment : a similar reference of the comment exists", e);
        }
    }

    @Transactional
    public void removeComment(Comment comment) throws TechnicalException {
        if (commentRepo.existsById(comment.getId())){
            commentRepo.delete(comment);
            return;
        }
        throw new TechnicalException("Comment doesn't exists");
    }

    public void loadWriters(List<CommentDto> comments) {
        if (CollectionUtils.isEmpty(comments)) {
            return;
        }
        UsersResource usersResource = getKeycloakUserResource();

        // Pour éviter de faire plusieurs fois le meme appel, on identifie les userId distincts
        // et on y mappe les userName correspondants
        Map<String, String> userIdUserNameMap =
            comments
            .stream().map(comment -> comment.getId().getUserId()).collect(Collectors.toSet())
            .stream().collect(Collectors.toMap(userId -> userId, userId -> {
                try {
                    return usersResource.get(userId).toRepresentation().getUsername();
                } catch (NotFoundException e) {
                    return "Unknown user";
                }
            }));

        comments.stream().forEach(comment -> {
            comment.setWriter(new WriterDTO());
            comment.getWriter().setUserId(comment.getId().getUserId());
            comment.getWriter().setUserName(userIdUserNameMap.get(comment.getId().getUserId()));
        });
    }
}

package com.wildadventures.mscomments.controllers;

import com.wildadventures.mscomments.controllers.dto.CommentDto;
import com.wildadventures.mscomments.controllers.dto.CommentIdDTO;
import com.wildadventures.mscomments.controllers.dto.ErrorDto;
import com.wildadventures.mscomments.controllers.dto.mappers.CommentMapper;
import com.wildadventures.mscomments.dao.entity.Comment;
import com.wildadventures.mscomments.dao.repository.CommentsRepository;
import com.wildadventures.mscomments.exceptions.TechnicalException;
import com.wildadventures.mscomments.services.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommentController {

    @Autowired
    private CommentsRepository commentRepo;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentMapper commentMapper;

    /**
     * Format ResponseEntity from a list of comments
     *
     * @param comments
     * @return
     */
    private ResponseEntity<List<CommentDto>> getCommentsResponseEntity(List<Comment> comments){
        // S'il on ne trouve rien, on retourne un 204 : no content
        if (comments == null || comments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // Sinon, on retourne un status 200 avec la liste des commentaires correspondants
            List<CommentDto> toReturn = comments.stream()
                    .map(commentMapper::fromEntityToDto)
                    .collect(Collectors.toList());
            commentService.loadWriters(toReturn);
            return new ResponseEntity<List<CommentDto>>(toReturn, HttpStatus.OK);
        }
    }

    /**
     * Returns the comments written by a user
     *
     * @param userId
     * @return
     */
    @GetMapping("/users/{userId}/comments")
    @ApiOperation(value = "Returns the comments written by a user")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Server retrieved user's comments"),
        @ApiResponse(code = 204, message = "Server didn't retrieve any user's comments", response = Void.class)
    })
    public ResponseEntity<List<CommentDto>> getCommentsByUserId(@PathVariable("userId") String userId){
        return getCommentsResponseEntity(commentRepo.findByIdUserId(userId));
    }

    /**
     * Returns the comments written for an adventure
     *
     * @param adventureId
     * @return
     */
    @GetMapping("/adventures/{adventureId}/comments")
    @ApiOperation(value = "Returns the comments written for an adventure")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Server retrieved adventure's comments"),
        @ApiResponse(code = 204, message = "Server didn't retrieve any adventure's comments", response = Void.class)
    })
    public ResponseEntity<List<CommentDto>> getCommentsByAdventureId(@PathVariable("adventureId") Integer adventureId){
        return getCommentsResponseEntity(commentRepo.findByIdAdventureId(adventureId));
    }

    /**
     * Attach comment to the given adventure and save it
     *
     * @param adventureId
     * @param comment
     * @return
     */
    @PostMapping(value = "/adventures/{adventureId}/comments")
    @ApiOperation(value = "Attach comment to the given adventure and save it")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Comment has been created"),
            @ApiResponse(code = 409, message = "Technical error during comment creation", response = ErrorDto.class)
    })
    @PreAuthorize("hasAnyRole('user')")
    public ResponseEntity<CommentDto> writeAdventureComment(@PathVariable("adventureId") Integer adventureId, @Valid @RequestBody CommentDto comment, Authentication authentication) throws TechnicalException {
        comment.setId(new CommentIdDTO());

        comment.getId().setAdventureId(adventureId);
        comment.getId().setUserId(authentication.getName());
        comment.setDate(LocalDateTime.now());
        return new ResponseEntity<CommentDto>(
                commentMapper.fromEntityToDto(commentService.writeNewComment(commentMapper.fromDtoToEntity(comment))),
                HttpStatus.CREATED
        );
    }


    /**
     *
     * @param adventureId
     * @param commentId
     * @return
     * @throws TechnicalException
     */
    @DeleteMapping("/adventures/{adventureId}/comments/{commentId}")
    @ApiOperation(value = "Delete comment")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Comment has been deleted"),
            @ApiResponse(code = 409, message = "Comment doesn't exist", response = ErrorDto.class)
    })
    @PreAuthorize("hasAnyRole('user')")
    public ResponseEntity<Void> deleteComment(@PathVariable("adventureId") Integer adventureId, @PathVariable("commentId") Integer commentId, Authentication authentication) throws TechnicalException {
        CommentDto comment = new CommentDto();
        CommentIdDTO commentIdentifier = new CommentIdDTO();

        commentIdentifier.setUserId(authentication.getName());
        commentIdentifier.setAdventureId(adventureId);
        commentIdentifier.setCommentId(commentId);
        comment.setId(commentIdentifier);
        commentService.removeComment(commentMapper.fromDtoToEntity(comment));
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

}

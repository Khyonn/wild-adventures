package com.wildadventures.mscomments.dao.repository;

import com.wildadventures.mscomments.dao.entity.Comment;
import com.wildadventures.mscomments.dao.entity.pk.CommentPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentsRepository extends JpaRepository<Comment, CommentPK> {
    List<Comment> findByIdUserId(String userId);

    List<Comment> findByIdAdventureId(Integer adventureId);

    @Query("SELECT max(c.id.commentId) FROM Comment c WHERE c.id.adventureId = :adventureId AND c.id.userId = :userId")
    Optional<Integer> getMaxCommentIdByAdventureAndUser(@Param("adventureId") Integer adventureId, @Param("userId") String userId);
}

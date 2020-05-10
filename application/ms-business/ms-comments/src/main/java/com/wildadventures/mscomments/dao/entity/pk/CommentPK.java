package com.wildadventures.mscomments.dao.entity.pk;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class CommentPK implements Serializable {
    @Column(name = "user_id")
    private String userId;

    @Column(name = "adventure_id")
    private Integer adventureId;

    @Column(name = "comment_id")
    private Integer commentId;

    public CommentPK(){}

    public CommentPK(String userId, Integer adventureId, Integer commentId) {
        this.userId = userId;
        this.adventureId = adventureId;
        this.commentId = commentId;
    }
}

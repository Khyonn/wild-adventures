package com.wildadventures.mscomments.dao.entity;

import com.wildadventures.mscomments.dao.entity.pk.CommentPK;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @EmbeddedId
    private CommentPK id;

    @Column(name = "date")
    @NotNull
    private LocalDateTime dateTime;

    @Column(name="content")
    @NotNull
    private String content;

}

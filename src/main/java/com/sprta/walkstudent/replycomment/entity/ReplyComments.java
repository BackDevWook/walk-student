package com.sprta.walkstudent.replycomment.entity;

import com.sprta.walkstudent.BaseEntity;
import com.sprta.walkstudent.comment.entity.Comments;
import com.sprta.walkstudent.schedule.entity.Schedules;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reply_comments")
public class ReplyComments extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long writerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedules schedules;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comments comments;


    public ReplyComments(String content, Long writerId, Schedules schedule, Comments commnet) {
        this.content = content;
        this.writerId = writerId;
        this.schedules = schedule;
        this.comments = commnet;
    }

    public void update(String content) {
        this.content = content;
    }
}

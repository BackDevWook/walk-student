package com.sprta.walkstudent.schedule.entity;

import com.sprta.walkstudent.BaseEntity;
import com.sprta.walkstudent.comment.entity.Comments;
import com.sprta.walkstudent.replycomment.entity.ReplyComments;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "schedules")
public class Schedules extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 25)
    private String title;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(nullable = false)
    private Long writerId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schedules")
    private List<Comments> comments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schedules")
    private List<ReplyComments> replyComments;

    public Schedules(String title, String content, Long writerId) {
        this.title = title;
        this.content = content;
        this.writerId = writerId;
    }


}

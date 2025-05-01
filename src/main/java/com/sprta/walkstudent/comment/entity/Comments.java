package com.sprta.walkstudent.comment.entity;

import com.sprta.walkstudent.BaseEntity;
import com.sprta.walkstudent.replycomment.entity.ReplyComments;
import com.sprta.walkstudent.schedule.entity.Schedules;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
public class Comments extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long writerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Schedules schedules;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comments")
    private List<ReplyComments> replyComments;

    public Comments(String content, Long writerId, Schedules schedule) {
        this.content = content;
        this.writerId = writerId;
        this.schedules = schedule;
    }

    public void update(String content) {
        this.content = content;
    }

}

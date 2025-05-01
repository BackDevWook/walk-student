package com.sprta.walkstudent.comment.entity;

import com.sprta.walkstudent.BaseEntity;
import com.sprta.walkstudent.schedule.entity.Schedules;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public Comments(String content, Schedules schedule) {
        this.content = content;
        this.schedules = schedule;
    }

}

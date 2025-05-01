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

    /**
     * PK
     * 제목
     * 내용
     * 유저 id
     * 댓글 참조
     * 대댓글 참조
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long writerId;

    @Column(nullable = false, length = 25)
    private String title;

    @Column(nullable = false, length = 500)
    private String content;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schedules")
    private List<Comments> comments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schedules")
    private List<ReplyComments> replyComments;

    // 요청을 위한 생성자
    public Schedules(Long writerId, String title, String content) {
        this.writerId = writerId;
        this.title = title;
        this.content = content;
    }

    // 수정용 생성자
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }


}

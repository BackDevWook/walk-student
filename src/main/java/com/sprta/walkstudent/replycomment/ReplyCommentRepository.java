package com.sprta.walkstudent.replycomment;

import com.sprta.walkstudent.replycomment.entity.ReplyComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyCommentRepository extends JpaRepository<ReplyComments, Long> {
}

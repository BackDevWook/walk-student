package com.sprta.walkstudent.replycomment;

import com.sprta.walkstudent.comment.CommentRepository;
import com.sprta.walkstudent.comment.entity.Comments;
import com.sprta.walkstudent.replycomment.dto.request.ReplyCommentDeleteDto;
import com.sprta.walkstudent.replycomment.dto.request.ReplyCommentRequestDto;
import com.sprta.walkstudent.replycomment.dto.request.ReplyCommentUpdateDto;
import com.sprta.walkstudent.replycomment.dto.response.ReplyCommentResponseDto;
import com.sprta.walkstudent.replycomment.entity.ReplyComments;
import com.sprta.walkstudent.schedule.ScheduleRepository;
import com.sprta.walkstudent.schedule.entity.Schedules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyCommentService {

    private final ReplyCommentRepository replyCommentRepository;
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    // 1. 대댓글 작성
    public ReplyCommentResponseDto createReply(Long scheduleId, Long commentId, ReplyCommentRequestDto dto) {

        Schedules schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new RuntimeException("Schedule not found"));
        Comments comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));

        ReplyComments reply = replyCommentRepository.save(new ReplyComments(
                dto.getContent(),
                dto.getWriterId(),
                schedule,
                comment
        ));

        return new ReplyCommentResponseDto(
                reply.getId(),
                reply.getWriterId(),
                reply.getContent()
        );
    }

    // 2. 특정 댓글의 대댓글 조회
    @Transactional(readOnly = true)
    public List<ReplyCommentResponseDto> getAllreply(Long commentId) {

        Comments comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));

        return comment.getReplyComments().stream()
                .map(reply -> new ReplyCommentResponseDto(
                        reply.getId(),
                        reply.getWriterId(),
                        reply.getContent()
                )).toList();
    }

    // 3. 대댓글 수정
    public ReplyCommentResponseDto updateReply(Long replyId, ReplyCommentUpdateDto dto) {

        ReplyComments reply = replyCommentRepository.findById(replyId).orElseThrow(() -> new RuntimeException("Reply not found"));

        if(!reply.getWriterId().equals(dto.getWriterId())) {
            throw new RuntimeException("작성자가 일치하지 않습니다.");
        }

        reply.update(dto.getContent());

        replyCommentRepository.save(reply);

        return new ReplyCommentResponseDto(
                reply.getId(),
                reply.getWriterId(),
                reply.getContent()
        );
    }

    // 4. 대댓글 삭제
    public void deleteReply(Long replyId, ReplyCommentDeleteDto dto) {

        ReplyComments reply = replyCommentRepository.findById(replyId).orElseThrow(() -> new RuntimeException("Reply not found"));

        if(!reply.getWriterId().equals(dto.getWriterId())) {
            throw new RuntimeException("작성자가 일치하지 않습니다.");
        }

        replyCommentRepository.delete(reply);
    }

}

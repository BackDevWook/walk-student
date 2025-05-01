package com.sprta.walkstudent.comment;

import com.sprta.walkstudent.comment.dto.request.CommentDeleteDto;
import com.sprta.walkstudent.comment.dto.request.CommentRequestDto;
import com.sprta.walkstudent.comment.dto.request.CommentUpdateDto;
import com.sprta.walkstudent.comment.dto.response.CommentResponseDto;
import com.sprta.walkstudent.comment.entity.Comments;
import com.sprta.walkstudent.schedule.ScheduleRepository;
import com.sprta.walkstudent.schedule.entity.Schedules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    // 1. 댓글 작성
    public CommentResponseDto createComment(Long scheduleId, CommentRequestDto dto) {

        Schedules schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new RuntimeException("Schedule not found"));

        Comments comment = commentRepository.save(new Comments(
                dto.getContent(),
                dto.getWriterId(),
                schedule
        ));

        return new CommentResponseDto(
                comment.getId(),
                comment.getWriterId(),
                comment.getContent()
        );
    }

    // 2. 특성 게시물의 댓글 조회
    @Transactional(readOnly = true)
    public List<CommentResponseDto> getComment(Long scheduleId) {

        Schedules schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new RuntimeException("Schedule not found"));

        return schedule.getComments().stream().map(comment -> new CommentResponseDto(
                comment.getId(),
                comment.getWriterId(),
                comment.getContent()
        )).toList();
    }

    // 3. 댓글 수정
    public CommentResponseDto updateComment(Long commentId, CommentUpdateDto dto) {

        Comments comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));

        comment.update(dto.getContent());
        commentRepository.save(comment);

        return new CommentResponseDto(
                comment.getId(),
                comment.getWriterId(),
                comment.getContent()
        );

    }

    // 4. 댓글 삭제
    public void deleteComment(Long commentId, CommentDeleteDto dto) {

        Comments comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));

        if(!comment.getWriterId().equals(dto.getWriterId())) {
            throw new RuntimeException("작성자 id가 일치하지 않습니다.");
        }

        commentRepository.delete(comment);
    }
}

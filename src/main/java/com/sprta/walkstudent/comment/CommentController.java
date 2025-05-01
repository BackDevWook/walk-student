package com.sprta.walkstudent.comment;

import com.sprta.walkstudent.comment.dto.request.CommentDeleteDto;
import com.sprta.walkstudent.comment.dto.request.CommentRequestDto;
import com.sprta.walkstudent.comment.dto.request.CommentUpdateDto;
import com.sprta.walkstudent.comment.dto.response.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 생성
     *
     * @param scheduleId
     * @param dto
     * @return 생성 정보 반환
     */
    @PostMapping("/{scheduleId}")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long scheduleId, @RequestBody CommentRequestDto dto) {
        return new ResponseEntity<>(commentService.createComment(scheduleId, dto), HttpStatus.CREATED);
    }

    /**
     * 특정 게시물 댓글 조회
     *
     * @param scheduleId
     * @return 댓글들 정보 반환
     */
    @GetMapping("/{scheduleId}")
    public ResponseEntity<List<CommentResponseDto>> getComment(@PathVariable Long scheduleId) {
        return new ResponseEntity<>(commentService.getComment(scheduleId), HttpStatus.OK);
    }

    /**
     * 댓글 수정
     *
     * @param commentId
     * @param dto
     * @return 수정된 내역 반환
     */
    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateDto dto) {
        return new ResponseEntity<>(commentService.updateComment(commentId, dto), HttpStatus.OK);
    }

    /**
     * 댓글 삭제
     *
     * @param commentId
     * @param dto
     * @return 204 no content
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, @RequestBody CommentDeleteDto dto) {
        commentService.deleteComment(commentId, dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

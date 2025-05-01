package com.sprta.walkstudent.comment;

import com.sprta.walkstudent.comment.dto.request.CommentDeleteDto;
import com.sprta.walkstudent.comment.dto.request.CommentRequestDto;
import com.sprta.walkstudent.comment.dto.request.CommentUpdateDto;
import com.sprta.walkstudent.comment.dto.response.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    // 1. 댓글 생성
    @PostMapping("/{scheduleId}")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long scheduleId,  @RequestBody CommentRequestDto dto) {
        return new ResponseEntity<>(commentService.createComment(scheduleId, dto),HttpStatus.CREATED);
    }

    // 2. 댓글 조회
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> getComment(@PathVariable Long commentId) {
        return new ResponseEntity<>(commentService.getComment(commentId),HttpStatus.OK);
    }

    // 3. 댓글 수정
    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateDto dto) {
        return new ResponseEntity<>(commentService.updateComment(commentId, dto), HttpStatus.OK);
    }

    // 4. 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, @RequestBody CommentDeleteDto dto) {
        commentService.deleteComment(commentId, dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

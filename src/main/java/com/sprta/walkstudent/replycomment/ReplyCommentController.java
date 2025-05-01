package com.sprta.walkstudent.replycomment;

import com.sprta.walkstudent.replycomment.dto.request.ReplyCommentDeleteDto;
import com.sprta.walkstudent.replycomment.dto.request.ReplyCommentRequestDto;
import com.sprta.walkstudent.replycomment.dto.request.ReplyCommentUpdateDto;
import com.sprta.walkstudent.replycomment.dto.response.ReplyCommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reply-comments")
@RequiredArgsConstructor
public class ReplyCommentController {

    private final ReplyCommentService replyCommentService;

    /**
     * 1. 대댓글 작성
     *
     * @param dto
     * @param scheduleId
     * @param commentId
     * @return 대댓글 작성 내역 반환
     */
    @PostMapping("/{scheduleId}/{commentId}")
    public ResponseEntity<ReplyCommentResponseDto> createReply(@RequestBody ReplyCommentRequestDto dto,
                                                               @PathVariable Long scheduleId,
                                                               @PathVariable Long commentId) {

        return new ResponseEntity<>(replyCommentService.createReply(scheduleId, commentId, dto), HttpStatus.CREATED);
    }

    /**
     * 2. 특정 댓글의 대댓글 조회
     *
     * @param commentId
     * @return 대댓글 정보 전체 반환
     */
    @GetMapping("/{commentId}")
    public ResponseEntity<List<ReplyCommentResponseDto>> getAllReply(@PathVariable Long commentId) {
        return new ResponseEntity<>(replyCommentService.getAllreply(commentId), HttpStatus.OK);
    }

    /**
     * 3. 대댓글 수정
     *
     * @param dto
     * @param replyId
     * @return 수정된 내역 반환
     */
    @PatchMapping("/{replyId}")
    public ResponseEntity<ReplyCommentResponseDto> updateReply(@RequestBody ReplyCommentUpdateDto dto,
                                                               @PathVariable Long replyId) {
        return new ResponseEntity<>(replyCommentService.updateReply(replyId, dto), HttpStatus.OK);
    }

    /**
     * 4. 대댓글 삭제
     *
     * @param replyId
     * @param dto
     * @return 204 no content
     */
    @DeleteMapping("/{replyId}")
    public ResponseEntity<Void> deleteReply(@PathVariable Long replyId,
                                            @RequestBody ReplyCommentDeleteDto dto) {
        replyCommentService.deleteReply(replyId, dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

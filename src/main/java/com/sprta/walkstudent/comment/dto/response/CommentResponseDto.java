package com.sprta.walkstudent.comment.dto.response;

import com.sprta.walkstudent.replycomment.dto.response.ReplyCommentResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class CommentResponseDto {

    /**
     * 댓글 id
     * 작성자 id
     * 내용
     * 대댓글들
     */

    private final Long commentId;

    private final Long writerId;

    private final String content;

    private final List<ReplyCommentResponseDto> replies;


}

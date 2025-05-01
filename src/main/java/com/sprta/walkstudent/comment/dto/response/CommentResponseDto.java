package com.sprta.walkstudent.comment.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentResponseDto {

    /**
     * 댓글 id
     * 작성자 id
     * 내용
     */

    private final Long commentId;

    private final Long writerId;

    private final String content;
}

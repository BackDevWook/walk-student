package com.sprta.walkstudent.replycomment.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReplyCommentResponseDto {

    /**
     * 대댓글 id
     * 작성자 id
     * 내용
     */

    private final Long id;

    private final Long writerId;

    private final String content;
}

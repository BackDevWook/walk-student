package com.sprta.walkstudent.replycomment.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReplyCommentResponseDto {

    private final Long id;

    private final Long writerId;

    private final String content;
}

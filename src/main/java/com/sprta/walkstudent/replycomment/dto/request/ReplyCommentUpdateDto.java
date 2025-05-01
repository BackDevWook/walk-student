package com.sprta.walkstudent.replycomment.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReplyCommentUpdateDto {

    /**
     * 작성자 id (인증)
     * 내용
     */

    @Pattern(regexp = "^[1-9]\\d*$", message = "1 이상의 정수만 입력 가능합니다.")
    private final Long writerId;

    @Size(min = 1, max = 500, message = "게시물은 최소 1글자, 최대 500글자까지 입력 가능합니다.")
    private final String content;

}

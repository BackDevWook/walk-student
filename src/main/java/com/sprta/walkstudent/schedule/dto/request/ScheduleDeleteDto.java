package com.sprta.walkstudent.schedule.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ScheduleDeleteDto {

    /**
     * 작성자 id (인증)
     */

    @Pattern(regexp = "^[1-9]\\d*$", message = "1 이상의 정수만 입력 가능합니다.")
    private final Long writerId;
}

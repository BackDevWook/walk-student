package com.sprta.walkstudent.schedule.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ScheduleResponseDto {

    /**
     * 스케줄 id
     * 작성자 id
     * 제목
     * 내용
     */

    private final Long scheduleId;

    private final Long writerId;

    private final String title;

    private final String content;




}

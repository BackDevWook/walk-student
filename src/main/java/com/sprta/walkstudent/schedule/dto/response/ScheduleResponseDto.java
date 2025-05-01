package com.sprta.walkstudent.schedule.dto.response;

import com.sprta.walkstudent.comment.dto.response.CommentResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ScheduleResponseDto {

    /**
     * 스케줄 id
     * 작성자 id
     * 제목
     * 내용
     * 댓글들
     */

    private final Long scheduleId;

    private final Long writerId;

    private final String title;

    private final String content;

    private final List<CommentResponseDto> comments;





}

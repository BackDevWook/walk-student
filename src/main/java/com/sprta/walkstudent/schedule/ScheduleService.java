package com.sprta.walkstudent.schedule;

import com.sprta.walkstudent.comment.dto.response.CommentResponseDto;
import com.sprta.walkstudent.replycomment.dto.response.ReplyCommentResponseDto;
import com.sprta.walkstudent.schedule.dto.request.ScheduleRequestDto;
import com.sprta.walkstudent.schedule.dto.request.ScheduleUpdateDto;
import com.sprta.walkstudent.schedule.dto.response.ScheduleResponseDto;
import com.sprta.walkstudent.schedule.entity.Schedules;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // 1. 일정 생성
    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto) {

        Schedules schedule = scheduleRepository.save(
                new Schedules(
                        dto.getWriterId(),
                        dto.getTitle(),
                        dto.getContent()));

        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getWriterId(),
                schedule.getTitle(),
                schedule.getContent(),
                null // NPE 에러 방지
                );
    }

    // 2. 전체 일정 조회
    @Transactional(readOnly = true)
    public Page<ScheduleResponseDto> getAllSchedules(Pageable pageable) {
        Pageable page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("updatedAt").descending());
        Page<Schedules> schedules = scheduleRepository.findAll(page);

        return schedules.map(schedule -> new ScheduleResponseDto(
                schedule.getId(),
                schedule.getWriterId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getComments().stream().map(comment -> new CommentResponseDto(
                        comment.getId(),
                        comment.getWriterId(),
                        comment.getContent(),
                        comment.getReplyComments().stream().map(reply -> new ReplyCommentResponseDto(
                                reply.getId(),
                                reply.getWriterId(),
                                reply.getContent()
                        )).toList()
                )).toList())
        );
    }

    // 3. 단일 일정 조회
    @Transactional(readOnly = true)
    public ScheduleResponseDto getSchedule(Long id) {

        Schedules schedule = scheduleRepository.findById(id).orElseThrow(() -> new RuntimeException("Schedule not found"));

        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getWriterId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getComments().stream().map(comment -> new CommentResponseDto(
                        comment.getId(),
                        comment.getWriterId(),
                        comment.getContent(),
                        comment.getReplyComments().stream().map(reply -> new ReplyCommentResponseDto(
                                reply.getId(),
                                reply.getWriterId(),
                                reply.getContent()
                        )).toList()
                )).toList()
        );
    }

    // 4. 일정 수정
    public ScheduleResponseDto updateSchedule(Long scheduleId, ScheduleUpdateDto dto) {

        Schedules schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new RuntimeException("Schedule not found"));

        // 사용자 검증
        if(!schedule.getWriterId().equals(dto.getWriterId())) {
            throw new RuntimeException("Schedule id mismatch");
        }

        schedule.update(dto.getTitle(), dto.getContent());
        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getWriterId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getComments().stream().map(comment -> new CommentResponseDto(
                        comment.getId(),
                        comment.getWriterId(),
                        comment.getContent(),
                        comment.getReplyComments().stream().map(reply -> new ReplyCommentResponseDto(
                                reply.getId(),
                                reply.getWriterId(),
                                reply.getContent()
                        )).toList()
                )).toList()
        );

    }

    // 5. 일정 삭제
    public void deleteSchedule(Long scheduleId, Long writerId) {

        Schedules schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new RuntimeException("Schedule not found"));

        // 사용자 검증
        if(!schedule.getWriterId().equals(writerId)) {
            throw new RuntimeException("Schedule id mismatch");
        }

        scheduleRepository.deleteById(scheduleId);

    }

}

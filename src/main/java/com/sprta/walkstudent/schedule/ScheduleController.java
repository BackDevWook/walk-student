package com.sprta.walkstudent.schedule;

import com.sprta.walkstudent.schedule.dto.request.ScheduleDeleteDto;
import com.sprta.walkstudent.schedule.dto.request.ScheduleRequestDto;
import com.sprta.walkstudent.schedule.dto.request.ScheduleUpdateDto;
import com.sprta.walkstudent.schedule.dto.response.ScheduleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 1. 일정 생성 POST
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto) {

        return new ResponseEntity<>(scheduleService.createSchedule(dto), HttpStatus.CREATED);

    }

    // 2. 전체 일정 및 단일 일정 조회 GET
    @GetMapping
    public ResponseEntity<Page<ScheduleResponseDto>> getAllSchedules(Pageable pageable) {
        return new ResponseEntity<>(scheduleService.getAllSchedules(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.getSchedule(id), HttpStatus.OK);
    }

    // 3. 일정 수정 PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleUpdateDto dto) {
        return new ResponseEntity<>(scheduleService.updateSchedule(id, dto), HttpStatus.OK);
    }

    // 4. 일정 삭제 DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @RequestBody ScheduleDeleteDto dto) {
        scheduleService.deleteSchedule(id, dto.getWriterId());
        return ResponseEntity.noContent().build();
    }


}

package com.sprta.walkstudent.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 1. 일정 생성 POST

    // 2. 일정 조회 GET

    // 3. 일정 수정 PATCH

    // 4. 일정 삭제 DELETE


}

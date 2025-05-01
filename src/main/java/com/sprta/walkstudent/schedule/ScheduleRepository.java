package com.sprta.walkstudent.schedule;

import com.sprta.walkstudent.schedule.entity.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedules, Long> {

}

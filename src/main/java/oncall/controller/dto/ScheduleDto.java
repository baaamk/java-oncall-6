package oncall.controller.dto;

import oncall.model.domain.vo.Day;
import oncall.model.domain.vo.Month;

import java.util.List;

public record ScheduleDto(Month month, List<Day> days, List<String> workers) {
    public static ScheduleDto of(Month month, List<Day> days, List<String> workers) {
        return new ScheduleDto(month, days, workers);
    }

}

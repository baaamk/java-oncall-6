package oncall.controller.dto;

import oncall.model.domain.vo.Month;
import oncall.model.domain.vo.Week;

public record MonthMapper(Month month, Week week) {
}

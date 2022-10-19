package com.sgeller.tool.rental.services;

import com.sgeller.tool.rental.domain.DateType;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class DefaultDateTypeService implements DateTypeService {

    private static final Map<DayOfWeek, DateType> DAY_TYPE_MAP = Map.of(
            DayOfWeek.SUNDAY, DateType.WEEKEND,
            DayOfWeek.MONDAY, DateType.WEEKDAY,
            DayOfWeek.TUESDAY, DateType.WEEKDAY,
            DayOfWeek.WEDNESDAY, DateType.WEEKDAY,
            DayOfWeek.THURSDAY, DateType.WEEKDAY,
            DayOfWeek.FRIDAY, DateType.WEEKDAY,
            DayOfWeek.SATURDAY, DateType.WEEKEND
    );

    private final HolidayService holidayService;

    public DefaultDateTypeService(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @Override
    public DateType getDateType(LocalDate localDate) {
        if (localDate == null) {
            throw new IllegalArgumentException("localDate must be present.");
        }

        return Optional.ofNullable(localDate.getYear())
                .map(this.holidayService::getHolidayDates)
                .orElseGet(ArrayList::new).stream()
                .filter(holidayDate -> holidayDate.isEqual(localDate))
                .findFirst()
                .map(__ -> DateType.HOLIDAY)
                .orElse(DAY_TYPE_MAP.get(localDate.getDayOfWeek()));
    }
}

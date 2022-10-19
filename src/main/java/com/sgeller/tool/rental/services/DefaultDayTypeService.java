package com.sgeller.tool.rental.services;

import com.sgeller.tool.rental.domain.DayType;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class DefaultDayTypeService implements DayTypeService {

    private static final Map<DayOfWeek, DayType> DAY_TYPE_MAP = Map.of(
            DayOfWeek.SUNDAY, DayType.WEEKEND,
            DayOfWeek.MONDAY, DayType.WEEKDAY,
            DayOfWeek.TUESDAY, DayType.WEEKDAY,
            DayOfWeek.WEDNESDAY, DayType.WEEKDAY,
            DayOfWeek.THURSDAY, DayType.WEEKDAY,
            DayOfWeek.FRIDAY, DayType.WEEKDAY,
            DayOfWeek.SATURDAY, DayType.WEEKEND
    );

    private final HolidayService holidayService;

    public DefaultDayTypeService(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @Override
    public DayType getDayType(LocalDate localDate) {
        if (localDate == null) {
            throw new IllegalArgumentException("localDate must be present.");
        }

        return Optional.ofNullable(localDate.getYear())
                .map(this.holidayService::getHolidayDates)
                .orElseGet(ArrayList::new).stream()
                .filter(holidayDate -> holidayDate.isEqual(localDate))
                .findFirst()
                .map(__ -> DayType.HOLIDAY)
                .orElse(DAY_TYPE_MAP.get(localDate.getDayOfWeek()));
    }
}

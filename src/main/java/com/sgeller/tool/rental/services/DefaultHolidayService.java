package com.sgeller.tool.rental.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DefaultHolidayService implements HolidayService {

    @Override
    public Collection<LocalDate> getHolidayDates(Integer year) {
        if (year == null) {
            throw new IllegalArgumentException("year must be present.");
        }

        return Stream.of(getLaborDayDate(year),
                        getIndependenceDayDate(year))
                .collect(Collectors.toList());
    }

    private LocalDate getLaborDayDate(Integer year) {
        return LocalDate.of(year, 9, 1)
                .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
    }

    private LocalDate getIndependenceDayDate(Integer year) {
        return this.adjustWeekendHoliday(LocalDate.of(year, 7, 4));
    }

    private LocalDate adjustWeekendHoliday(LocalDate localDate) {

        if (localDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return localDate.minusDays(1);
        }

        if (localDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return localDate.plusDays(1);
        }

        return localDate;
    }
}

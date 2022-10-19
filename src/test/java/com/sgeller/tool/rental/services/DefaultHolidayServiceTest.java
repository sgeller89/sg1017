package com.sgeller.tool.rental.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class DefaultHolidayServiceTest {

    private HolidayService holidayService;

    @BeforeEach
    public void setup() {
        this.holidayService = new DefaultHolidayService();
    }

    @Test
    public void testGetHolidaysOnWeekdays() {
        Collection<LocalDate> holidayDates = this.holidayService.getHolidayDates(2022);

        List<LocalDate> expectedHolidayDates = List.of(
                LocalDate.of(2022, 9, 5),
                LocalDate.of(2022, 7, 4)
        );

        Assertions.assertEquals(expectedHolidayDates, holidayDates);
    }

    @Test
    public void testGetHolidaysOnSunday() {
        Collection<LocalDate> holidayDates = this.holidayService.getHolidayDates(2021);

        List<LocalDate> expectedHolidayDates = List.of(
                LocalDate.of(2021, 9, 6),
                LocalDate.of(2021, 7, 5)
        );

        Assertions.assertEquals(expectedHolidayDates, holidayDates);
    }

    @Test
    public void testGetHolidaysOnSaturday() {
        Collection<LocalDate> holidayDates = this.holidayService.getHolidayDates(2020);

        List<LocalDate> expectedHolidayDates = List.of(
                LocalDate.of(2020, 9, 7),
                LocalDate.of(2020, 7, 3)
        );

        Assertions.assertEquals(expectedHolidayDates, holidayDates);
    }

    @Test
    public void testGetHolidaysNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> this.holidayService.getHolidayDates(null));

        Assertions.assertEquals("year must be present.", exception.getMessage());
    }
}

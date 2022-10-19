package com.sgeller.tool.rental.services;

import com.sgeller.tool.rental.domain.DayType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

public class DefaultDayTypeServiceTest {

    private DayTypeService dayTypeService;
    private HolidayService holidayService;

    @BeforeEach
    public void setup() {
        this.holidayService = Mockito.mock(HolidayService.class);
        Mockito.doReturn(List.of(LocalDate.of(2022, 9, 5)))
                .when(this.holidayService)
                .getHolidayDates(2022);

        this.dayTypeService = new DefaultDayTypeService(this.holidayService);
    }

    @Test
    public void testGetDayTypeSunday() {
        DayType dayType = this.dayTypeService.getDayType(LocalDate.of(2022, 10, 16));
        Assertions.assertEquals(DayType.WEEKEND, dayType);
    }

    @Test
    public void testGetDayTypeMonday() {
        DayType dayType = this.dayTypeService.getDayType(LocalDate.of(2022, 10, 17));
        Assertions.assertEquals(DayType.WEEKDAY, dayType);
    }

    @Test
    public void testGetDayTypeTuesday() {
        DayType dayType = this.dayTypeService.getDayType(LocalDate.of(2022, 10, 18));
        Assertions.assertEquals(DayType.WEEKDAY, dayType);
    }

    @Test
    public void testGetDayTypeWednesday() {
        DayType dayType = this.dayTypeService.getDayType(LocalDate.of(2022, 10, 19));
        Assertions.assertEquals(DayType.WEEKDAY, dayType);
    }

    @Test
    public void testGetDayTypeThursday() {
        DayType dayType = this.dayTypeService.getDayType(LocalDate.of(2022, 10, 20));
        Assertions.assertEquals(DayType.WEEKDAY, dayType);
    }

    @Test
    public void testGetDayTypeFriday() {
        DayType dayType = this.dayTypeService.getDayType(LocalDate.of(2022, 10, 21));
        Assertions.assertEquals(DayType.WEEKDAY, dayType);
    }

    @Test
    public void testGetDayTypeSaturday() {
        DayType dayType = this.dayTypeService.getDayType(LocalDate.of(2022, 10, 22));
        Assertions.assertEquals(DayType.WEEKEND, dayType);
    }

    @Test
    public void testGetDayTypeHoliday() {
        DayType dayType = this.dayTypeService.getDayType(LocalDate.of(2022, 9, 5));
        Assertions.assertEquals(DayType.HOLIDAY, dayType);
    }

    @Test
    public void testGetDayTypeNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> this.dayTypeService.getDayType(null));

        Assertions.assertEquals("localDate must be present.", exception.getMessage());
    }
}

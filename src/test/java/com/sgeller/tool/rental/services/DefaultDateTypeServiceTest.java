package com.sgeller.tool.rental.services;

import com.sgeller.tool.rental.domain.DateType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

public class DefaultDateTypeServiceTest {

    private DateTypeService dateTypeService;
    private HolidayService holidayService;

    @BeforeEach
    public void setup() {
        this.holidayService = Mockito.mock(HolidayService.class);
        Mockito.doReturn(List.of(LocalDate.of(2022, 9, 5)))
                .when(this.holidayService)
                .getHolidayDates(2022);

        this.dateTypeService = new DefaultDateTypeService(this.holidayService);
    }

    @Test
    public void testGetDateTypeSunday() {
        DateType dateType = this.dateTypeService.getDateType(LocalDate.of(2022, 10, 16));
        Assertions.assertEquals(DateType.WEEKEND, dateType);
    }

    @Test
    public void testGetDateTypeMonday() {
        DateType dateType = this.dateTypeService.getDateType(LocalDate.of(2022, 10, 17));
        Assertions.assertEquals(DateType.WEEKDAY, dateType);
    }

    @Test
    public void testGetDateTypeTuesday() {
        DateType dateType = this.dateTypeService.getDateType(LocalDate.of(2022, 10, 18));
        Assertions.assertEquals(DateType.WEEKDAY, dateType);
    }

    @Test
    public void testGetDateTypeWednesday() {
        DateType dateType = this.dateTypeService.getDateType(LocalDate.of(2022, 10, 19));
        Assertions.assertEquals(DateType.WEEKDAY, dateType);
    }

    @Test
    public void testGetDateTypeThursday() {
        DateType dateType = this.dateTypeService.getDateType(LocalDate.of(2022, 10, 20));
        Assertions.assertEquals(DateType.WEEKDAY, dateType);
    }

    @Test
    public void testGetDateTypeFriday() {
        DateType dateType = this.dateTypeService.getDateType(LocalDate.of(2022, 10, 21));
        Assertions.assertEquals(DateType.WEEKDAY, dateType);
    }

    @Test
    public void testGetDateTypeSaturday() {
        DateType dateType = this.dateTypeService.getDateType(LocalDate.of(2022, 10, 22));
        Assertions.assertEquals(DateType.WEEKEND, dateType);
    }

    @Test
    public void testGetDateTypeHoliday() {
        DateType dateType = this.dateTypeService.getDateType(LocalDate.of(2022, 9, 5));
        Assertions.assertEquals(DateType.HOLIDAY, dateType);
    }

    @Test
    public void testGetDateTypeNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> this.dateTypeService.getDateType(null));

        Assertions.assertEquals("localDate must be present.", exception.getMessage());
    }
}

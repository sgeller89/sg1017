package com.sgeller.tool.rental.services;

import java.time.LocalDate;
import java.util.Collection;

public interface HolidayService {

    Collection<LocalDate> getHolidayDates(Integer year);
}

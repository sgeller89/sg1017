package com.sgeller.tool.rental.services;

import com.sgeller.tool.rental.domain.Checkout;
import com.sgeller.tool.rental.domain.RentalDaysInfo;
import com.sgeller.tool.rental.domain.Tool;

public interface RentalDaysService {

    RentalDaysInfo calculateChargeDays(Checkout checkout, Tool tool);
}

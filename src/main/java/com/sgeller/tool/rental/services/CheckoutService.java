package com.sgeller.tool.rental.services;

import com.sgeller.tool.rental.domain.Checkout;
import com.sgeller.tool.rental.domain.RentalAgreement;

public interface CheckoutService {

    RentalAgreement createRentalAgreement(Checkout checkout);
}

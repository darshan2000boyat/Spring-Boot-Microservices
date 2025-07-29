package com.ecom.customer.dto;

import com.ecom.customer.customer.Address;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}

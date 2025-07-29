package com.ecom.customer.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
public class CustomerNotFoundException extends RuntimeException {
    private final String message;
}

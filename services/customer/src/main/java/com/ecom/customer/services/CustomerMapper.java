package com.ecom.customer.services;

import com.ecom.customer.customer.Customer;
import com.ecom.customer.dto.CustomerRequest;
import com.ecom.customer.dto.CustomerResponse;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest customerRequest) {
        return Customer.builder()
                .email(customerRequest.email())
                .id(customerRequest.id())
                .address(customerRequest.address())
                .lastname(customerRequest.lastname())
                .firstname(customerRequest.firstname())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}

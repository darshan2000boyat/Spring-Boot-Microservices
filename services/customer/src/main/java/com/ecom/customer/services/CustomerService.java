package com.ecom.customer.services;

import com.ecom.customer.customer.Customer;
import com.ecom.customer.dto.CustomerRequest;
import com.ecom.customer.dto.CustomerResponse;
import com.ecom.customer.exceptions.CustomerNotFoundException;
import com.ecom.customer.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;
    public String createCustomer(CustomerRequest customerRequest) {
        var customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        return customer.getId();
    }

    public void updateCustomer(@Valid CustomerRequest customerRequest) {
        var customer = customerRepository.findById(
                        customerRequest.id()
                )
                .orElseThrow(
                        () -> new CustomerNotFoundException(
                                String.format("Customer not found with Id: %s", customerRequest.id())
                        )
                );

        mergeCustomer(customer, customerRequest);
        customerRepository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest customerRequest) {

        if(StringUtils.isNotBlank(customerRequest.firstname())){
            customer.setFirstname(customerRequest.firstname());
        } if(StringUtils.isNotBlank(customerRequest.lastname())){
            customer.setLastname(customerRequest.lastname());
        } if(StringUtils.isNotBlank(customerRequest.email())){
            customer.setEmail(customerRequest.email());
        } if(customerRequest.address() != null){
            customer.setAddress(customerRequest.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean checkIfCustomerExist(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    public CustomerResponse findCustomerById(String customerId) {
        return customerRepository.findById(customerId).map(customerMapper::fromCustomer).orElseThrow(
                () -> new CustomerNotFoundException(String.format("No customer found with Id:: %s", customerId))
        );
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}

package com.mon.reactivespring.com.mon.reative.service;

import com.mon.reactivespring.com.mon.reative.dao.CustomerDao;
import com.mon.reactivespring.com.mon.reative.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;

    public List<Customer> getAllCustomers(){
        long startTime = System.currentTimeMillis();
        List<Customer> customerList = customerDao.getAllCustomers();
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime));
        return customerDao.getAllCustomers();
    }

    public Flux<Customer> getAllCustomersStream(){
        long startTime = System.currentTimeMillis();
        Flux<Customer> customerFlux = customerDao.getAllCustomersStream();
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime));
        return customerFlux;
    }
}

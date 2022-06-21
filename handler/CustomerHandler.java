package com.mon.reactivespring.com.mon.reative.handler;

import com.mon.reactivespring.com.mon.reative.dao.CustomerDao;
import com.mon.reactivespring.com.mon.reative.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> loadCustomers(ServerRequest serverRequest){
        Flux<Customer> customerFlux = customerDao.getAllCustomerList();
        return ServerResponse.ok().body(customerFlux, Customer.class);
    }


    public Mono<ServerResponse> findCustomer(ServerRequest serverRequest){
        int customerId = Integer.parseInt(serverRequest.pathVariable("input"));
//        Mono<Customer> customerMono = customerDao.getAllCustomerList()
//                .filter(dto -> dto.getId() == customerId).take(1).single();
        Mono<Customer> customerMono = customerDao.getAllCustomerList().filter(dto -> dto.getId() == customerId).next();
        return ServerResponse.ok().body(customerMono, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest serverRequest){
        Mono<Customer> customerMono = serverRequest.bodyToMono(Customer.class);
        Mono<String> saveResponse = customerMono.map(dto -> dto.getId() + ":" + dto.getName());
        return ServerResponse.ok().body(saveResponse, String.class);
    }
}

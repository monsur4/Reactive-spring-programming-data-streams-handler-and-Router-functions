package com.mon.reactivespring.com.mon.reative.dao;

import com.mon.reactivespring.com.mon.reative.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    private static void sleepExecution(int time){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public List<Customer> getAllCustomers(){
        return IntStream.rangeClosed(1, 50)
                .peek(CustomerDao::sleepExecution)
                .peek(i -> System.out.println("processing count: " + i))
                .mapToObj(i -> new Customer(i, "customer " + i))
                .collect(Collectors.toList());
    }

    public Flux<Customer> getAllCustomersStream(){
        return Flux.range(1, 50)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("processing count in flux: " + i))
                .map(i -> new Customer(i, "customer " + i));
    }

    public Flux<Customer> getAllCustomerList(){
        return Flux.range(1, 50)
                .doOnNext(i -> System.out.println("processing count in flux: " + i))
                .map(i -> new Customer(i, "customer " + i));
    }
}

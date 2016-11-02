package hu.tb.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 2016.11.02..
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


}

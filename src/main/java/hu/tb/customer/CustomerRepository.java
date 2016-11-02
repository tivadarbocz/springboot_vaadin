package hu.tb.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Tivadar Bocz on 2016.11.02..
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);
}

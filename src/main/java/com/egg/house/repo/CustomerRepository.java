package com.egg.house.repo;

import com.egg.house.model.Customer;
import com.sun.xml.internal.bind.v2.model.core.ID;
import com.sun.xml.internal.ws.policy.sourcemodel.ModelNode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>
{
    List<Customer> findByLastName(String lastName);
}

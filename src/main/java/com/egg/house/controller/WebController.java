package com.egg.house.controller;

import com.egg.house.model.Customer;
import com.egg.house.model.EggHouse;
import com.egg.house.model.EggType;
import com.egg.house.repo.CustomerRepository;
import com.egg.house.repo.EggHouseRepository;
import com.egg.house.repo.EggTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Optional;

@RestController
public class WebController {
    @Autowired
    CustomerRepository repository;
    @Autowired
    EggHouseRepository eggHouseRepo;
    @Autowired
    EggTypeRepository eggTypeRepo;

    @RequestMapping("/save")
    public String process(){
        // save a single Customer
        repository.save(new Customer("Jack", "Smith"));

        // save a list of Customers
        repository.saveAll(Arrays.asList(new Customer("Adam", "Johnson"), new Customer("Kim", "Smith"),
                new Customer("David", "Williams"), new Customer("Peter", "Davis")));

        return "Done";
    }


    @RequestMapping("/findall")
    public String findAll(){
        String result = "";

        for(Customer cust : repository.findAll()){
            result += cust.toString() + "<br>";
        }

        return result;
    }

    @RequestMapping("/findbyid")
    public String findById(@RequestParam("id") long id){
        String result = "";
        Customer customer = repository.findById(id).orElse(new Customer("l", "l"));
        result = customer.toString();
        return result;
    }

    @RequestMapping("/findbylastname")
    public String fetchDataByLastName(@RequestParam("lastname") String lastName){
        String result = "";

        for(Customer cust: repository.findByLastName(lastName)){
            result += cust.toString() + "<br>";
        }

        return result;
    }

    @RequestMapping("/readyTableAndStaticData")
    public String readyTableAndStaticData()
    {
        // ready eggHouse  11 - 26
        for(int i = 11 ; i < 27 ; i ++ )
        {
            eggHouseRepo.save(new EggHouse(i, "鸡舍" + i + "号"));
        }

        // ready egg type : fresh , dirty , broken
        eggTypeRepo.save(new EggType(1, "fresh"));
        eggTypeRepo.save(new EggType(2, "dirty"));
        eggTypeRepo.save(new EggType(3, "broken"));

        return "sym SB -ok";
    }
}

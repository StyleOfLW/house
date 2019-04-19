package com.egg.house.repo;

import com.egg.house.model.EggHouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EggHouseRepository extends CrudRepository<EggHouse,Integer>
{
}

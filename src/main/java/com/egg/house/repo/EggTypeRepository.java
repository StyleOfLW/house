package com.egg.house.repo;

import com.egg.house.model.EggType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EggTypeRepository extends CrudRepository<EggType, Integer>
{
}

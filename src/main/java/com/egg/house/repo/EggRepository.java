package com.egg.house.repo;

import com.egg.house.model.Egg;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EggRepository extends CrudRepository<Egg,Long>
{
}

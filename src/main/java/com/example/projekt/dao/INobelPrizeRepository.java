package com.example.projekt.dao;

import com.example.projekt.entities.NobelPrize;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface INobelPrizeRepository extends CrudRepository<NobelPrize,Long> {
    public List<NobelPrize> findAll();
}

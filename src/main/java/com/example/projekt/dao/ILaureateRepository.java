package com.example.projekt.dao;

import com.example.projekt.entities.Laureate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ILaureateRepository extends CrudRepository<Laureate,Long>, JpaRepository<Laureate,Long> {

    Laureate findByGivenName(String name);
}

package com.metrodata.serverapp.repository;

import com.metrodata.serverapp.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
    Optional<Country> findByName(String name);
    Optional<Country> findByNameOrCode(String name, String code);
    Boolean existsByName(String name);


}

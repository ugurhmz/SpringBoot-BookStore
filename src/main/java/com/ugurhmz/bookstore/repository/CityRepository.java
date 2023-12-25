package com.ugurhmz.bookstore.repository;

import com.ugurhmz.bookstore.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}

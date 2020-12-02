package com.codeclan.example.WhiskyTracker.repositories;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WhiskyRepository extends JpaRepository<Whisky, Long> {
    List<Whisky> findByYear(int year);
    List<Whisky> findByAgeAndDistillery(int age, Distillery distillery);
    List<Whisky> findByAge(int age);
    List<Whisky> findByDistillery(Distillery distillery);
}

package com.shravya.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shravya.flightreservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}

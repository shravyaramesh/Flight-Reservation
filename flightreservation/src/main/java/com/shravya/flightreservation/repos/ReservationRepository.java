package com.shravya.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shravya.flightreservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}

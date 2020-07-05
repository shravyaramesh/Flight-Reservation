package com.shravya.flightreservation.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import com.shravya.flightreservation.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
	@Query("from Flight where departure_city=:departureCity and arrival_city=:arrivalCity and date_of_departure=:dateOfDeparture")
	List<Flight> findFlights(@Param("departureCity") String from, @Param("arrivalCity") String to, @Param("dateOfDeparture") Date dateOfDeparture);
}

package com.shravya.flightreservation.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shravya.flightreservation.dto.ReservationUpdateRequest;
import com.shravya.flightreservation.entities.Reservation;
import com.shravya.flightreservation.repos.ReservationRepository;

@RestController
@CrossOrigin //So different applications with different ports to access through can use this api
public class RservationRestController {
	
	@Autowired
	private ReservationRepository reservationRepo;
	
	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id")Long id) {
		Optional<Reservation> optionalReservation = reservationRepo.findById(id);
		Reservation reservation = null;
		if(optionalReservation.isPresent()) {
			reservation = optionalReservation.get();
		}
		return reservation;
	}
	
	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		Optional<Reservation> optionalReservation = reservationRepo.findById(request.getId());
		Reservation updatedReservation = null;
		if(optionalReservation.isPresent()) {
			Reservation reservation = optionalReservation.get();
			reservation.setCheckedIn(request.isCheckedin());
			reservation.setNumberOfBags(request.getNumberOfBags());
			updatedReservation = reservationRepo.save(reservation);
		}
		return updatedReservation;
	}
}

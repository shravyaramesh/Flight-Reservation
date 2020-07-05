package com.shravya.flightreservation.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shravya.flightreservation.dto.ReservationRequest;
import com.shravya.flightreservation.entities.Flight;
import com.shravya.flightreservation.entities.Passenger;
import com.shravya.flightreservation.entities.Reservation;
import com.shravya.flightreservation.repos.FlightRepository;
import com.shravya.flightreservation.repos.PassengerRepository;
import com.shravya.flightreservation.repos.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private PassengerRepository passengerRepo;
	
	@Autowired
	private ReservationRepository reservationRepo;
	
	@Override
	public Reservation bookFlight(ReservationRequest request) {
		
		//Make Payment 
		
		Long flightId = request.getFlightId();
		Optional<Flight> opFlight =flightRepo.findById(flightId);
		Flight flight = null;
		if(opFlight.isPresent())
			flight = opFlight.get();
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerPhone());
		
		Passenger savedPassenger = passengerRepo.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		
		Reservation savedReservation = reservationRepo.save(reservation);
		
		return savedReservation;
	}

}

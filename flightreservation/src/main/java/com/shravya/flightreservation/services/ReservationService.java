package com.shravya.flightreservation.services;

import com.shravya.flightreservation.dto.ReservationRequest;
import com.shravya.flightreservation.entities.Reservation;

public interface ReservationService {
	public Reservation bookFlight(ReservationRequest request);
}

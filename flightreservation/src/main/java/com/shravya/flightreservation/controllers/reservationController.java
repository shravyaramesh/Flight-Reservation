package com.shravya.flightreservation.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shravya.flightreservation.dto.ReservationRequest;
import com.shravya.flightreservation.entities.Flight;
import com.shravya.flightreservation.entities.Reservation;
import com.shravya.flightreservation.repos.FlightRepository;
import com.shravya.flightreservation.services.ReservationService;

@Controller
public class reservationController {

	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private ReservationService reservationService;
	
	@RequestMapping("showCompleteReservation")
	public String showCompletedReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
		Optional<Flight> optionalFlight = flightRepo.findById(flightId);
		if(optionalFlight.isPresent()) {
			Flight flight = optionalFlight.get();
			modelMap.addAttribute("flight", flight);
		}
		return "completeReservation";
	}
	
	@RequestMapping(value = "completeReservation", method=RequestMethod.POST)
	public String completeReservation(ReservationRequest request, ModelMap modelMap) {
		Reservation reservation = reservationService.bookFlight(request);
		modelMap.addAttribute("msg", "Reservation created successfully and the id is:"+reservation.getId());
		
		return "reservationConfirmation";
	}

}

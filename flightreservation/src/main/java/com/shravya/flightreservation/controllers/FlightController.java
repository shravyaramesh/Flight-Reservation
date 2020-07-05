package com.shravya.flightreservation.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shravya.flightreservation.entities.Flight;
import com.shravya.flightreservation.repos.FlightRepository;

@Controller
public class FlightController {
	
	@Autowired
	private FlightRepository flightRepo;
	
	@RequestMapping("findFlights")
	public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to,
		@RequestParam("dateOfDeparture") @DateTimeFormat(pattern = "MM-dd-yyyy") Date dateOfDeparture,
		ModelMap modelMap) {
		
		System.out.println(dateOfDeparture);
		List<Flight> flights = new ArrayList<>();
		flights = flightRepo.findFlights(from, to, dateOfDeparture);
		modelMap.addAttribute("flights", flights);
		return "displayFlights";

	}
	
}

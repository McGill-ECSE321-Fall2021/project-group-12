package ca.mcgill.ecse321.library.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.library.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin(origins = "*")
@RestController
public class MovieRestController {
	
	@Autowired
	private MovieService service;
}

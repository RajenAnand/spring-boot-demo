package com.firstapp.movies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.firstapp.movies.dataaccess.MovieListRepository;
import com.firstapp.movies.model.Movie;
import com.firstapp.movies.model.Profile;

@Controller
@RequestMapping("/movies")
public class MoviesListController {
	
	@Autowired
	private MovieListRepository movieListRepository;
	
	@Value("${spring.activemq.broker-url}")
	private String profileName;
	
	
	@GetMapping("/profile")
	public String getConfig(Model profileModel) {
		
		Profile profile = new Profile();
		profile.setName(profileName);
		
		profileModel.addAttribute("profile", profile);
		
		return "profile";
	}
	
	
	@GetMapping("/list")
	//@RequestMapping(method=RequestMethod.GET, value="/list")
	public String getMoviesList(Model model) {
		
		List<Movie> movieList = movieListRepository.findAll();
		model.addAttribute("movies", movieList);
		
		return "moviesList";
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/list/{actor}")
	public String getMoviesListByActor(@PathVariable("actor") String name, Model model) {
			
		List<Movie> movieList = movieListRepository.findMoviesByActor(name);
		model.addAttribute("movies", movieList);
		
		return "moviesList";
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/list", consumes = "application/json")
	public ResponseEntity<Object> addMovie(@RequestBody Movie movie) {
		movieListRepository.save(movie);
		return ResponseEntity.ok().build();
	}
}

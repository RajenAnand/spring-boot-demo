package com.firstapp.movies.dataaccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstapp.movies.model.Movie;

public interface MovieListRepository extends JpaRepository<Movie, Long>{
	List<Movie> findMoviesByActor(String name);
}

package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class MovieService {

    @Autowired

    MovieRepository movieRepository;

    void addMovie(Movie mov){
        movieRepository.addMovie(mov);
    }

    void addDirector(Director dir){
        movieRepository.addDirector(dir);
    }

    Director getDirectorByName(String director_name){
        return movieRepository.getDirectorByName(director_name);
    }

    Movie getMovieByName(String movie_name){
         return movieRepository.getMovieByName(movie_name);
    }
    ArrayList<String> findAllMovies(){
        return movieRepository.findAllMovies();
    }

    String addPair(String mov, String dir){
        return movieRepository.addPair(mov,dir);
    }

    ArrayList<String> getMoviesByDirectorName(String dir){
        return movieRepository.getMoviesByDirectorName(dir);
    }

    String deleteDirectorByName(String dir){
        return movieRepository.deleteDirectorByName(dir);
    }

    String deleteAllDirectors(){
        return movieRepository.deleteAllDirectors();
    }

}

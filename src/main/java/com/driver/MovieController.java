package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/movies/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie mov){
        movieService.addMovie(mov);
        return new ResponseEntity<>("Movie successfully added",HttpStatus.CREATED);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director dir){
        movieService.addDirector(dir);
        return new ResponseEntity<>("Director successfully added",HttpStatus.CREATED);
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie")String mov_name,
                                                       @RequestParam("director")String dir_name){
        if(movieService.addPair(mov_name,dir_name)!=null){
            return new ResponseEntity<>(movieService.addPair(mov_name,dir_name),HttpStatus.OK);
        }

        return new ResponseEntity<>("Movie or Director does not exist",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/movies/get-movie-by-name/{movie_name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("movie_name") String movie_name){
        System.out.print("Trying to get movie");
        if(movieService.getMovieByName(movie_name)!=null){
            return new ResponseEntity<>(movieService.getMovieByName(movie_name),HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/movies/get-director-by-name/{director_name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("director_name") String director_name){
        if(movieService.getDirectorByName(director_name)!=null){
            return new ResponseEntity<>(movieService.getDirectorByName(director_name),HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        if(movieService.findAllMovies()!=null){
            return new ResponseEntity<>(movieService.findAllMovies(),HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/movies/get-movies-by-director-name/{dir_name}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("dir_name") String dir_name){
        if(movieService.getMoviesByDirectorName(dir_name)!=null){
            return new ResponseEntity<>(movieService.getMoviesByDirectorName(dir_name),HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("director") String dir){

        String del = movieService.deleteDirectorByName(dir);

        if(del!=null) {
            return new ResponseEntity<>(del,HttpStatus.OK);
        }

        return new ResponseEntity<>("Invalid",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        String delAll = movieService.deleteAllDirectors();

        if(delAll!=null) {
            return new ResponseEntity<>(delAll,HttpStatus.OK);
        }

        return new ResponseEntity<>("Invalid",HttpStatus.BAD_REQUEST);
    }

}

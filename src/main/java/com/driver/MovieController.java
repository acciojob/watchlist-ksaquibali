package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MovieController {

    @Autowired(required = false)

    HashMap<String,Movie> movie = new HashMap<>();
    HashMap<String,Director> director = new HashMap<>();
    HashMap<String,String> pair = new HashMap<>();

    @PostMapping("/movies/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie mov){
        movie.put(mov.getName(),mov);

        return new ResponseEntity<String>("Movie sucessfully added", HttpStatus.CREATED);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director dir){
        director.put(dir.getName(),dir);

        return new ResponseEntity<String>("Director sucessfully added", HttpStatus.CREATED);
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie")String mov_name,
                                                       @RequestParam("director")String dir_name){
        pair.put(movie.get(mov_name).getName(), director.get(dir_name).getName());

        return new ResponseEntity<String>("Updated: "+dir_name+" directed "+mov_name, HttpStatus.ACCEPTED);

    }

    @GetMapping("/movies/get-movie-by-name/{name_mov}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name_mov){
        return new ResponseEntity<Movie>(movie.get(name_mov),HttpStatus.OK);
    }

    @GetMapping("/movies/get-director-by-name/{name_dir}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name_dir){
        return new ResponseEntity<Director>(director.get(name_dir),HttpStatus.OK);
    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity<ArrayList> findAllMovies(){

        ArrayList<String> listOfMovies = new ArrayList<>();

        for(Movie movies: movie.values()){
            listOfMovies.add(movies.getName());
        }
        return new ResponseEntity<ArrayList>(listOfMovies,HttpStatus.OK);
    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity<ArrayList> getMoviesByDirectorName(@PathVariable String dir_name){

        ArrayList<String> moviesByDir = new ArrayList<>();

        for(Map.Entry<String,String> e:pair.entrySet()){
            if (e.getValue().equals(dir_name)) {
                moviesByDir.add(e.getKey());
            }
        }

        return new ResponseEntity<ArrayList>(moviesByDir,HttpStatus.OK);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("director") String dir){

        for(Map.Entry<String,String> e:pair.entrySet()){
            if (e.getValue().equals(dir)) {
                movie.remove(e.getKey());
            }
        }

        for(Map.Entry<String,String> e:pair.entrySet()){
            if (e.getValue().equals(dir)) {
                pair.remove(e.getKey());
            }
        }

        director.remove(dir);

        return new ResponseEntity<String>(dir+" and related movies deleted successfully",HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){

        for(Map.Entry<String,String> e: pair.entrySet()){
            movie.remove(e.getValue());
            pair.remove(e.getKey());
        }

        for(Map.Entry<String,Director> e: director.entrySet()){
            director.remove(e.getKey());
        }

        return new ResponseEntity<String>("All directors and their movies deleted successfully.",HttpStatus.ACCEPTED);

    }

}

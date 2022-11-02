package com.driver;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class MovieRepository {

    ArrayList<Movie> movies = new ArrayList<>();
    ArrayList<Director> director = new ArrayList<>();
    HashMap<String,String> pair = new HashMap<>();

    void addMovie(Movie mov){
        movies.add(mov);
    }
    void addDirector(Director dir){
        director.add(dir);
    }

    Movie getMovieByName(String movie_name){
        for(Movie m: movies){
            if(m.getName().equals(movie_name)) return m;
        }
        return null;
    }

    Director getDirectorByName(String director_name){
        for(Director d: director){
            if(d.getName().equals(director_name)) return d;
        }

        return null;
    }

    ArrayList<String> findAllMovies(){

        ArrayList<String> allMovies = new ArrayList<>();

        if(movies!=null){
            for(Movie m:movies){
                allMovies.add(m.getName());
            }

            return allMovies;
        }

        return null;
    }

    String addPair(String mov, String dir){

        boolean movCheck = false;
        boolean dirCheck = false;

        for(Movie m:movies){
            if(m.getName().equals(mov)) movCheck = true;
        }

        for(Director d:director){
            if(d.getName().equals(dir)) dirCheck = true;
        }

        if(movCheck==true && dirCheck==true){
            pair.put(mov,dir);
            return (mov+" and "+dir+" successfully paired");
        }

        return null;
    }

    ArrayList<String> getMoviesByDirectorName(String dir){

        ArrayList<String> moviesByDir = new ArrayList<>();

        if(pair.containsValue(dir)) {
            for(Map.Entry<String,String> e: pair.entrySet()) {
                if (e.getValue().equals(dir)) {
                    moviesByDir.add(e.getKey());
                }
            }

            return moviesByDir;
        }

        return null;
    }

    void deleteMovie(String mov_name){
        for(int i=0;i< movies.size();i++){
            if(movies.get(i).getName().equals(mov_name)){
                movies.remove(movies.get(i));
                break;
            }
        }
    }

    String deleteDirectorByName(String dir){

        if(director.isEmpty()) return null;

        boolean check = false;

        for(int i=0;i< director.size();i++){
            if(director.get(i).getName().equals(dir)){
                director.remove(director.get(i));
                check = true;
                break;
            }
        }

        if(check==false) return null;

        if(!pair.isEmpty() & pair.containsValue(dir)){
            for (Map.Entry<String, String> p : pair.entrySet()) {
                if (p.getValue().equals(dir)) {
                    deleteMovie(p.getKey());
                }
            }

            pair.values().removeIf(value -> (dir.equals(value)));
        }

        return (dir+" and paired movies deleted successfully");

    }

    String deleteAllDirectors() {

        if (director.isEmpty()) return null;

        director.clear();

        for(Map.Entry<String,String> p: pair.entrySet()){
                deleteMovie(p.getKey());
        }

        pair.clear();

        return ("All directors and paired movies deleted successfully");

    }


}

package com.backend.Controllers;


import com.backend.Entity.Movie;
import com.backend.Repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = {"http://localhost:3001", "http://localhost:3000"})
@RestController
@RequestMapping("/movies")
@Slf4j
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping
    public List<Movie> findAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies;
    }

    @GetMapping("/{title}")
    public List<Movie> findAdminByUsername(@PathVariable(value = "title") String title) {
        Optional<Movie> movie = Optional.ofNullable(movieRepository.findBytitle(title));
        List movies = new ArrayList();

        if (movie.isPresent()) {
            movies.add(movie);
        }
        return movies;
    }

    @PostMapping
    public Movie insertMovie(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }


    @DeleteMapping("/remove/{id}")
    public String removeMovie(@PathVariable(value = "id") int Id)
    {
        Optional<Movie> movie = movieRepository.findById(Id);

        if (movie.isPresent()) {
            Movie movie_details = ResponseEntity.ok().body(movie.get()).getBody();
            movieRepository.delete(movie_details);
            return "Deleted";
        } else {
            return "Not Found";
        }
    }


    @PutMapping
    public String removeMovie(@RequestBody Movie new_details) {

        Optional<Movie> movie = movieRepository.findById(new_details.getId());

        Movie movie_details = ResponseEntity.ok().body(movie.get()).getBody();
        movie_details.setTitle(new_details.getTitle());
        movie_details.setPrice(new_details.getPrice());
        movie_details.setShowingtime(new_details.getShowingtime());
        movie_details.setSynopsis(new_details.getSynopsis());
        movie_details.setImageurl(new_details.getImageurl());

        if (movieRepository.save(movie_details).getId() == new_details.getId()) {
            return "Updated";
        } else {
            return "Not Updated";
        }

    }


}

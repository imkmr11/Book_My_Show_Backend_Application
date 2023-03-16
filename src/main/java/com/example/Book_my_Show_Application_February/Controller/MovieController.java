package com.example.Book_my_Show_Application_February.Controller;

import com.example.Book_my_Show_Application_February.EntryDtos.MovieEntryDto;
import com.example.Book_my_Show_Application_February.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {


    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody MovieEntryDto movieEntryDto){

        try{
            String result = movieService.addMovie(movieEntryDto);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e){
            String response = "Movie not added";
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/remove") //http://localhost:8080/movies/remove?movieId=<id here>
    public ResponseEntity<String> removeMovie(@RequestParam("movieId") int movieId){
        String response = movieService.removeMovie(movieId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-show-time") //http://localhost:8080/movies/get-show-time?movie-id=<id here>&theater-id=<id here>
    public ResponseEntity<List<Pair<LocalDate,LocalTime>>> getShowTime(@RequestParam("movie-id") int movieId,
                                                       @RequestParam("theater-id") int theaterId){
        List<Pair<LocalDate,LocalTime>> pairList = movieService.getShowTime(movieId,theaterId);
        return new ResponseEntity<>(pairList,HttpStatus.FOUND);
    }

    @GetMapping("/movie-with-max-shows") //http://localhost:8080/movies/movie-with-max-shows
    public ResponseEntity<Pair<Integer,String>> movieWithMaxShows(){
        Pair<Integer, String> pair = movieService.movieWithMaxShows();
        return new ResponseEntity<>(pair,HttpStatus.FOUND);
    }

    @GetMapping("/movie-with-max-collection") //http://localhost:8080/movies/movie-with-max-collection
    public ResponseEntity<MovieCollectionResponseDto> movieWithMaxCollection(){
        MovieCollectionResponseDto movieCollectionResponseDto = movieService.movieWithMaxCollection();
        return new ResponseEntity<>(movieCollectionResponseDto,HttpStatus.FOUND);
    }

    @GetMapping("/all-movies-total-collection") //http://localhost:8080/movies/all-movies-total-collection
    public ResponseEntity<Map<String /*movieName*/, Integer /*totalCollection*/>> allMoviesTotalCollection(){
        Map<String,Integer> moviesAndTheirCollections = movieService.allMoviesTotalCollection();
        return new ResponseEntity<>(moviesAndTheirCollections,HttpStatus.FOUND);
    }

    @GetMapping("/collection/{movie}") //http://localhost:8080/movies/collection/<movie>
    public ResponseEntity<Integer> totalCollectionOfMovie(@PathVariable("movie") String movieName){
        int collection = movieService.totalCollectionOfMovie(movieName);
        return new ResponseEntity<>(collection,HttpStatus.FOUND);
}

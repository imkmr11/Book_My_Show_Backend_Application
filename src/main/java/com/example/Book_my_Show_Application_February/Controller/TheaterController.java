package com.example.Book_my_Show_Application_February.Controller;


import com.example.Book_my_Show_Application_February.EntryDtos.TheaterEntryDto;
import com.example.Book_my_Show_Application_February.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/add")
    public ResponseEntity addTheater(@RequestBody TheaterEntryDto theaterEntryDto){

        try{
            String result = theaterService.addTheater(theaterEntryDto);
            return new ResponseEntity(result, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }



    }
     @DeleteMapping("/remove") //http://localhost:8080/theater/remove?theaterId=<id here>
    public ResponseEntity<String> removeTheater(@RequestParam("theaterId") int theaterId){
        String response = theaterService.removeTheater(theaterId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/theaters-with-unique-locations") //http://localhost:8080/theater/theaters-with-unique-locations
    public ResponseEntity<Map<String,String>> theatersWithUniqueLocations(){
        Map<String,String> theatersWithUniqueLocations = theaterService.theaterWithUniqueLocations();
        return new ResponseEntity<>(theatersWithUniqueLocations,HttpStatus.FOUND);
    }
}

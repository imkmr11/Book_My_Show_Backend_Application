package com.example.Book_my_Show_Application_February.Controller;


import com.example.Book_my_Show_Application_February.EntryDtos.ShowEntryDto;
import com.example.Book_my_Show_Application_February.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public ResponseEntity<String> addShow(@RequestBody ShowEntryDto showEntryDto){

        return new ResponseEntity<>(showService.addShow(showEntryDto), HttpStatus.CREATED);

    }
    
    
    @DeleteMapping("/remove") //http://localhost:8080/show/remove?showId=<id here>
    public ResponseEntity<String> removeShow(@RequestParam("showId") int showId){
        String response = showService.removeShow(showId);
        if(response.equals("CANCELED")){
            //Email will be sent to the users that show has been cancelled,
            //you will be refunded your money back
        }
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }


}

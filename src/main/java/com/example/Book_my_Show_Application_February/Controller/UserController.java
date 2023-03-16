package com.example.Book_my_Show_Application_February.Controller;

import com.example.Book_my_Show_Application_February.EntryDtos.UserEntryDto;
import com.example.Book_my_Show_Application_February.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserEntryDto userEntryDto){

        try{
            String response = userService.addUser(userEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        }catch (Exception e){

            String result = "User could not be added";
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }

    }
     @DeleteMapping("/remove") //http://localhost:8080/users/remove?userId=<id here>
    public ResponseEntity<String> removeUser(@RequestParam("userId") int userId){
        String response = userService.removeUser(userId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/all-tickets") //http://localhost:8080/users/all-tickets?userId=<id here>
    public ResponseEntity<List<TicketDetailsResponseDto>> allTickets(@RequestParam("userId") int userId){
        //This will return all tickets booked by user till now...and this includes even cancelled tickets also
        List<TicketDetailsResponseDto> ticketDetailsResponseDtoList = userService.allTickets(userId);
        return new ResponseEntity<>(ticketDetailsResponseDtoList,HttpStatus.FOUND);
    }

    @PutMapping("/update-address/{userId}") //http://localhost:8080/users/update-address/<userId>
    public ResponseEntity<String> updateUserAddress(@PathVariable int userId, @RequestBody String address){
        String response = userService.updateUserAddress(userId,address);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }
}

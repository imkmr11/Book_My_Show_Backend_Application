package com.example.Book_my_Show_Application_February.Controller;


import com.example.Book_my_Show_Application_February.EntryDtos.TicketEntryDto;
import com.example.Book_my_Show_Application_February.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/book")
    public String bookTicket(@RequestBody TicketEntryDto ticketEntryDto){


        try{
            String result = ticketService.addTicket(ticketEntryDto);
            return result;

        }catch (Exception e){

            return "";
        }

    }
      @GetMapping("/get-ticket-details") //http://localhost:8080/tickets/get-ticket-details?ticketId=<id here>
    public ResponseEntity<TicketDetailsResponseDto> getDetails(@RequestParam("ticketId") int ticketId){
        TicketDetailsResponseDto ticketDetailsResponseDto = ticketService.getDetails(ticketId);
        return new ResponseEntity<>(ticketDetailsResponseDto,HttpStatus.FOUND);
    }

    @DeleteMapping("/cancel_ticket") //http://localhost:8080/tickets/cancel_ticket?ticketId=<id here>
    public ResponseEntity<String> cancelTicket(@RequestParam("ticketId") int ticketId){
        String response = ticketService.cancelTicket(ticketId);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }
}

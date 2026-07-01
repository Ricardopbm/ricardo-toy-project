package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TicketService;
import com.example.demo.model.dto.NewTicketDTO;
import com.example.demo.model.dto.UpdateTicketStatusDTO;
import com.example.demo.repository.entity.Ticket;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket create(@Valid @RequestBody NewTicketDTO newTicket) {
        return ticketService.create(newTicket);
    }

    @PatchMapping(value = "/{id}/status", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Ticket updateStatus(
        @PathVariable Integer id,
        @Valid @RequestBody UpdateTicketStatusDTO update
    ) {
        return ticketService.updateStatus(id, update);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Ticket> findAll() {
        return ticketService.findAll();
    }
}

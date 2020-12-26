package com.example.joseph.tokoin.services;

import com.example.joseph.tokoin.dtos.TicketDTO;
import com.example.joseph.tokoin.models.Organization;
import com.example.joseph.tokoin.models.Ticket;
import com.example.joseph.tokoin.models.User;

import java.util.List;

public interface TicketService {

  TicketDTO findTicketById(List<Organization> orgRepo, List<Ticket> ticketRepo, List<User> userRepo, String id);

  List<TicketDTO> findTicketBySearchable(List<Organization> orgRepo, List<Ticket> ticketRepo, List<User> userRepo, String key, String value);
}

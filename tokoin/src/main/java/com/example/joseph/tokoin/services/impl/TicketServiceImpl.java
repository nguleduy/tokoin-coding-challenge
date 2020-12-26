package com.example.joseph.tokoin.services.impl;

import com.example.joseph.tokoin.dtos.TicketDTO;
import com.example.joseph.tokoin.models.Organization;
import com.example.joseph.tokoin.models.Ticket;
import com.example.joseph.tokoin.models.User;
import com.example.joseph.tokoin.services.TicketService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

  @Override
  public TicketDTO findTicketById(List<Organization> orgRepo, List<Ticket> ticketRepo, List<User> userRepo, String id) {
    TicketDTO ticketDTO = ticketRepo.stream().filter(ticket -> ticket.getId() == id).map(ticket -> new TicketDTO(ticket)).findFirst().orElseThrow(() -> new NoSuchElementException());
    ticketDTO.setOrganizationName(orgRepo.stream().filter(o -> o.getId() == ticketDTO.getOrganizationId()).findFirst().get().getName());
    ticketDTO.setSubmitterName(userRepo.stream().filter(u -> u.getId() == ticketDTO.getSubmitterId()).findFirst().get().getName());
    ticketDTO.setAssigneeName(userRepo.stream().filter(u -> u.getId() == ticketDTO.getAssigneeId()).findFirst().get().getName());
    return ticketDTO;
  }

  @Override
  public List<TicketDTO> findTicketBySearchable(List<Organization> orgRepo, List<Ticket> ticketRepo, List<User> userRepo, String key, String value) {
    if (Strings.isBlank(key)) {
      throw new NullPointerException("Key is invalid.");
    } else {
      switch (key) {
        case "_id":
          return ticketRepo.stream().filter(item -> item.getId().equalsIgnoreCase(value)).map(item -> findTicketById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "url":
          return ticketRepo.stream().filter(item -> item.getUrl().equalsIgnoreCase(value)).map(item -> findTicketById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "external_id":
          return ticketRepo.stream().filter(item -> item.getExternalId().equalsIgnoreCase(value)).map(item -> findTicketById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "created_at":
          return ticketRepo.stream().filter(item -> item.getCreatedAt().equalsIgnoreCase(value)).map(item -> findTicketById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "type":
          return ticketRepo.stream().filter(item -> item.getType().equalsIgnoreCase(value)).map(item -> findTicketById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "subject":
          return ticketRepo.stream().filter(item -> item.getSubject().equalsIgnoreCase(value)).map(item -> findTicketById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "description":
          return ticketRepo.stream().filter(item -> item.getDescription().equalsIgnoreCase(value)).map(item -> findTicketById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "priority":
          return ticketRepo.stream().filter(item -> item.getPriority().equalsIgnoreCase(value)).map(item -> findTicketById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "status":
          return ticketRepo.stream().filter(item -> item.getStatus().equalsIgnoreCase(value)).map(item -> findTicketById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "submitter_id":
          return ticketRepo.stream().filter(item -> item.getSubmitterId() == Integer.valueOf(value)).map(item -> findTicketById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "assignee_id":
          return ticketRepo.stream().filter(item -> item.getAssigneeId() == Integer.valueOf(value)).map(item -> findTicketById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "organization_id":
          return ticketRepo.stream().filter(item -> item.getOrganizationId() == Integer.valueOf(value)).map(item -> findTicketById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "tags":
          List<Ticket> tags = new ArrayList<>();
          for (Ticket ticket : ticketRepo) {
            for (String str : ticket.getTags()) {
              if (str.equalsIgnoreCase(value)) {
                tags.add(ticket);
              }
            }
          }
          return tags.stream().map(item -> findTicketById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "has_incidents":
          return ticketRepo.stream().filter(item -> item.getHasIncidents() == Boolean.valueOf(value)).map(item -> findTicketById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "due_at":
          return ticketRepo.stream().filter(item -> item.getDueAt().equalsIgnoreCase(value)).map(item -> findTicketById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "via":
          return ticketRepo.stream().filter(item -> item.getVia().equalsIgnoreCase(value)).map(item -> findTicketById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        default:
          return Collections.EMPTY_LIST;
      }
    }
  }
}

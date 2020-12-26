package com.example.joseph.tokoin.services.impl;

import com.example.joseph.tokoin.dtos.OrganizationDTO;
import com.example.joseph.tokoin.models.Organization;
import com.example.joseph.tokoin.models.Ticket;
import com.example.joseph.tokoin.models.User;
import com.example.joseph.tokoin.services.OrganizationService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {

  @Override
  public OrganizationDTO findOrganizationById(List<Organization> orgRepo, List<Ticket> ticketRepo, List<User> userRepo, Integer id) {
    OrganizationDTO organizationDTO = orgRepo.stream().filter(o -> o.getId() == id).map(organization -> new OrganizationDTO(organization)).findFirst().orElseThrow(() -> new NoSuchElementException());
    organizationDTO.setTicketSubjects(ticketRepo.stream().filter(t -> t.getOrganizationId() == organizationDTO.getId()).map(t -> t.getSubject()).toArray(String[]::new));
    organizationDTO.setUsernames(userRepo.stream().filter(u -> u.getOrganizationId() == organizationDTO.getId()).map(u -> u.getName()).toArray(String[]::new));
    return organizationDTO;
  }

  @Override
  public List<OrganizationDTO> findOrganizationBySearchable(List<Organization> orgRepo, List<Ticket> ticketRepo, List<User> userRepo, String key, String value) {
    if (Strings.isBlank(key)) {
      throw new NullPointerException("Key is invalid.");
    } else {
      switch (key) {
        case "_id":
          return orgRepo.stream().filter(item -> item.getId() == Integer.valueOf(value)).map(item -> findOrganizationById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "url":
          return orgRepo.stream().filter(item -> item.getUrl().equalsIgnoreCase(value)).map(item -> findOrganizationById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "external_id":
          return orgRepo.stream().filter(item -> item.getExternalId().equalsIgnoreCase(value)).map(item -> findOrganizationById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "name":
          return orgRepo.stream().filter(item -> item.getName().equalsIgnoreCase(value)).map(item -> findOrganizationById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "domain_names":
          List<Organization> domainNames = new ArrayList<>();
          for (Organization organization : orgRepo) {
            for (String str : organization.getDomainNames()) {
              if (str.equalsIgnoreCase(value)) {
                domainNames.add(organization);
              }
            }
          }
          return domainNames.stream().map(item -> findOrganizationById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "created_at":
          return orgRepo.stream().filter(item -> item.getCreatedAt().equalsIgnoreCase(value)).map(item -> findOrganizationById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "details":
          return orgRepo.stream().filter(item -> item.getDetails().equalsIgnoreCase(value)).map(item -> findOrganizationById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "shared_tickets":
          return orgRepo.stream().filter(item -> item.getSharedTickets().equals(Boolean.valueOf(value))).map(item -> findOrganizationById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "tags":
          List<Organization> tags = new ArrayList<>();
          for (Organization organization : orgRepo) {
            for (String str : organization.getTags()) {
              if (str.equalsIgnoreCase(value)) {
                tags.add(organization);
              }
            }
          }
          return tags.stream().map(item -> findOrganizationById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        default:
          return Collections.EMPTY_LIST;
      }
    }
  }
}

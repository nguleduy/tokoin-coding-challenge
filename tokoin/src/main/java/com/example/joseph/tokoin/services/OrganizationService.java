package com.example.joseph.tokoin.services;

import com.example.joseph.tokoin.dtos.OrganizationDTO;
import com.example.joseph.tokoin.models.Organization;
import com.example.joseph.tokoin.models.Ticket;
import com.example.joseph.tokoin.models.User;

import java.util.List;

public interface OrganizationService {

  OrganizationDTO findOrganizationById(List<Organization> orgRepo, List<Ticket> ticketRepo, List<User> userRepo, Integer id);

  List<OrganizationDTO> findOrganizationBySearchable(List<Organization> orgRepo, List<Ticket> ticketRepo, List<User> userRepo, String key, String value);
}

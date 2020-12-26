package com.example.joseph.tokoin.services.impl;

import com.example.joseph.tokoin.dtos.UserDTO;
import com.example.joseph.tokoin.models.Organization;
import com.example.joseph.tokoin.models.Ticket;
import com.example.joseph.tokoin.models.User;
import com.example.joseph.tokoin.services.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

  @Override
  public UserDTO findUserById(List<Organization> orgRepo, List<Ticket> ticketRepo, List<User> userRepo, Integer id) {
    UserDTO userDTO = userRepo.stream().filter(user -> user.getId() == id).map(user -> new UserDTO(user)).findFirst().orElseThrow(() -> new NoSuchElementException());
    userDTO.setAssigneeTicketSubjects(ticketRepo.stream().filter(t -> t.getAssigneeId() == userDTO.getId()).map(t -> t.getSubject()).toArray(String[]::new));
    userDTO.setSubmittedTicketSubjects(ticketRepo.stream().filter(t -> t.getSubmitterId() == userDTO.getId()).map(t -> t.getSubject()).toArray(String[]::new));
    userDTO.setOrganizationName(orgRepo.stream().filter(o -> o.getId() == userDTO.getOrganizationId()).findFirst().get().getName());
    return userDTO;
  }

  @Override
  public List<UserDTO> findUserBySearchable(List<Organization> orgRepo, List<Ticket> ticketRepo, List<User> userRepo, String key, String value) {
    if (Strings.isBlank(key)) {
      throw new NullPointerException("Key is invalid.");
    } else {
      switch (key) {
        case "_id":
          return userRepo.stream().filter(item -> item.getId() == Integer.valueOf(value)).map(item -> findUserById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "url":
          return userRepo.stream().filter(item -> item.getUrl().equalsIgnoreCase(value)).map(item -> findUserById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "external_id":
          return userRepo.stream().filter(item -> item.getExternalId().equalsIgnoreCase(value)).map(item -> findUserById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "name":
          return userRepo.stream().filter(item -> item.getName().equalsIgnoreCase(value)).map(item -> findUserById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "alias":
          return userRepo.stream().filter(item -> item.getAlias().equalsIgnoreCase(value)).map(item -> findUserById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "created_at":
          return userRepo.stream().filter(item -> item.getCreatedAt().equalsIgnoreCase(value)).map(item -> findUserById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "active":
          return userRepo.stream().filter(item -> item.getActive() == Boolean.valueOf(value)).map(item -> findUserById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "verified":
          return userRepo.stream().filter(item -> item.getVerified() == Boolean.valueOf(value)).map(item -> findUserById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "shared":
          return userRepo.stream().filter(item -> item.getShared() == Boolean.valueOf(value)).map(item -> findUserById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "locale":
          return userRepo.stream().filter(item -> item.getLocale().equalsIgnoreCase(value)).map(item -> findUserById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "timezone":
          return userRepo.stream().filter(item -> item.getTimezone().equalsIgnoreCase(value)).map(item -> findUserById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "last_login_at":
          return userRepo.stream().filter(item -> item.getLastLoginAt().equalsIgnoreCase(value)).map(item -> findUserById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "email":
          return userRepo.stream().filter(item -> item.getEmail().equalsIgnoreCase(value)).map(item -> findUserById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "phone":
          return userRepo.stream().filter(item -> item.getPhone().equalsIgnoreCase(value)).map(item -> findUserById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "signature":
          return userRepo.stream().filter(item -> item.getSignature().equalsIgnoreCase(value)).map(item -> findUserById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "organization_id":
          return userRepo.stream().filter(item -> item.getOrganizationId() == Integer.valueOf(value)).map(item -> findUserById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "tags":
          List<User> tags = new ArrayList<>();
          for (User user : userRepo) {
            for (String str : user.getTags()) {
              if (str.equalsIgnoreCase(value)) {
                tags.add(user);
              }
            }
          }
          return tags.stream().map(item -> findUserById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "suspended":
          return userRepo.stream().filter(item -> item.getSuspended() == Boolean.valueOf(value)).map(item -> findUserById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        case "role":
          return userRepo.stream().filter(item -> item.getRole().equalsIgnoreCase(value)).map(item -> findUserById(orgRepo, ticketRepo, userRepo, item.getId())).collect(Collectors.toList());
        default:
          return Collections.EMPTY_LIST;
      }
    }
  }
}

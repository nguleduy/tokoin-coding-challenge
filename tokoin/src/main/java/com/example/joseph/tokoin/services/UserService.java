package com.example.joseph.tokoin.services;


import com.example.joseph.tokoin.dtos.UserDTO;
import com.example.joseph.tokoin.models.Organization;
import com.example.joseph.tokoin.models.Ticket;
import com.example.joseph.tokoin.models.User;

import java.util.List;

public interface UserService {

  UserDTO findUserById(List<Organization> orgRepo, List<Ticket> ticketRepo, List<User> userRepo, Integer id);

  List<UserDTO> findUserBySearchable(List<Organization> orgRepo, List<Ticket> ticketRepo, List<User> userRepo, String key, String value);
}

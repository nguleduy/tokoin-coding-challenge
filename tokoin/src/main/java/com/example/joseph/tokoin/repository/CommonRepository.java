package com.example.joseph.tokoin.repository;

import com.example.joseph.tokoin.models.Organization;
import com.example.joseph.tokoin.models.Ticket;
import com.example.joseph.tokoin.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Repository
public class CommonRepository {

  private static final String jsonPathUser = "/json/users.json";
  private static final String jsonPathOrganization = "/json/organizations.json";
  private static final String jsonPathTicket = "/json/tickets.json";

  public List<User> readJsonUserFile() {
    List<User> result = null;
    ObjectMapper mapper = new ObjectMapper();
    TypeReference<List<User>> typeReference = new TypeReference<>() {
    };
    InputStream inputStream = TypeReference.class.getResourceAsStream(jsonPathUser);
    try {
      result = mapper.readValue(inputStream, typeReference);
      inputStream.close();
    } catch (IOException e) {
      System.out.println("Unable to read users: " + e.getMessage());
    }
    return result;
  }

  public List<Organization> readJsonOrganizationFile() {
    List<Organization> result = null;
    ObjectMapper mapper = new ObjectMapper();
    TypeReference<List<Organization>> typeReference = new TypeReference<>() {
    };
    InputStream inputStream = TypeReference.class.getResourceAsStream(jsonPathOrganization);
    try {
      result = mapper.readValue(inputStream, typeReference);
      inputStream.close();
    } catch (IOException e) {
      System.out.println("Unable to read organizations: " + e.getMessage());
    }
    return result;
  }

  public List<Ticket> readJsonTicketFile() {
    List<Ticket> result = null;
    ObjectMapper mapper = new ObjectMapper();
    TypeReference<List<Ticket>> typeReference = new TypeReference<>() {
    };
    InputStream inputStream = TypeReference.class.getResourceAsStream(jsonPathTicket);
    try {
      result = mapper.readValue(inputStream, typeReference);
      inputStream.close();
    } catch (IOException e) {
      System.out.println("Unable to read tickets: " + e.getMessage());
    }
    return result;
  }
}

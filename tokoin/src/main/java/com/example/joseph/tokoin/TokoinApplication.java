package com.example.joseph.tokoin;

import com.example.joseph.tokoin.models.Organization;
import com.example.joseph.tokoin.models.Ticket;
import com.example.joseph.tokoin.models.User;
import com.example.joseph.tokoin.repository.CommonRepository;
import com.example.joseph.tokoin.services.OrganizationService;
import com.example.joseph.tokoin.services.TicketService;
import com.example.joseph.tokoin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class TokoinApplication implements CommandLineRunner {

  @Autowired
  CommonRepository commonRepository;

  @Autowired
  private OrganizationService organizationService;

  @Autowired
  private TicketService ticketService;

  @Autowired
  private UserService userService;

  private static final String INTRODUCTION_MSG = "Type 'quit' to exit at any time. Press 'Enter' to continue" +
          "\n" +
          "\tSelect search options:\n" +
          " \t* Press 1 to search\n" +
          " \t* Press 2 to view a list of searchable fields\n" +
          "\t* Type 'quit' to exit\"";
  private static final String CHOOSE_MODEL = "Select 1) Users or 2) Tickets or 3) Organizations";
  private static final String SEARCH_TERM = "Enter search term";
  private static final String SEARCH_VALUE = "Enter search value";
  private static final String[] USER_SEARCHABLE = {"_id", "url", "external_id", "name", "alias", "created_at", "active",
          "verified", "shared", "locale", "timezone", "last_login_at", "email", "phone", "signature", "organization_id", "tags", "suspended", "role"};
  private static final String[] TICKET_SEARCHABLE = {"_id", "url", "external_id", "created_at", "type", "subject", "description",
          "priority", "status", "submitter_id", "assignee_id", "organization_id", "tags", "has_incidents", "due_at", "via"};
  private static final String[] ORGANIZATION_SEARCHABLE = {"_id", "url", "external_id", "name", "domain_names", "created_at", "details", "shared_tickets", "tags"};

  private String searchTerm = "";
  private String searchValue = "";
  private List<Organization> organizations = new ArrayList<>();
  private List<User> users = new ArrayList<>();
  private List<Ticket> tickets = new ArrayList<>();

  @Override
  public void run(String... args) throws Exception {
    // init data
    organizations = commonRepository.readJsonOrganizationFile();
    users = commonRepository.readJsonUserFile();
    tickets = commonRepository.readJsonTicketFile();
    // start app
    this.tokoin();
  }

  private void showTermValue(Scanner scanner) {
    System.out.println(SEARCH_TERM);
    searchTerm = scanner.next();
    System.out.println(SEARCH_VALUE);
    searchValue = scanner.next();
  }

  private void tokoin() {
    System.out.println(INTRODUCTION_MSG);
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      if (scanner.hasNextInt()) {
        int option = scanner.nextInt();
        if (option == 1) {
          System.out.println(CHOOSE_MODEL);
          int key = scanner.nextInt();
          if (key == 1) {
            // Users
            showTermValue(scanner);
            System.out.println("Searching users for " + searchTerm + " with a value of " + searchValue);
            var result = userService.findUserBySearchable(organizations, tickets, users, searchTerm, searchValue);
            if (result.size() == 0) {
              System.out.println("No results found");
            }
            result.stream().forEach(System.out::println);
            System.out.println("-------------------------------------");
            System.out.println(INTRODUCTION_MSG);
          } else if (key == 2) {
            // Tickets
            showTermValue(scanner);
            System.out.println("Searching tickets for " + searchTerm + " with a value of " + searchValue);
            var result = ticketService.findTicketBySearchable(organizations, tickets, users, searchTerm, searchValue);
            if (result.size() == 0) {
              System.out.println("No results found");
            }
            result.stream().forEach(System.out::println);
            System.out.println("-------------------------------------");
            System.out.println(INTRODUCTION_MSG);
          } else if (key == 3) {
            // Organizations
            showTermValue(scanner);
            System.out.println("Searching organizations for " + searchTerm + " with a value of " + searchValue);
            var result = organizationService.findOrganizationBySearchable(organizations, tickets, users, searchTerm, searchValue);
            if (result.size() == 0) {
              System.out.println("No results found");
            }
            result.stream().forEach(System.out::println);
            System.out.println("-------------------------------------");
            System.out.println(INTRODUCTION_MSG);
          }
        } else if (option == 2) {
          System.out.println("-------------------------------------");
          System.out.println("Search Users with");
          Arrays.stream(USER_SEARCHABLE).forEach(System.out::println);
          System.out.println("-------------------------------------");
          System.out.println("Search Tickets with");
          Arrays.stream(TICKET_SEARCHABLE).forEach(System.out::println);
          System.out.println("-------------------------------------");
          System.out.println("Search Organizations with");
          Arrays.stream(ORGANIZATION_SEARCHABLE).forEach(System.out::println);
          System.out.println("-------------------------------------");
          System.out.println(INTRODUCTION_MSG);
        }
      } else if (scanner.hasNext()) {
        String quit = scanner.next();
        if (quit.equalsIgnoreCase("quit")) {
          System.exit(0);
        }
      }
    }
  }

  public static void main(String[] args) {
    SpringApplication.run(TokoinApplication.class, args);
  }

}

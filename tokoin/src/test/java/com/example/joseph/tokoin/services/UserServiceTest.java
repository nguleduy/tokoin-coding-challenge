package com.example.joseph.tokoin.services;

import com.example.joseph.tokoin.TokoinApplication;
import com.example.joseph.tokoin.models.Organization;
import com.example.joseph.tokoin.models.Ticket;
import com.example.joseph.tokoin.models.User;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TokoinApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class UserServiceTest {

  @Autowired
  UserService userService;

  private PodamFactory podam = new PodamFactoryImpl();
  private List<Organization> organizations = new ArrayList<>();
  private List<Ticket> tickets = new ArrayList<>();
  private List<User> users = new ArrayList<>();
  private User user = new User();
  private Ticket ticket = new Ticket();
  private Organization organization = new Organization();

  @Before
  public void setUp() {
    user.setId(1);
    user.setUrl("http://initech.tokoin.io.com/api/v2/users/1.json");
    user.setExternalId("74341f74-9c79-49d5-9611-87ef9b6eb75f");
    user.setName("Francisca Rasmussen");
    user.setAlias("Miss Coffey");
    user.setCreatedAt("2016-04-15T05:19:46 -10:00");
    user.setActive(true);
    user.setVerified(true);
    user.setShared(false);
    user.setLocale("en-AU");
    user.setTimezone("Sri Lanka");
    user.setLastLoginAt("2013-08-04T01:03:27 -10:00");
    user.setEmail("coffeyrasmussen@flotonic.com");
    user.setPhone("8335-422-718");
    user.setSignature("Don't Worry Be Happy!");
    user.setOrganizationId(122);
    user.setTags(new String[]{"Springville"});
    user.setSuspended(true);
    user.setRole("admin");
    users.add(user);
    ticket.setId("13aafde0-81db-47fd-b1a2-94b0015803df");
    ticket.setUrl("http://initech.tokoin.io.com/api/v2/tickets/13aafde0-81db-47fd-b1a2-94b0015803df.json");
    ticket.setExternalId("6161e938-50cc-4545-acff-a4f23649b7c3");
    ticket.setCreatedAt("2016-03-30T08:35:27 -11:00");
    ticket.setType("task");
    ticket.setSubject("A Problem in Malawi");
    ticket.setDescription("Lorem ipsum eiusmod pariatur enim. Qui aliquip voluptate cupidatat eiusmod aute velit non aute ullamco.");
    ticket.setPriority("urgent");
    ticket.setStatus("solved");
    ticket.setSubmitterId(1);
    ticket.setAssigneeId(1);
    ticket.setOrganizationId(122);
    ticket.setTags(new String[]{"New Mexico"});
    ticket.setHasIncidents(false);
    ticket.setDueAt("2016-08-08T03:25:53 -10:00");
    ticket.setVia("voice");
    tickets.add(ticket);
    organization.setId(122);
    organization.setUrl("http://initech.tokoin.io.com/api/v2/organizations/122.json");
    organization.setExternalId("33c4e38d-bfa3-4b12-9bb6-6f547524cf33");
    organization.setName("Geekfarm");
    organization.setDomainNames(new String[]{"comstar.com"});
    organization.setCreatedAt("2016-04-10T11:12:35 -10:00");
    organization.setDetails("Non profit");
    organization.setSharedTickets(true);
    organization.setTags(new String[]{"Hensley"});
    organizations.add(organization);
  }

  @Test
  public void testFindUserById() {
    var result = this.userService.findUserById(organizations, tickets, users, 1);
    assertNotNull(result);
  }

  @Test
  public void testFindUserByIdNotFound() {
    assertThrows(NoSuchElementException.class, () -> this.userService.findUserById(organizations, tickets, users, 12345));
  }

  @Test
  public void testFindUserByUrl() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "url", "http://initech.tokoin.io.com/api/v2/users/1.json");
    assertNotNull(result);
  }

  @Test
  public void testFindUserByUrlNotFound() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "url", "test");
    assertEquals(0, result.size());
  }

  @Test
  public void testFindUserByExternalId() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "external_id", "74341f74-9c79-49d5-9611-87ef9b6eb75f");
    assertNotNull(result);
  }

  @Test
  public void testFindUserByExternalIdNotFound() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "external_id", "test");
    assertEquals(0, result.size());
  }

  @Test
  public void testFindUserByName() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "name", "Francisca Rasmussen");
    assertNotNull(result);
  }

  @Test
  public void testFindUserByNameNotFound() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "name", "test");
    assertEquals(0, result.size());
  }

  @Test
  public void testFindUserByAlias() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "alias", "Miss Coffey");
    assertNotNull(result);
  }

  @Test
  public void testFindUserByAliasNotFound() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "alias", "test");
    assertEquals(0, result.size());
  }

  @Test
  public void testFindUserByCreatedAt() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "created_at", "2016-04-15T05:19:46 -10:00");
    assertNotNull(result);
  }

  @Test
  public void testFindUserByCreatedAtNotFound() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "created_at", "test");
    assertEquals(0, result.size());
  }

  @Test
  public void testFindUserByActive() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "active", "true");
    assertNotNull(result);
  }

  @Test
  public void testFindUserByVerified() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "verified", "true");
    assertNotNull(result);
  }

  @Test
  public void testFindUserByShred() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "shared", "false");
    assertNotNull(result);
  }

  @Test
  public void testFindUserByLocale() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "locale", "en-AU");
    assertNotNull(result);
  }

  @Test
  public void testFindUserByLocaleNotFound() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "locale", "test");
    assertEquals(0, result.size());
  }

  @Test
  public void testFindUserByTimeZone() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "timezone", "Sri Lanka");
    assertNotNull(result);
  }

  @Test
  public void testFindUserByTimeZoneNotFound() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "timezone", "test");
    assertEquals(0, result.size());
  }

  @Test
  public void testFindUserByLastLoginAt() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "last_login_at", "2013-08-04T01:03:27 -10:00");
    assertNotNull(result);
  }

  @Test
  public void testFindUserByLastLoginAtNotFound() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "last_login_at", "test");
    assertEquals(0, result.size());
  }

  @Test
  public void testFindUserByEmail() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "email", "coffeyrasmussen@flotonic.com");
    assertNotNull(result);
  }

  @Test
  public void testFindUserByEmailNotFound() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "email", "test");
    assertEquals(0, result.size());
  }

  @Test
  public void testFindUserByPhone() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "phone", "8335-422-718");
    assertNotNull(result);
  }

  @Test
  public void testFindUserByPhoneNotFound() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "phone", "test");
    assertEquals(0, result.size());
  }

  @Test
  public void testFindUserBySignature() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "signature", "Don't Worry Be Happy!");
    assertNotNull(result);
  }

  @Test
  public void testFindUserBySignatureNotFound() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "signature", "test");
    assertEquals(0, result.size());
  }

  @Test
  public void testFindUserByOrganizationId() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "organization_id", "119");
    assertNotNull(result);
  }

  @Test
  public void testFindUserByOrganizationIdNotFound() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "organization_id", "12345");
    assertEquals(0, result.size());
  }

  @Test
  public void testFindUserByTags() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "tags", "Springville");
    assertNotNull(result);
  }

  @Test
  public void testFindUserByTagsNotFound() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "tags", "test");
    assertEquals(0, result.size());
  }

  @Test
  public void testFindUserBySuspended() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "suspended", "true");
    assertNotNull(result);
  }

  @Test
  public void testFindUserByRole() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "role", "admin");
    assertNotNull(result);
  }

  @Test
  public void testFindUserByRoleNotFound() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "role", "test");
    assertEquals(0, result.size());
  }

  @Test
  public void testFindUserWithNullKey() {
    assertThrows(NullPointerException.class, () -> this.userService.findUserBySearchable(organizations, tickets, users, null, null));
  }

  @Test
  public void testFindUserWithInvalidKey() {
    var result = this.userService.findUserBySearchable(organizations, tickets, users, "key", null);
    assertEquals(0, result.size());
  }
}

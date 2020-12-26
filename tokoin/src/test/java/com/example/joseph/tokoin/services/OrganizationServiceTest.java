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
public class OrganizationServiceTest {

  @Autowired
  OrganizationService organizationService;

  private PodamFactory podam = new PodamFactoryImpl();
  private List<Organization> organizations = new ArrayList<>();
  private List<Ticket> tickets = new ArrayList<>();
  private List<User> users = new ArrayList<>();
  private Organization organization = new Organization();

  @Before
  public void setUp() {
    organization = podam.manufacturePojo(Organization.class);
    organization.setId(116);
    organization.setUrl("http://initech.tokoin.io.com/api/v2/organizations/116.json");
    organization.setExternalId("dbc692fc-e1ae-47d8-a1d7-263d07710fe1");
    organization.setName("Zentry");
    organization.setCreatedAt("2016-01-13T09:34:07 -11:00");
    organization.setDetails("Artisan");
    organization.setSharedTickets(false);
    organization.setDomainNames(new String[]{"datagene.com"});
    organization.setTags(new String[]{"Schneider"});
    organizations.add(organization);
  }

  @Test
  public void testFindOrganizationById() {
    var result = this.organizationService.findOrganizationById(organizations, tickets, users, 116);
    assertNotNull(result);
  }

  @Test
  public void testFindOrganizationByIdNotFound() {
    assertThrows(NoSuchElementException.class, () -> this.organizationService.findOrganizationById(organizations, tickets, users, 123456));
  }

  @Test
  public void testFindOrganizationByIdField() {
    var result = this.organizationService.findOrganizationBySearchable(organizations, tickets, users, "_id", "116");
    assertNotNull(result);
  }

  @Test
  public void testFindOrganizationByIdFieldNotFound() {
    var result = this.organizationService.findOrganizationBySearchable(organizations, tickets, users, "_id", "123456");
    assertEquals(0, result.size());
  }

  @Test
  public void testFindOrganizationByUrlField() {
    var result = this.organizationService.findOrganizationBySearchable(organizations, tickets, users, "url", "http://initech.tokoin.io.com/api/v2/organizations/116.json");
    assertNotNull(result);
  }

  @Test
  public void testFindOrganizationByUrlFieldNotFound() {
    var result = this.organizationService.findOrganizationBySearchable(organizations, tickets, users, "url", "url");
    assertEquals(0, result.size());
  }

  @Test
  public void testFindOrganizationByExternalIdField() {
    var result = this.organizationService.findOrganizationBySearchable(organizations, tickets, users, "external_id", "dbc692fc-e1ae-47d8-a1d7-263d07710fe1");
    assertNotNull(result);
  }

  @Test
  public void testFindOrganizationByExternalIdFieldNotFound() {
    var result = this.organizationService.findOrganizationBySearchable(organizations, tickets, users, "external_id", "external_id");
    assertEquals(0, result.size());
  }

  @Test
  public void testFindOrganizationByNameField() {
    var result = this.organizationService.findOrganizationBySearchable(organizations, tickets, users, "name", "Zentry");
    assertNotNull(result);
  }

  @Test
  public void testFindOrganizationByNameFieldNotFound() {
    var result = this.organizationService.findOrganizationBySearchable(organizations, tickets, users, "name", "name");
    assertEquals(0, result.size());
  }

  @Test
  public void testFindOrganizationByCreatedAtField() {
    var result = this.organizationService.findOrganizationBySearchable(organizations, tickets, users, "created_at", "2016-01-13T09:34:07 -11:00");
    assertNotNull(result);
  }

  @Test
  public void testFindOrganizationByCreatedAtFieldNotFound() {
    var result = this.organizationService.findOrganizationBySearchable(organizations, tickets, users, "created_at", "created_at");
    assertEquals(0, result.size());
  }

  @Test
  public void testFindOrganizationByDetailsField() {
    var result = this.organizationService.findOrganizationBySearchable(organizations, tickets, users, "details", "Artisan");
    assertNotNull(result);
  }

  @Test
  public void testFindOrganizationByDetailsFieldNotFound() {
    var result = this.organizationService.findOrganizationBySearchable(organizations, tickets, users, "details", "details");
    assertEquals(0, result.size());
  }

  @Test
  public void testFindOrganizationBySharedTicketsField() {
    var result = this.organizationService.findOrganizationBySearchable(organizations, tickets, users, "shared_tickets", "false");
    assertNotNull(result);
  }

  @Test
  public void testFindOrganizationByDomainNamesField() {
    var result = this.organizationService.findOrganizationBySearchable(organizations, tickets, users, "domain_names", "datagene.com");
    assertNotNull(result);
  }

  @Test
  public void testFindOrganizationByDomainNamesFieldNotFound() {
    var result = this.organizationService.findOrganizationBySearchable(organizations, tickets, users, "domain_names", "domain_names");
    assertEquals(0, result.size());
  }

  @Test
  public void testFindOrganizationByTagsField() {
    var result = this.organizationService.findOrganizationBySearchable(organizations, tickets, users, "tags", "Schneider");
    assertNotNull(result);
  }

  @Test
  public void testFindOrganizationByTagsFieldNotFound() {
    var result = this.organizationService.findOrganizationBySearchable(organizations, tickets, users, "tags", "domain_names");
    assertEquals(0, result.size());
  }

  @Test
  public void testFindOrganizationWithNullKey() {
    assertThrows(NullPointerException.class, () -> this.organizationService.findOrganizationBySearchable(organizations, tickets, users, null, null));
  }

  @Test
  public void testFindOrganizationWithInvalidKey() {
    var result = this.organizationService.findOrganizationBySearchable(organizations, tickets, users, "key", null);
    assertEquals(0, result.size());
  }
}

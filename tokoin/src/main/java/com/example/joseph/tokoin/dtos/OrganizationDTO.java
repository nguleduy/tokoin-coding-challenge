package com.example.joseph.tokoin.dtos;

import com.example.joseph.tokoin.models.Organization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrganizationDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;
  private String url;
  private String externalId;
  private String name;
  private String[] domainNames;
  private String createdAt;
  private String details;
  private Boolean sharedTickets;
  private String[] tags;
  private String[] ticketSubjects;
  private String[] usernames;

  public OrganizationDTO(Organization organization) {
    this.id = organization.getId();
    this.url = organization.getUrl();
    this.externalId = organization.getExternalId();
    this.name = organization.getName();
    this.domainNames = organization.getDomainNames();
    this.createdAt = organization.getCreatedAt();
    this.details = organization.getDetails();
    this.sharedTickets = organization.getSharedTickets();
    this.tags = organization.getTags();
  }
}

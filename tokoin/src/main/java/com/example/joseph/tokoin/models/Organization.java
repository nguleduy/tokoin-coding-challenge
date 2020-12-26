package com.example.joseph.tokoin.models;

import com.fasterxml.jackson.annotation.JsonAlias;
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
public class Organization implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonAlias("_id")
  private Integer id;

  private String url;

  @JsonAlias("external_id")
  private String externalId;

  private String name;

  @JsonAlias("domain_names")
  private String[] domainNames;

  @JsonAlias("created_at")
  private String createdAt;

  private String details;
  @JsonAlias("shared_tickets")

  private Boolean sharedTickets;

  private String[] tags;

}

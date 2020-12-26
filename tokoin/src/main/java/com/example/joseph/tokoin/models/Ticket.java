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
public class Ticket implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonAlias("_id")
  private String id;

  private String url;

  @JsonAlias("external_id")
  private String externalId;

  @JsonAlias("created_at")
  private String createdAt;

  private String type;

  private String subject;

  private String description;

  private String priority;

  private String status;

  @JsonAlias("submitter_id")
  private Integer submitterId;

  @JsonAlias("assignee_id")
  private Integer assigneeId;

  @JsonAlias("organization_id")
  private Integer organizationId;

  private String[] tags;

  @JsonAlias("has_incidents")
  private Boolean hasIncidents;

  @JsonAlias("due_at")
  private String dueAt;

  private String via;
}

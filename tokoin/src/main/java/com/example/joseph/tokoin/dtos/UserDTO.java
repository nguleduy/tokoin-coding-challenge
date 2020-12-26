package com.example.joseph.tokoin.dtos;

import com.example.joseph.tokoin.models.User;
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
public class UserDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;
  private String url;
  private String externalId;
  private String name;
  private String alias;
  private String createdAt;
  private Boolean active;
  private Boolean verified;
  private Boolean shared;
  private String locale;
  private String timezone;
  private String lastLoginAt;
  private String email;
  private String phone;
  private String signature;
  private Integer organizationId;
  private String[] tags;
  private Boolean suspended;
  private String role;

  private String[] assigneeTicketSubjects;
  private String[] submittedTicketSubjects;
  private String organizationName;

  public UserDTO(User user) {
    this.id = user.getId();
    this.url = user.getUrl();
    this.externalId = user.getExternalId();
    this.name = user.getName();
    this.alias = user.getAlias();
    this.createdAt = user.getCreatedAt();
    this.active = user.getActive();
    this.verified = user.getVerified();
    this.shared = user.getShared();
    this.locale = user.getLocale();
    this.timezone = user.getTimezone();
    this.lastLoginAt = user.getLastLoginAt();
    this.email = user.getEmail();
    this.phone = user.getPhone();
    this.signature = user.getSignature();
    this.organizationId = user.getOrganizationId();
    this.tags = user.getTags();
    this.suspended = user.getSuspended();
    this.role = user.getRole();
  }
}

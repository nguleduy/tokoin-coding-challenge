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
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonAlias("_id")
  private Integer id;

  private String url;

  @JsonAlias("external_id")
  private String externalId;

  private String name;

  private String alias;

  @JsonAlias("created_at")
  private String createdAt;

  private Boolean active;

  private Boolean verified;

  private Boolean shared;

  private String locale;

  private String timezone;

  @JsonAlias("last_login_at")
  private String lastLoginAt;

  private String email;

  private String phone;

  private String signature;

  @JsonAlias("organization_id")
  private Integer organizationId;

  private String[] tags;

  private Boolean suspended;

  private String role;
}

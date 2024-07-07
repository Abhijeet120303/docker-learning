package org.dnyanyog.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

@Entity
@Table
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Users {

  @GeneratedValue @Id @Column private int user_code;

  @Column(name = "user_id")
  private long userId;

  @Column(name = "user_name")
  private String userName;

  @Column private String password;

  @Column private String email_id;

  @Column private int age;

  @Column private String aes_Key;

  public static Users getInstance() {
    return new Users();
  }

  public String getUserName() {
    return userName;
  }

  public Users setUserName(String userName) {
    this.userName = userName;

    return this;
  }

  public String getPassword() {
    return password;
  }

  public Users setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getEmail_id() {
    return email_id;
  }

  public Users setEmail_id(String email_id) {
    this.email_id = email_id;
    return this;
  }

  public int getAge() {
    return age;
  }

  public Users setAge(int age) {
    this.age = age;
    return this;
  }

  public int getUser_code() {
    return user_code;
  }

  public Users setUser_code(int user_code) {
    this.user_code = user_code;
    return this;
  }

  public long getUserId() {
    return userId;
  }

  public Users setUserId(long userId) {
    this.userId = userId;
    return this;
  }

  public String getAes_Key() {
    return aes_Key;
  }

  public Users setAes_Key(String aes_Key) {
    this.aes_Key = aes_Key;
    return this;
  }
}

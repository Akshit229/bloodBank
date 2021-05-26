package com.akshit.bloodbankmain.Models;

public class RequestDataModel {


  private String id;

  private String message;

  private String name;

  private String url;

  private String number;

  public RequestDataModel(String id, String message, String url, String number) {
    this.id = id;
    this.message = message;
    this.url = url;
    this.number = number;
  }

  public RequestDataModel() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

}
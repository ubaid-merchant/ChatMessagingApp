package com.ubaidmerchant.chatmessaging.models;

public class MemberDataModel {
  private String name;
  private String color;

  public MemberDataModel(String name, String color) {
    this.name = name;
    this.color = color;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  @Override public String toString() {
    return "MemberDataModel{" +
        "name='" + name + '\'' +
        ", color='" + color + '\'' +
        '}';
  }
}

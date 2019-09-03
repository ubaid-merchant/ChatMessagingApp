package com.ubaidmerchant.chatmessaging.models;

public class MessageModel {
  private String text;
  private MemberDataModel memberData;
  private boolean belongsToCurrentUser;
  private String time;
  private String initials;

  public MessageModel(String text, MemberDataModel data, boolean belongsToCurrentUser, String time,
      String initials) {
    this.text = text;
    this.memberData = data;
    this.belongsToCurrentUser = belongsToCurrentUser;
    this.time = time;
    this.initials = initials;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public MemberDataModel getMemberData() {
    return memberData;
  }

  public void setMemberData(MemberDataModel memberData) {
    this.memberData = memberData;
  }

  public boolean isBelongsToCurrentUser() {
    return belongsToCurrentUser;
  }

  public void setBelongsToCurrentUser(boolean belongsToCurrentUser) {
    this.belongsToCurrentUser = belongsToCurrentUser;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getInitials() {
    return initials;
  }

  public void setInitials(String initials) {
    this.initials = initials;
  }
}

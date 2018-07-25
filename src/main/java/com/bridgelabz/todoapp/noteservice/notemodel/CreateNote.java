package com.bridgelabz.todoapp.noteservice.notemodel;

import java.util.Date;

public class CreateNote {
	private String title;
	private String description;
	private String image;
	private boolean isPin;
	private boolean isArchieve;
	private boolean isTrash;
	private String color;
	private Date remindMe;
	public String getTitle() {
		return title;
	}
	public boolean isPin() {
		return isPin;
	}
	public void setPin(boolean isPin) {
		this.isPin = isPin;
	}
	public boolean isArchieve() {
		return isArchieve;
	}
	public void setArchieve(boolean isArchieve) {
		this.isArchieve = isArchieve;
	}
	public boolean isTrash() {
		return isTrash;
	}
	public void setTrash(boolean isTrash) {
		this.isTrash = isTrash;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Date getRemindMe() {
		return remindMe;
	}
	public void setRemindMe(Date remindMe) {
		this.remindMe = remindMe;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

}

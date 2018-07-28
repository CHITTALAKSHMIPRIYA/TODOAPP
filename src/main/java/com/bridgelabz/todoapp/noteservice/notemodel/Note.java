package com.bridgelabz.todoapp.noteservice.notemodel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>A POJO class with the Note details.</b>
 *        </p>
 */
@SuppressWarnings("serial")
@Document
public class Note implements Serializable {

	@Id
	private String note;
	private String user;
	private String title;
	private String description;
	public boolean isTrashed = false;
	private String createdAt;
	private String updatedAt;
	private Date remindMe;
	private boolean isPin=false;
	private boolean isArchieve=false;
	private List<LabelDTO> label;
	

	public List<LabelDTO> getLabel() {
		return label;
	}

	public void setLabel(List<LabelDTO> label) {
		this.label = label;
	}

	public boolean isPin() {
		return isPin;
	}

	public boolean isArchieve() {
		return isArchieve;
	}

	public void setArchieve(boolean isArchieve) {
		this.isArchieve = isArchieve;
	}

	public void setPin(boolean isPin) {
		this.isPin = isPin;
	}

	public boolean isTrashed() {
		return isTrashed;
	}

	

	public void setTrashed(boolean isTrashed) {
		this.isTrashed = isTrashed;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

/*	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}*/

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
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

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

}

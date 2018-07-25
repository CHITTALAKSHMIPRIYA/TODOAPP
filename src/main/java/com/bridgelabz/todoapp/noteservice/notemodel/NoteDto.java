
package com.bridgelabz.todoapp.noteservice.notemodel;

import java.util.List;

/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>A Dto class with the note details.</b>
 *        </p>
 */

public class NoteDto {
	private String title;
	private String description;
	private String id;
	private List<LabelDTO> label;

	public String getTitle() {
		return title;
	}

	public List<LabelDTO> getLabel() {
		return label;
	}

	public void setLabel(List<LabelDTO> label) {
		this.label = label;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	private String noteId;

}

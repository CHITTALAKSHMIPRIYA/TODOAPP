
package com.bridgelabz.todoapp.noteservice.noteservice;

import java.util.List;
import org.springframework.stereotype.Service;

import com.bridgelabz.todoapp.noteservice.notemodel.Label;
import com.bridgelabz.todoapp.noteservice.notemodel.LabelDTO;
import com.bridgelabz.todoapp.noteservice.notemodel.Note;
import com.bridgelabz.todoapp.noteservice.notemodel.NoteDto;
import com.bridgelabz.todoapp.utilservice.Exception.TodoException;

/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>Interface contains methods to create,update,delete note.</b>
 *        </p>
 */
@Service
public interface INoteService {
	/**
	 * @param title
	 * @param description
	 * @param authorId
	 * @param id
	 *            <p>
	 *            <b> Method to create a new note</b>
	 *            </p>
	 * @throws TodoException
	 */
	public void createNote(NoteDto note, String token) throws TodoException;

	/**
	 * @param userId
	 * @param noteId
	 * @param newTitle
	 * @param newDescription
	 * @throws TodoException
	 *             <p>
	 *             <b>method to update note</b>
	 *             </p>
	 */
	public void updateNote(String noteId, NoteDto note, String token) throws TodoException;

	/**
	 * @param userId
	 * @param noteId
	 * @throws TodoException
	 *             <p>
	 *             <b>method to delete note</b>
	 *             </p>
	 */
	public void deleteNote(String noteId, String token) throws TodoException;

	/**
	 * @param noteId
	 * @param token
	 * @throws TodoException
	 *             <p>
	 *             <b>This method is used to get the data from trash to database</b>
	 *             </p>
	 */
	void restoreFromTrash(String noteId, String token) throws TodoException;

	/**
	 * @param noteId
	 * @param token
	 * @throws TodoException
	 *             <p>
	 *             <b>Method to send the data from database to trash</b>
	 *             </p>
	 */
	void deleteTotrash(String noteId, String token) throws TodoException;

	/**
	 * @param token
	 * @return
	 * @throws TodoException
	 *             <p>
	 *             <b>Display all the notes</b>
	 *             </p>
	 */
	List<Note> display(String token) throws TodoException;

	/**
	 * @param noteId
	 * @param token
	 * @throws TodoException
	 *             <p>
	 *             <b>Making a note as important</b>
	 *             </p>
	 */
	void pinNote(String noteId, String token) throws TodoException;

	/**
	 * @param labelDto
	 * @param token
	 * @throws TodoException
	 *             <p>
	 *             <b>Creating a label</b>
	 *             </p>
	 */
	void createLabel(LabelDTO labelDto, String token) throws TodoException;

	/**
	 * @param noteId
	 * @param token
	 * @throws TodoException
	 */
	void archieve(String noteId, String token) throws TodoException;

	/**
	 * @param token
	 * @param id
	 * @param reminderTime
	 * @return
	 * @throws Exception
	 */
	Note setReminder(String token, String id, String reminderTime) throws Exception;

	/**
	 * @param labelName
	 * @param token
	 * @throws TodoException
	 */
	void deleteLabel(String labelName, String token) throws TodoException;

	/**
	 * @param id
	 * @param label
	 * @param token
	 * @throws TodoException
	 */
	void updateLabel(String id, LabelDTO label, String token) throws TodoException;

	/**
	 * @param token1
	 * @return
	 * @throws TodoException
	 *             <p>
	 *             <b>Displaying List of labels</b>
	 *             </p>
	 */
	List<Label> displayLabels(String token1) throws TodoException;

	/**
	 * @param note
	 * @param id
	 * @param token
	 * @throws TodoException
	 *             <p>
	 *             <b>Adding label to note</b>
	 *             </p>
	 */
	void addLabelToNote(String note, String id, String token) throws TodoException;

	/**
	 * @param noteId
	 * @param token
	 * @throws TodoException
	 *             <p>
	 *             <b>Making a note as unimportant</b>
	 *             </p>
	 */
	void unpinNote(String noteId, String token) throws TodoException;

	/**
	 * @param labelName
	 * @param token
	 * @throws TodoException
	 *             <p>
	 *             <b>Method to remove label from note and label</b>
	 *             </p>
	 */
	void removeLabel(String labelName, String token) throws TodoException;

	/**
	 * @param labelName
	 * @param token
	 * @throws TodoException
	 *             <p>
	 *             <b>Method to remove label from note</b>
	 *             </p>
	 */
	void removeLabelfromNote(String labelName, String token) throws TodoException;

	/**
	 * @param labelId
	 * @param token
	 * @param newLabelName
	 * @throws ToDoException
	 *             <p>
	 *             <b>Method to rename a label in note as well as from label
	 *             table</b>
	 *             </p>
	 */
	void renameLabel(String labelname, String token, String newLabelName) throws TodoException;

}
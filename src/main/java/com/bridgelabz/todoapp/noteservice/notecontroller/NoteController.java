
package com.bridgelabz.todoapp.noteservice.notecontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.todoapp.noteservice.notemodel.LabelDTO;
import com.bridgelabz.todoapp.noteservice.notemodel.Note;
import com.bridgelabz.todoapp.noteservice.notemodel.NoteDto;
import com.bridgelabz.todoapp.noteservice.noteservice.INoteService;
import com.bridgelabz.todoapp.utilservice.Utility;
import com.bridgelabz.todoapp.utilservice.Exception.TodoException;

/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>Controller class .</b>
 *        </p>
 */
@RestController
@RequestMapping(value = "/note")
public class NoteController {
	public static final Logger LOG = LoggerFactory.getLogger(NoteController.class);
	@Autowired
	private INoteService noteService;
	@SuppressWarnings("unused")
	@Autowired
	private Utility util;
	final String REQ_ID = "IN_NOTE";
	final String REs_ID = "OUT_NOTE";

	/********************************************************************************************************
	 * @param note
	 * @param token
	 * @return
	 *         <p>
	 *         <b>Creates a new note</b>
	 *         </p>
	 * @throws TodoException
	 ********************************************************************************************************/
	@PostMapping(value = "/createnote")
	public ResponseEntity<String> createNote(@RequestBody NoteDto note,@RequestHeader("token") String token,HttpServletRequest request)
			throws TodoException {
		LOG.debug("Creating a note");
		LOG.info(REQ_ID);
		LOG.info("Creating note");
		String userId=(String) request.getAttribute("userId");
		System.out.println(userId);
		noteService.createNote(note, userId);
		LOG.info(REs_ID);
		LOG.info("created note successfully");
		return new ResponseEntity<>("Note created Successfully", HttpStatus.OK);
	}

	/*********************************************************************************************************
	 * @param noteId
	 * @param token
	 * @return
	 *         <p>
	 *         <b>Deleting a note</b>
	 *         </p>
	 * @throws TodoException
	 *********************************************************************************************************/
	@DeleteMapping(value = "/deletenote")
	public ResponseEntity<String> deleteNote(@RequestParam("noteId") String noteId,
			@RequestHeader("token") String token,HttpServletRequest request) throws TodoException {
		LOG.debug("Deleting a Note");
		LOG.info(REQ_ID);
		LOG.info("Deleting note");
		String userId=(String) request.getAttribute("userId");
		noteService.deleteNote(noteId, userId);
		LOG.info(REs_ID);
		LOG.info("Deleted Note successfully");
		return new ResponseEntity<>("Note deleted Successfully", HttpStatus.OK);
	}

	/********************************************************************************************************
	 * @param noteId
	 * @param note
	 * @param token
	 * @return
	 *         <p>
	 *         <b>Update a note</b>
	 *         </p>
	 * @throws TodoException
	 ********************************************************************************************************/
	@PutMapping(value = "/updatenote")
	public ResponseEntity<String> updateNote(@RequestParam("noteId") String noteId, @RequestBody NoteDto note,
			@RequestHeader("token") String token,HttpServletRequest request) throws TodoException {
		LOG.debug("Updating note");
		LOG.info(REQ_ID);
		LOG.info("Updating note");
		String userId=(String) request.getAttribute("userId");
		noteService.updateNote(noteId, note, userId);
		LOG.info(REs_ID);
		LOG.info("Updated note successfully");
		return new ResponseEntity<>("Note Updated Successfully", HttpStatus.OK);
	}

	/********************************************************************************************************
	 * @param token
	 * @return
	 *         <p>
	 *         <b>displays list of notes</b>
	 *         </p>
	 * @throws TodoException
	 ********************************************************************************************************/
	@GetMapping(value = "/displaynote")
	public ResponseEntity<List<Note>> displayNote(@RequestHeader("token") String token,HttpServletRequest request) throws TodoException {
		LOG.debug("displying note");
		LOG.info(REQ_ID);
		LOG.info("displying note");
		String userId=(String) request.getAttribute("userId");
		List note = null;
		note = noteService.display(userId);
		LOG.info(REs_ID);
		LOG.info("displayed note successfully");
		return new ResponseEntity<>(note, HttpStatus.OK);
	}

	/*********************************************************************************************************
	 * @param noteId
	 * @param token
	 * @return
	 * @throws TodoException
	 *             <p>
	 *             <b>Method to send the data from database to trash</b>
	 *             </p>
	 *********************************************************************************************************/
	@DeleteMapping(value = "/trashnote")
	public ResponseEntity<String> trashNote(@RequestParam("noteId") String noteId, @RequestHeader("token") String token,HttpServletRequest request)
			throws TodoException {
		LOG.debug("Deleting note");
		LOG.info(REQ_ID);
		LOG.info("Deleting note");
		String userId=(String) request.getAttribute("userId");
		noteService.deleteTotrash(noteId, userId);
		LOG.info(REs_ID);
		LOG.info("Deleted Note successfully");
		return new ResponseEntity<>("Note deleted Successfully", HttpStatus.OK);
	}

	/********************************************************************************************************
	 * @param noteId
	 * @param token
	 * @return
	 * @throws TodoException
	 *             <p>
	 *             <b>This method is used to get the data from trash to database</b>
	 *             </p>
	 ********************************************************************************************************/
	@DeleteMapping(value = "/restoretrashnote")
	public ResponseEntity<String> restoreTrashNote(@RequestParam("noteId") String noteId,
			@RequestParam("token") String token,HttpServletRequest request) throws TodoException {
		LOG.info(REQ_ID);
		LOG.info("getting back deleted note");
		String userId=(String) request.getAttribute("userId");
		noteService.restoreFromTrash(noteId, userId);
		LOG.info(REs_ID);
		LOG.info("Got back deleted note successfully");
		return new ResponseEntity<>("Note deleted Successfully", HttpStatus.OK);
	}

	/********************************************************************************************************
	 * @param noteId
	 * @param token
	 * @return
	 * @throws TodoException
	 *             <p>
	 *             <b>Method to make note as important</b>
	 *             </p>
	 ********************************************************************************************************/
	@PutMapping(value = "/pinnote")
	public ResponseEntity<String> pinNote(@RequestParam("noteId") String noteId, @RequestParam("token") String token,HttpServletRequest request)
			throws TodoException {
		LOG.info(REQ_ID);
		LOG.info("making note pinned");
		String userId=(String) request.getAttribute("userId");
		noteService.pinNote(noteId, userId);
		LOG.info(REs_ID);
		LOG.info("pinned a note");
		return new ResponseEntity<>("pinned a note", HttpStatus.OK);
	}

	/********************************************************************************************************
	 * @param noteId
	 * @param token
	 * @return
	 * @throws TodoException
	 *             <p>
	 *             <b>Method to make note as unimportant</b>
	 *             </p>
	 ********************************************************************************************************/
	@PutMapping(value = "/unpinnote")
	public ResponseEntity<String> unpinNote(@RequestParam("noteId") String noteId, @RequestParam("token") String token,HttpServletRequest request )
			throws TodoException {
		LOG.info(REQ_ID);
		LOG.info("making note unpinned");
		String userId=(String) request.getAttribute("userId");
		noteService.pinNote(noteId, userId);
		LOG.info(REs_ID);
		LOG.info("made a note as unimportant");
		return new ResponseEntity<>("made a note as unimportant", HttpStatus.OK);
	}

	/********************************************************************************************************
	 * @param noteId
	 * @param token
	 * @return
	 * @throws TodoException
	 ********************************************************************************************************/
	@PutMapping(value = "/archievenote")
	public ResponseEntity<String> archieveNote(@RequestParam("noteId") String noteId,
			@RequestParam("token") String token,HttpServletRequest request) throws TodoException {
		LOG.info(REQ_ID);
		LOG.info("making note archieve");
		String userId=(String) request.getAttribute("userId");
		noteService.archieve(noteId, userId);
		LOG.info(REs_ID);
		LOG.info("archieved a note");
		return new ResponseEntity<>("archieved a note", HttpStatus.OK);
	}

	/********************************************************************************************************
	 * @param token
	 * @param id
	 * @param reminderTime
	 * @return
	 * @throws Exception
	 *             <p>
	 *             <b>Method to remind a note</b>
	 *             </p>
	 ********************************************************************************************************/
	@PutMapping(value = "/remindnote")
	public ResponseEntity<String> remindNote(@RequestHeader("token") String token, @RequestParam("noteId") String id,
			@RequestParam("remindAt") String reminderTime,HttpServletRequest request) throws Exception {
		LOG.info(REQ_ID);
		LOG.info("making note to remind");
		String userId=(String) request.getAttribute("userId");
		noteService.setReminder(userId, id, reminderTime);
		LOG.info(REs_ID);
		LOG.info("made a note to remind");
		return new ResponseEntity<>("remind time", HttpStatus.OK);
	}

	/********************************************************************************************************
	 * @param labelDto
	 * @param token
	 * @return
	 * @throws TodoException
	 *             <p>
	 *             <b>Method to create a label</b>
	 *             </p>
	 ********************************************************************************************************/
	@PostMapping(value = "/createlabel")
	public ResponseEntity<String> createLabel(@RequestBody LabelDTO labelDto, @RequestHeader("token") String token,HttpServletRequest request)
			throws TodoException {
		LOG.debug("creating a label");
		LOG.info(REQ_ID);
		LOG.info("creating a label");
		String userId=(String) request.getAttribute("userId");
		noteService.createLabel(labelDto, userId);
		LOG.info(REs_ID);
		LOG.info("created a label");
		return new ResponseEntity<>("created a label", HttpStatus.OK);
	}

	/********************************************************************************************************
	 * @param labelName
	 * @param token
	 * @return
	 * @throws TodoException
	 *             <p>
	 *             <b>Method to delete label</b>
	 *             </p>
	 ********************************************************************************************************/
	@DeleteMapping(value = "/deletelabel")
	public ResponseEntity<String> deleteLabel(@RequestParam("labelName") String labelName, @RequestHeader("token") String token,HttpServletRequest request)
			throws TodoException {
		LOG.debug("Deleting label");
		LOG.info(REQ_ID);
		LOG.info("Deleting label");
		String userId=(String) request.getAttribute("userId");
		noteService.deleteLabel(labelName, userId);
		LOG.info(REs_ID);
		LOG.info("Deleted label successfully");
		return new ResponseEntity<>("label deleted Successfully", HttpStatus.OK);
	}

	/********************************************************************************************************
	 * @param labelId
	 * @param labelDto
	 * @param token
	 * @return
	 * @throws TodoException
	 *             <p>
	 *             <b>Method to update a label</b>
	 *             </p>
	 ********************************************************************************************************/
	@PutMapping(value = "/updatelabel")
	public ResponseEntity<String> updateLabel(@RequestParam("labelId") String labelId, @RequestBody LabelDTO labelDto,
			@RequestHeader String token) throws TodoException {
		LOG.debug("Updating label");
		LOG.info(REQ_ID);
		LOG.info("Updating label");
		LOG.warn("warn message for label updation");
		LOG.error("an error occured when  label is updating");
		noteService.updateLabel(labelId, labelDto, token);
		LOG.info(REs_ID);
		LOG.info("Updated label successfully");
		return new ResponseEntity<>("label Updated Successfully", HttpStatus.OK);
	}

	/********************************************************************************************************
	 * @param token
	 * @return
	 * @throws TodoException
	 *             <p>
	 *             <b>Method to display list of labels</b>
	 *             </p>
	 ********************************************************************************************************/
	@GetMapping(value = "/displaylabel")
	public ResponseEntity displayLabel(@RequestHeader("token") String token,HttpServletRequest request) throws TodoException {
		LOG.debug("displaying label");
		LOG.info(REQ_ID);
		LOG.info("displaying label");
		String userId=(String) request.getAttribute("userId");
		List label = null;
		label = noteService.displayLabels(userId);
		LOG.info(REs_ID);
		LOG.info("displayed label successfully");
		return new ResponseEntity<>(label, HttpStatus.OK);
	}

	/********************************************************************************************************
	 * @param noteId
	 * @param id
	 * @param token
	 * @return
	 * @throws TodoException
	 *             <p>
	 *             <b>Method to add a label to note</b>
	 *             </p>
	 ********************************************************************************************************/
	@PostMapping(value = "/addlabeltonote")
	public ResponseEntity<String> addLabelToNote(@RequestParam("noteId") String noteId,
			@RequestParam("labelName") String labelName, @RequestHeader("token") String token,HttpServletRequest request) throws TodoException {
		LOG.debug("adding a label to note");
		LOG.info(REQ_ID);
		LOG.info("adding a label to note");
		String userId=(String) request.getAttribute("userId");
		noteService.addLabelToNote(noteId, labelName, userId);
		LOG.info(REs_ID);
		LOG.info("added a label");
		return new ResponseEntity<>("added a label", HttpStatus.OK);
	}

	/*******************************************************************************************************
	 * @param noteId
	 * @param labelName
	 * @param token
	 * @return
	 * @throws TodoException
	 *             <p>
	 *             <b>Method to remove label from note and label</b>
	 *             </p>
	 *******************************************************************************************************/
	@DeleteMapping(value = "/removelabel")
	public ResponseEntity<String> removelabel(@RequestParam("labelName") String labelName, @RequestHeader("token") String token,HttpServletRequest request)
			throws TodoException {
		LOG.debug("removing  a label from note and label table");
		LOG.info(REQ_ID);
		LOG.info("removing  a label from note");
		String userId=(String) request.getAttribute("userId");
		noteService.removeLabel(labelName, userId);
		LOG.info(REs_ID);
		LOG.info("removed a label");
		return new ResponseEntity<>("removed a label", HttpStatus.OK);
	}

	/******************************************************************************************************
	 * @param noteId
	 * @param labelName
	 * @param token
	 * @return
	 * @throws TodoException
	 *             <p>
	 *             <b>Method to remove label from note</b>
	 *             </p>
	 ******************************************************************************************************/
	@DeleteMapping(value = "/removelabelfromnote")
	public ResponseEntity<String> removelabelFromNote(@RequestParam("labelName") String labelName,
			@RequestHeader("token") String token,HttpServletRequest request) throws TodoException {
		LOG.debug("removing  a label from note");
		LOG.info(REQ_ID);
		LOG.info("removing  a label from note");
		String userId=(String) request.getAttribute("userId");
		noteService.removeLabelfromNote(labelName, userId);
		LOG.info(REs_ID);
		LOG.info("removed a label");
		return new ResponseEntity<>("removed a label", HttpStatus.OK);
	}

	/*******************************************************************************************************
	 * @param labelname
	 * @param token
	 * @param newLabelName
	 * @return
	 * @throws TodoException
	 *             <p>
	 *             <b>Method to rename label </b>
	 *             </p>
	 *******************************************************************************************************/
	@PutMapping(value = "/renamelabel")
	public ResponseEntity<String> renamelabel(@RequestParam("labelname") String labelname, @RequestHeader("token") String token,
			@RequestParam("renamelabelName") String newLabelName,HttpServletRequest request) throws TodoException {
		LOG.debug("renaming  a label from note and label table");
		LOG.info(REQ_ID);
		LOG.info("renaming a label from note");
		String userId=(String) request.getAttribute("userId");
		noteService.renameLabel(labelname, token, newLabelName);
		LOG.info(REs_ID);
		LOG.info("renamed a label");
		return new ResponseEntity<>("renamed a label", HttpStatus.OK);
	}
}
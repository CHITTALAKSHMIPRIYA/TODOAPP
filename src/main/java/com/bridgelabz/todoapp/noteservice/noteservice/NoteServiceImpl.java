
package com.bridgelabz.todoapp.noteservice.noteservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.todoapp.noteservice.notemodel.Label;
import com.bridgelabz.todoapp.noteservice.notemodel.LabelDTO;
import com.bridgelabz.todoapp.noteservice.notemodel.Note;
import com.bridgelabz.todoapp.noteservice.notemodel.NoteDto;
import com.bridgelabz.todoapp.noteservice.noterepository.IElasticRepository;
import com.bridgelabz.todoapp.noteservice.noterepository.ILabel;
import com.bridgelabz.todoapp.noteservice.noterepository.ILabelElasticRepository;
import com.bridgelabz.todoapp.noteservice.noterepository.INoteDao;

import com.bridgelabz.todoapp.userservice.usermodel.User;
import com.bridgelabz.todoapp.userservice.userrepository.IUserDAO;
import com.bridgelabz.todoapp.utilservice.Messages;
import com.bridgelabz.todoapp.utilservice.ModelMapperService;
import com.bridgelabz.todoapp.utilservice.RestPreCondition;
import com.bridgelabz.todoapp.utilservice.TokenParseInterceptor;
import com.bridgelabz.todoapp.utilservice.Utility;
import com.bridgelabz.todoapp.utilservice.Exception.TodoException;
import io.jsonwebtoken.Claims;

/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>Note Service class implementing interface note service.</b>
 *        </p>
 */
@Service
public class NoteServiceImpl implements INoteService {
	@Autowired
	private INoteDao dao;
	@Autowired
	private IUserDAO userdao;
	@Autowired
	Utility util;
	@Autowired
	ModelMapperService model;
	@Autowired
	private ILabel ilabel;
	@Autowired
	Messages messages;
	@Autowired
	RestPreCondition restprecondition;
	@Autowired
	IElasticRepository elasticrepo;
	@Autowired
	ILabelElasticRepository labelelasticrepo;

	@Autowired
	TokenParseInterceptor tokenParseInterceptor;

	public static final Logger LOG = LoggerFactory.getLogger(NoteServiceImpl.class);

	/******************************************************************************************************
	 * @param title
	 * @param description
	 * @param authorId
	 * @param id
	 *            <p>
	 *            <b> Method to create a new note</b>
	 *            </p>
	 * @throws TodoException
	 ******************************************************************************************************/
	public void createNote(NoteDto note, String userId) throws TodoException {
		restprecondition.checkNotNull(note.getDescription(), messages.get("100"));
		Optional<User> user = userdao.findByEmail(userId);
		Optional<Note> noteEl=elasticrepo.findById(userId);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String createdDate = formatter.format(new Date());
		Note note1 = model.map(note, Note.class);
		note1.setUser(user.get().getId());
		note1.setCreatedAt(createdDate);
		note1.setUpdatedAt(createdDate);
		dao.save(note1);
		elasticrepo.save(note1);
	}

	/****************************************************************************************************
	 * @param noteId
	 * @param note
	 * @param token
	 * @return
	 *         <p>
	 *         <b>Update a note</b>
	 *         </p>
	 * @throws TodoException
	 *****************************************************************************************************/
	@Override
	public void updateNote(String noteId, NoteDto note, String userId) throws TodoException {
		restprecondition.checkNotNull(note.getDescription(), messages.get("100"));
		restprecondition.checkArgument(elasticrepo.existsById(noteId), messages.get("101"));
		Optional<Note> note1 = elasticrepo.findById(noteId);
		Optional<User> user = userdao.findByEmail(userId);
		Note note2 = model.map(note, Note.class);
		note2.setNote(noteId);
		note2.setUser(user.get().getId());
		note2.setCreatedAt(note1.get().getCreatedAt());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		note2.setUpdatedAt(formatter.format(new Date()));
		dao.save(note2);
		elasticrepo.save(note2);

	}

	/*****************************************************************************************************
	 * @param noteId
	 * @param token
	 * @return
	 *         <p>
	 *         <b>Deleting a note</b>
	 *         </p>
	 * @throws TodoException
	 ****************************************************************************************************/
	@Override
	public void deleteNote(String noteId, String userId) throws TodoException {
		restprecondition.checkNotNull(noteId, messages.get("102"));
		restprecondition.checkArgument(elasticrepo.existsById(noteId), messages.get("101"));
		dao.deleteById(noteId);
		elasticrepo.deleteById(noteId);
	}

	/****************************************************************************************************
	 * @param noteId
	 * @param token
	 * @throws TodoException
	 *             <p>
	 *             <b>This method is used to get the data from trash to database</b>
	 *             </p>
	 *****************************************************************************************************/
	@Override
	public void restoreFromTrash(String noteId, String userId) throws TodoException {
		restprecondition.checkNotNull(noteId, messages.get("102"));
		restprecondition.checkArgument(elasticrepo.existsById(noteId), messages.get("101"));
		Note note = dao.findById(noteId).get();
		if (note.isTrashed()) {
			note.setTrashed(false);
			dao.save(note);
			elasticrepo.save(note);
		}
	}

	/******************************************************************************************************
	 * @param noteId
	 * @param token
	 * @throws TodoException
	 *             <p>
	 *             <b>Method to send the data from database to trash</b>
	 *             </p>
	 ******************************************************************************************************/
	@Override
	public void deleteTotrash(String noteId, String userId) throws TodoException {
		restprecondition.checkNotNull(noteId, messages.get("102"));
		restprecondition.checkArgument(elasticrepo.existsById(noteId), messages.get("101"));
		Note note = elasticrepo.findById(noteId).get();
		note.setTrashed(true);
		dao.save(note);
		elasticrepo.save(note);
	}

	/****************************************************************************************************
	 * @param noteId
	 * @param token
	 * @throws TodoException
	 *             <p>
	 *             <b>making a note as important</b>
	 *             </p>
	 ****************************************************************************************************/
	@Override
	public void pinNote(String noteId, String userId) throws TodoException {
		restprecondition.checkNotNull(noteId, messages.get("102"));
		restprecondition.checkArgument(elasticrepo.existsById(noteId), messages.get("101"));
		Note note = dao.findById(noteId).get();
		if (!note.isTrashed()) {
			note.setPin(true);
			dao.save(note);
			elasticrepo.save(note);
		}
	}

	/****************************************************************************************************
	 * @param noteId
	 * @param token
	 * @throws TodoException
	 *             <p>
	 *             <b>making a note unimportant</b>
	 *             </p>
	 ****************************************************************************************************/
	@Override
	public void unpinNote(String noteId, String userId) throws TodoException {
		restprecondition.checkNotNull(noteId, messages.get("102"));
		restprecondition.checkArgument(elasticrepo.existsById(noteId), messages.get("101"));
		Note note = dao.findById(noteId).get();
		if (!note.isTrashed()) {
			note.setPin(false);
			dao.save(note);
			elasticrepo.save(note);
		}
	}

	/***************************************************************************************************
	 * @param noteId
	 * @param token
	 * @throws TodoException
	 ***************************************************************************************************/
	@Override
	public void archieve(String noteId, String userId) throws TodoException {
		restprecondition.checkNotNull(noteId, messages.get("102"));
		restprecondition.checkArgument(elasticrepo.existsById(noteId), messages.get("101"));
		Note note = elasticrepo.findById(noteId).get();
		if (!note.isTrashed()) {
			note.setArchieve(true);
			dao.save(note);
			elasticrepo.save(note);
		}
	}

	/*****************************************************************************************************
	 * @param token
	 * @param id
	 * @param reminderTime
	 * @return
	 * @throws Exception
	 *             <p>
	 *             <b>making a note to remind</b>
	 *             </p>
	 *****************************************************************************************************/
	@Override
	public Note setReminder(String token, String id, String reminderTime) throws TodoException, ParseException {

		Optional<Note> note = restprecondition.checkNotNull(elasticrepo.findById(id), messages.get("102"));
		Timer timer = null;
		if (note.isPresent()) {
			Date reminder = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(reminderTime);
			long timeDifference = reminder.getTime() - new Date().getTime();
			timer = new Timer();
			Claims claim = util.parseJwt(token);
			Optional<User> optionalUser = userdao.findByEmail(claim.getId());
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					System.out.println("Reminder task:" + note.toString());
				}
			}, timeDifference);
		}
		return note.get();
	}

	/***************************************************************************************************
	 * @param token
	 * @return
	 * @throws TodoException
	 *             <p>
	 *             <b>Display all the notes</b>
	 *             </p>
	 ****************************************************************************************************/
	@Override
	public List<Note> display(String userId) throws TodoException {
		List<Note> list = new ArrayList<>();
		List<Note> notelist=elasticrepo.findByUser(userId);
	Optional<User> user = userdao.findByEmail(userId);
		list = dao.findAll();
		
		return list;
	}

	/*****************************************************************************************************
	 * @param labelDto
	 * @param token
	 * @throws TodoException
	 *             <p>
	 *             <b>Creating a label</b>
	 *             </p>
	 *****************************************************************************************************/
	@Override
	public void createLabel(LabelDTO labelDto, String userId) throws TodoException {
		restprecondition.checkNotNull(labelDto.getLabelName(), messages.get("110"));
		Optional<User> user = userdao.findByEmail(userId);
		Label label = model.map(labelDto, Label.class);
		label.setUser(user.get().getId());
		ilabel.save(label);
		labelelasticrepo.save(label);
	}

	/*************************************************************************************************
	 * @param labelName
	 * @param token
	 * @throws TodoException
	 *             <p>
	 *             <b>Deleting a label</b>
	 *             </p>
	 *************************************************************************************************/
	@Override
	public void deleteLabel(String labelName, String userId) throws TodoException {
		// Claims claim = util.parseJwt(token);
		restprecondition.checkNotNull(labelName, messages.get("110"));
		// restprecondition.checkNotNull(token, messages.get("103"));
		ilabel.deleteByLabelName(labelName);
	}

	/**************************************************************************************************
	 * @param id
	 * @param label
	 * @param token
	 * @throws TodoException
	 *             <p>
	 *             <b>updating a label</b>
	 *             </p>
	 **************************************************************************************************/
	@Override
	public void updateLabel(String id, LabelDTO label, String token) throws TodoException {
		restprecondition.checkNotNull(label.getLabelName(), messages.get("110"));
		restprecondition.checkNotNull(token, messages.get("103"));
		restprecondition.checkArgument(ilabel.existsById(id), messages.get("111"));

		Optional<Label> label1 = ilabel.findById(id);
		Label label2 = model.map(label, Label.class);
		label2.setId(label1.get().getId());
		label2.setUser(label1.get().getUser());
		ilabel.save(label2);

	}

	/***************************************************************************************************
	 * @param token1
	 * @return
	 * @throws TodoException
	 *             <p>
	 *             <b>displaying a list of labels</b>
	 *             </p>
	 ***************************************************************************************************/
	@Override
	public List<Label> displayLabels(String userId) throws TodoException {
		List<Label> list = new ArrayList<>();
		Optional<User> user = userdao.findByEmail(userId);
		list = ilabel.findAll();
		return list;
	}

	/****************************************************************************************************
	 * @param note
	 * @param id
	 * @param token
	 * @throws TodoException
	 *             <p>
	 *             <b>Adding label to note</b>
	 *             </p>
	 ****************************************************************************************************/
	@Override
	public void addLabelToNote(String note, String labelName, String userId) throws TodoException {
		Optional<User> user = userdao.findByEmail(userId);
		Optional<Note> optionalNote = elasticrepo.findById(note);
		List<Note> listOfNote = dao.findAll();
		System.out.println(listOfNote);
		LabelDTO label = new LabelDTO();
		restprecondition.checkArgument(elasticrepo.existsById(note), messages.get("101"));
		for (Note n : listOfNote) {
			if (n.getNote().equals(note)) {
				label.setLabelName(labelName);
				Label labelmap = model.map(label, Label.class);
				ilabel.save(labelmap);
				Note noteLabel = model.map(label, Note.class);
				n.getLabel().add(label);
				dao.save(n);
				elasticrepo.save(n);
			}
		}
	}

	/*************************************************************************************************
	 * @param labelName
	 * @param token
	 * @throws TodoException
	 *             <p>
	 *             <b>Method to remove label from note and label</b>
	 *             </p>
	 *************************************************************************************************/
	@Override
	public void removeLabel(String labelName, String userId) throws TodoException {
		restprecondition.checkNotNull(labelelasticrepo.findByLabelName(labelName), messages.get("112"));
		Optional<Label> labelFound = labelelasticrepo.findById(labelName);
		ilabel.deleteByLabelName(labelName);
		List<Note> notes = dao.findAll();
		for (int i = 0; i < notes.size(); i++) {

			for (int j = 0; j < notes.get(i).getLabel().size(); j++) {
				if (labelFound == null) {
					LOG.error(messages.get("111"));
					throw new TodoException(messages.get("111"));
				}

				if (labelName.equals(notes.get(i).getLabel().get(j).getLabelName())) {
					notes.get(i).getLabel().remove(j);
					Note note1 = notes.get(i);
					dao.save(note1);
					elasticrepo.save(note1);
					break;
				}
			}
		}
	}

	/***************************************************************************************************
	 * @param labelName
	 * @param token
	 * @throws TodoException
	 *             <p>
	 *             <b>Method to remove label from note </b>
	 *             </p>
	 ***************************************************************************************************/

	@Override
	public void removeLabelfromNote(String labelName, String userId) throws TodoException {
		restprecondition.checkNotNull(ilabel.findByLabelName(labelName), messages.get("112"));
		List<Note> notes = dao.findAll();
		for (int i = 0; i < notes.size(); i++) {
			for (int j = 0; j < notes.get(i).getLabel().size(); j++) {
				if (labelName.equals(notes.get(i).getLabel().get(j).getLabelName())) {
					notes.get(i).getLabel().remove(j);
					Note note1 = notes.get(i);
					dao.save(note1);
					break;
				}
			}
		}
	}

	/**************************************************************************************************
	 * @param labelId
	 * @param token
	 * @param newLabelName
	 * @throws ToDoException
	 *             <p>
	 *             <b>Method to rename a label in note as well as from label
	 *             table</b>
	 *             </p>
	 ***************************************************************************************************/
	@Override
	public void renameLabel(String labelname, String userId, String newLabelName) throws TodoException {
		Optional<User> user = userdao.findByEmail(userId);
		restprecondition.checkNotNull(ilabel.findByLabelName(labelname), messages.get("112"));
		Label labelFound = ilabel.findByLabelName(labelname);
		labelFound.setLabelName(newLabelName);
		ilabel.save(labelFound);
		List<Note> notes = dao.findAll();
		for (int i = 0; i < notes.size(); i++) {
			for (int j = 0; j < notes.get(i).getLabel().size(); j++) {

				if (labelname.equals(notes.get(i).getLabel().get(j).getLabelName())) {
					notes.get(i).getLabel().get(j).setLabelName(newLabelName);
					Note note = notes.get(i);
					dao.save(note);
					break;
				}

			}
		}
	}
}


package com.bridgelabz.todoapp.noteservice.noterepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.bridgelabz.todoapp.noteservice.notemodel.Note;
import com.bridgelabz.todoapp.userservice.usermodel.User;

/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>A POJO class with the user details.</b>
 *        </p>
 */

public interface IElasticRepository extends ElasticsearchRepository<Note, String>{
	/**
	 * @param id
	 * @return
	 */
	Note findByNote(String id);

	/**
	 * @param string
	 * @return
	 */
	public List<Note> findByUser(String string);

	/**
	 * @param user
	 * @param labelName
	 * @return
	 */
	List<Note> findByUser(Optional<User> user, String labelName);
}

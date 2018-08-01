/**
 * 
 */
package com.bridgelabz.todoapp.noteservice.noterepository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.bridgelabz.todoapp.noteservice.notemodel.Label;
import com.bridgelabz.todoapp.noteservice.notemodel.LabelDTO;
import com.bridgelabz.todoapp.noteservice.notemodel.Note;

/**
 * @author LAKSHMI PRIYA
 * @since DATE:10-07-2018
 *        <p>
 *        <b>A POJO class with the user details.</b>
 *        </p>
 */

public interface ILabelElasticRepository extends ElasticsearchRepository<Label, String>{
	void deleteByLabelName(String labelName);

	/**
	 * @param label
	 */
	void save(LabelDTO label);
	public Label findByLabelName(String labelname); 
}

package com.kevinchard.postit.dao;

import java.util.List;

import com.kevinchard.postit.model.Note;


public interface NoteDao {

	List<Note> getAll();

	Note get(Long id); 

	Long create(Note entity);

	void update(Note entity);

	boolean delete(Long id);
}

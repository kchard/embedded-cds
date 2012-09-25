package com.kevinchard.postit.service;

import java.util.List;

import com.kevinchard.postit.dao.NoteDao;
import com.kevinchard.postit.model.Note;

public class NoteServiceImpl implements NoteService {

	private final NoteDao dao;
	
	public NoteServiceImpl(NoteDao dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Note> getNotes() {
		return dao.getAll();
	}

	@Override
	public Note getNote(Long id) {
		return dao.get(id);
	}

	@Override
	public Note createNote(Note note) {
		note.setId(dao.create(note));
		return note;
	}

	@Override
	public Note updateNote(Note note) {
		
		dao.update(note);
		
		return note;
	}

	@Override
	public void deleteNote(long id) {
		dao.delete(id);
	}

}

package com.kevinchard.postit.service;

import java.util.List;

import com.kevinchard.postit.model.Note;

public interface NoteService {

	List<Note> getNotes();

	Note getNote(Long id);

	Note createNote(Note note);

	Note updateNote(Note note);

	void deleteNote(long id);
}

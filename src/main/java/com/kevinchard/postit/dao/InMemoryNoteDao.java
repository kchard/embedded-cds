package com.kevinchard.postit.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.kevinchard.dao.EntityDoesNotExistException;
import com.kevinchard.postit.model.Note;

public class InMemoryNoteDao implements NoteDao {

	private final Map<Long, Note> store;
	private Long currId = 1L;
	
	
	public InMemoryNoteDao() {
		this.store = new LinkedHashMap<Long, Note>();
	}
	
	@Override
	public List<Note> getAll() {
		return new ArrayList<Note>(store.values());
	}

	@Override
	public Note get(Long id) {
		return store.get(id);
	}

	@Override
	public Long create(Note entity) {
		entity.setId(getNextId());
		store.put(entity.getId(), entity);
		
		return entity.getId();
	}

	@Override
	public void update(Note entity) {
		if(!store.containsKey(entity.getId())) {
			throw new EntityDoesNotExistException(entity.getId()); 
		}
		store.put(entity.getId(), entity);
	}

	@Override
	public boolean delete(Long id) {
		
		return store.remove(id) != null;
	}

	private Long getNextId() {		
		return currId++;
	}
}

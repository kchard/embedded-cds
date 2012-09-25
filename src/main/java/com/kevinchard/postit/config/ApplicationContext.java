package com.kevinchard.postit.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.kevinchard.postit.dao.InMemoryNoteDao;
import com.kevinchard.postit.dao.NoteDao;
import com.kevinchard.postit.service.NoteService;
import com.kevinchard.postit.service.NoteServiceImpl;
import com.kevinchard.postit.web.controller.NoteController;

@Configuration
public class ApplicationContext {

	@Bean
	public NoteDao noteDao() {
		return new InMemoryNoteDao();
	}
	
	@Bean
	public NoteService noteService() {
		return new NoteServiceImpl(noteDao());
	}
	
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public NoteController noteController() {
		return new NoteController();
	}
}

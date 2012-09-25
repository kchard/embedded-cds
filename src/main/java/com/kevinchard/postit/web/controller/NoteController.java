package com.kevinchard.postit.web.controller;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kevinchard.dao.EntityDoesNotExistException;
import com.kevinchard.postit.model.Note;
import com.kevinchard.postit.service.NoteService;
import com.sun.jersey.api.NotFoundException;

@Path("/note")
@Component
public class NoteController {

	@Autowired
	private NoteService service;

	@Context
	private UriInfo uriInfo;
	
	//curl http://localhost:8080/services/note
    @GET
    @Produces("application/json")
    public Response getNotes() {
    
    	List<Note> notes = service.getNotes();
    	if(notes == null) {
    		throw new NotFoundException();
    	}
    	
    	return Response.ok(service.getNotes()).build();
    }
    
    //curl http://localhost:8080/services/note/{id}
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getNote(@PathParam(value="id") Long id) {	
    	
    	Note note = service.getNote(id);
    	if(note == null) {
    		throw new NotFoundException();
    	}
    	
    	return Response.ok(note).build();
    }
    
    //Linux
    //curl -v -X POST -H "Content-Type: application/json" -d '{"title":"TITLE", "description":"DESC"}' http://localhost:8080/services/note
    
    //Windows
    //curl -v -X POST -H "Content-Type: application/json" -d "{\"title\":\"TITLE\", \"description\":\"DESC\"}" http://localhost:8080/services/note
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response createNote(Note note) {
    
    	Note createdNote = service.createNote(note);
    	
    	URI uri =  UriBuilder.fromPath(uriInfo.getAbsolutePath() + "/" + createdNote.getId()).build();
    	
    	return Response.created(uri).build();
    }
    
    //Linux
    //curl -v -X PUT -H "Content-Type: application/json" -d '{"id":"1", "title":"TITLE UPDATE", "description":"DESC UPDATE"}' http://localhost:8080/services/note/1
    
    //Windows
    //curl -v -X PUT -H "Content-Type: application/json" -d "{\"id\":\"1\", \"title\":\"TITLE UPDATE\", \"description\":\"DESC UPDATE\"}" http://localhost:8080/services/note/1
    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateNote(@PathParam(value="id") Long id, Note note) {
    
    	if(id != null && !id.equals(note.getId())) {
    		return Response.status(Status.BAD_REQUEST).build();
    	}
    	
    	try {
    		service.updateNote(note);
    	} catch(EntityDoesNotExistException e) {
    		return Response.status(Status.BAD_REQUEST).build();
    	}
    	
    	return Response.ok().build();
    }
    
    //curl -v -X DELETE http://localhost:8080/services/note/{id}
    @DELETE
    @Path("/{id}")
    public Response deleteNote(@PathParam(value="id") Long id) {
    
    	service.deleteNote(id);
    	
    	return Response.ok().build();
    }
}
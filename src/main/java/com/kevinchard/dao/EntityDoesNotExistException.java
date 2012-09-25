package com.kevinchard.dao;

public class EntityDoesNotExistException extends RuntimeException {

	private static final long serialVersionUID = -2171498178408242983L;

	public EntityDoesNotExistException(Long id) {
		super("No entity exists with id: " + id);
	}
}

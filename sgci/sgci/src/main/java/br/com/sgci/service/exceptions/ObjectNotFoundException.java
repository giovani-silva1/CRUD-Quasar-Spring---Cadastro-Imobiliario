package br.com.sgci.service.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String smg) {
		super(smg);
	}

}

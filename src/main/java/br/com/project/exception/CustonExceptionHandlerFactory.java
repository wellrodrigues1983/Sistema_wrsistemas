package br.com.project.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class CustonExceptionHandlerFactory extends ExceptionHandlerFactory{

	
	private ExceptionHandlerFactory parent;
	
	
	public CustonExceptionHandlerFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}	
	
	@Override
	public ExceptionHandler getExceptionHandler() {
		ExceptionHandler handler = new CustonExceptionHandler(parent.getExceptionHandler());
		return handler;
	}

}

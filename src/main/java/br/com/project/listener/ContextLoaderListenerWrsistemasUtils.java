package br.com.project.listener;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@ApplicationScoped
public class ContextLoaderListenerWrsistemasUtils extends ContextLoaderListener implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static WebApplicationContext getWac() {
		return WebApplicationContextUtils.getWebApplicationContext(getCurrentWebApplicationContext().getServletContext());
	}
	
	public static Object getBean(String idNomebean) {		
		return getWac().getBean(idNomebean);		
	}
	
	public static Object getBean(Class<?> classe) {
		return getWac().getBean(classe);
	}

}

package br.com.project.exception;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.apache.commons.fileupload.RequestContext;
import org.hibernate.SessionFactory;

import br.com.framework.hibernate.session.HibernateUtil;

public class CustonExceptionHandler extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapperd;

	final FacesContext facesContext = FacesContext.getCurrentInstance();

	final Map<String, Object> requesMap = facesContext.getExternalContext().getRequestMap();

	final NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();

	public CustonExceptionHandler(ExceptionHandler exceptionHandler) {
		this.wrapperd = exceptionHandler;
	}

	// Sobrescreve o método ExceptionHandler que retorna a "pilha" de exceções
	@Override
	public ExceptionHandler getWrapped() {
		return wrapperd;
	}

	// Sobrescreve o metodo handle que é responsável por manipular as exceções do
	// jsf
	@Override
	public void handle() throws FacesException {
		final Iterator<ExceptionQueuedEvent> iterator = getUnhandledExceptionQueuedEvents().iterator();

		while (iterator.hasNext()) {
			ExceptionQueuedEvent event = iterator.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

			// Recuperar a exceção do contexto
			Throwable exception = context.getException();

			// Aqui Trabalhamos a Exceção
			try {

				requesMap.put("exceptionMessage", exception.getMessage());

				if (exception != null && exception.getMessage() != null
						&& exception.getMessage().indexOf("ConstranintViolationException") != -1) {

					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Registro Não pode ser removido por" + " estar associado.", ""));

				} else if (exception != null && exception.getMessage() != null
						&& exception.getMessage().indexOf("org.hibernate.StateObjectStateException") != -1) {

					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Registro foi atualizado ou excluido por outro usuário." + " Consulte novamente.", ""));

				} else {

					// Avisa o usuario do erro
					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"O Sistema se recuperou de um erro inesperado.", ""));

					// Tranquiliza o Usuario
					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Voce pode continuar usando o sistema normalmente.", ""));

					// Tranquiliza o Usuario
					FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"O erro foi causado por:\n" + exception.getMessage(), ""));

					// PrimeFaces
					// Esse Alert apenas é exibido se a pagina não redirecionar
					org.primefaces.context.RequestContext.getCurrentInstance()
							.execute("alert('O Sistema se recuperou de um erro inesperado.')");

					org.primefaces.context.RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(
							FacesMessage.SEVERITY_INFO, "Erro", "O Sistema se recuperou de um erro inesperado."));

					// Redireciona para Página de erro
					navigationHandler.handleNavigation(facesContext, null,
							"/error/error.jsf?faces-redirect=true&expired=true");
				}
				//Renderiza a página de erro e exibe as mensagens
				facesContext.renderResponse();

			} finally {
				SessionFactory sf = HibernateUtil.getSessionFactory();
				if (sf.getCurrentSession().getTransaction().isActive()) {
					sf.getCurrentSession().getTransaction().rollback();

				}

				// imprime o erro no console
				exception.printStackTrace();

				iterator.remove();
			}
		}

		getWrapped().handle();

	}

}

package br.com.project.util.all;

import org.springframework.stereotype.Component;

@Component
public abstract class BeanViewAbstract implements ActionViewPadrao {

	private static final long serialVersionUID = 1L;

	public void limparLista() throws Exception {

	}

	public String save() throws Exception {
		return null;
	}

	public void saveNotReturn() throws Exception {

	}

	public void saveEdit() throws Exception {

	}

	public void excluir() throws Exception {

	}

	public String ativar() throws Exception {
		return null;
	}

	public String novo() throws Exception {
		return null;
	}

	public String editar() throws Exception {
		return null;
	}

	public void setarVariaveisNulas() throws Exception {

	}

	public void consultarEntidade() throws Exception {

	}

	public void statusOperation(StatusPersistencia a) throws Exception {
		Mesagens.responseOperation(a);

	}
	
	protected void sucesso() throws Exception {
		statusOperation(StatusPersistencia.SUCESSO);
	}
	
	protected void error() throws Exception {
		statusOperation(StatusPersistencia.ERRO);
	}

	public String redirecionarNewEntidade() throws Exception {
		return null;
	}

	public String redirecionarFindEntidade() throws Exception {
		return null;
	}

	public void addMsg(String msg) {
		Mesagens.msg(msg);
	}

}

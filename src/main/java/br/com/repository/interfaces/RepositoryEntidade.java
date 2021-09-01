package br.com.repository.interfaces;

import java.io.Serializable;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface RepositoryEntidade extends Serializable{
	
	Date getUltimoAcessoEntidadeLogada(String name);
	void updateUltimoAcessoUser(String login);
	boolean existeUsuario(String ent_login);

}

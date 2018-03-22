package br.com.room.model.interfaces;

import java.util.List;

import br.com.room.model.entitys.Despesa;
import br.com.room.model.entitys.Tarefa;
import br.com.room.model.entitys.User;

public interface UserServiceI {

	String geraRelatorio();
	float getSaldo(int id);
	User cadastraUser(User user);
	boolean excluirUser(int id);
	User editarUser(User user);
	User buscarPorId(int id);
	List<Despesa> listarDespesasUser(int idUser);
	List<Tarefa> listarTarefasUser(int idUser);
}

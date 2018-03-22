package br.com.room.model.interfaces;

import java.util.List;

import br.com.room.model.entitys.Despesa;
import br.com.room.model.entitys.Grupo;
import br.com.room.model.entitys.Tarefa;
import br.com.room.model.entitys.User;

public interface GroupServiceI {

	String gerarRelatorioGroup();
	void addMembro(int userId, int groupId);
	void addTarefa(int groupId, int taredaId);
	void criarGroup(Grupo grupo);
	List <User> listarMenbros(int groupId);
	void addAdmin(int groupId, int userId);
	void editarGroup(Grupo grupo);
	void excluirGroup(int id);
	Grupo buscarPorId(int id);
	List<Despesa> listarDespesasGroup(int idGroup);
	List<Tarefa> listarTarefasGroup(int idGroup);
}

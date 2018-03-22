package br.com.room.model.interfaces;

import br.com.room.model.entitys.Tarefa;

public interface TarefaServiceI {
	
	void addTarefa(Tarefa tarefa);
	void excluirTarefa(int id);
	void editarTarefa(Tarefa tarefa);
	void direcionarTarefa(int userId, int tarefaId);
	void concluirTarefa(int id, boolean cloncuida);
	Tarefa buscarporId(int id);

}

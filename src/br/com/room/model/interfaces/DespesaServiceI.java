package br.com.room.model.interfaces;


import br.com.room.model.entitys.Despesa;

public interface DespesaServiceI {

	void addDespesa(Despesa despesa);
	void addPagamento(int id);
	Despesa bucarDespesaPorId(int id);
	void editarDespesa(Despesa despesa);
	void excluirDespesa(int id);
}

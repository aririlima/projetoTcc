package br.com.room.controller;

import java.sql.SQLException;

import br.com.room.model.dao.DaoUser;

public class Main {

	public static void main(String[] args) throws SQLException{
		
//
//		List <Tarefa> tarefas = new ArrayList<Tarefa>();
//		tarefas = DaoTarefa.obterTodos();
//		
//		for(Tarefa t : tarefas) {
//			System.out.println(t.getNome());
//			System.out.println(t.getDescricao());
//		}
		
		DaoUser u = new DaoUser();
		u.alteraGroupId("arianna.consultoria@gmail.com");
		
//		System.out.println("É AQUI");
//		DaoTarefa.buscarPorId(5);
//		
	}
}

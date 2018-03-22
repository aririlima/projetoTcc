package br.com.room.model.dao;


import java.text.ParseException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.room.model.entitys.Tarefa;

public class DaoTarefa {

	private static EntityManager conexao = null;
	private static EntityTransaction transacao = null;
	
	public static void incluir(Tarefa tarefa) throws ParseException{		
		conexao = Conexao.obterConexao();		
		transacao = conexao.getTransaction();	
		try { 
			if(!transacao.isActive()) {
				transacao.begin(); 
			}
			conexao.persist(tarefa);
			transacao.commit();	
		}catch (Exception e) { 
			transacao.rollback(); 
		}
	}
	
	public static void merge(Tarefa tarefa){
		conexao = Conexao.obterConexao();		
		transacao = conexao.getTransaction();	
		try { 
			if(!transacao.isActive()) {
				transacao.begin(); 
			}
			conexao.merge(tarefa);
			transacao.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void excluir(Tarefa tarefa) throws ParseException{		
		conexao = Conexao.obterConexao();		
		transacao = conexao.getTransaction();	
		try { 
			if(!transacao.isActive()) {
				transacao.begin(); 
			}
			conexao.remove(tarefa);
			transacao.commit();	
		}catch (Exception e) { 
			transacao.rollback(); 
		}
	}
	
	
	public static Tarefa buscarPorId(int id) {
		conexao = Conexao.obterConexao();		
		
		Tarefa t = conexao.find(Tarefa.class, id);
		System.out.println(t.getNome());
		return t;
	}
	
	public static List<Tarefa> buscarPorUserId(int userId) {
		conexao = Conexao.obterConexao();			    
	    try{
	    	Query query = conexao.createQuery("select t from Tarefa t where userId= "+userId);
	    	return query.getResultList();
	    }catch(Exception ex) {
	    	ex.printStackTrace();
	    }
	    return null;
	}

	public static List<Tarefa> obterTodos(){
		
		conexao = Conexao.obterConexao();			    
	    try {
			Query query = conexao.createQuery("select t from Tarefa t");
		    return query.getResultList();

	    }catch(Exception ex) {
	    	ex.printStackTrace();
	    }
	    return null;
	}
	
	public static List<Tarefa> obterPorGroupId(int id){
		conexao = Conexao.obterConexao();		

		String jpql = "SELECT * FROM tarefa WHERE tarefaId = "+id;
	    TypedQuery<Tarefa> query = conexao.createQuery(jpql, Tarefa.class);
	    return query.getResultList();	
	}

	public static List<Tarefa> buscarPorGroupId(int groupId) {
		conexao = Conexao.obterConexao();			    
	    Query query = conexao.createQuery("select t from Tarefa t where groupId= "+groupId);
	    return query.getResultList();
	}
}

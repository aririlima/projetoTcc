package br.com.room.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.room.model.entitys.Grupo;
import br.com.room.model.entitys.User;

public class DaoGroup {
	
	private static EntityManager conexao = null;
	private static EntityTransaction transacao = null;
	
	
	public static void incluir(Grupo group){
		conexao = Conexao.obterConexao();		
		transacao = conexao.getTransaction();	

		transacao.begin();	
		conexao.flush();
		conexao.persist(group);		
		transacao.commit();	
		//conexao.close();		
	}
	
	public static void alterar(Grupo group){
		conexao = Conexao.obterConexao();		
		transacao = conexao.getTransaction();	
		
		transacao.begin();	
		conexao.merge(group);		
		transacao.commit();		
		//conexao.close();		
	}
	
	public static void excluir(Grupo group){
		conexao = Conexao.obterConexao();		
		transacao = conexao.getTransaction();	
		
		Grupo aGroup = conexao.find(Grupo.class, group.getGroup_id());
		conexao.remove(aGroup);
		//conexao.close();
	}
	
	public static void excluirPeloId(int id){
		conexao = Conexao.obterConexao();		
		transacao = conexao.getTransaction();	
		Grupo grupo;
		try { 
			if(!transacao.isActive()) {
				transacao.begin(); 
			}
		    Query query = conexao.createQuery("select g from Grupo g where id="+id);
			grupo = (Grupo) query.getSingleResult();
			System.out.println("------------------------------------");
			System.out.println(grupo.getNome());
			System.out.println("------------------------------------");
			grupo.setGroupIdd(0);
			conexao.merge(grupo);
			transacao.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Grupo buscarPorId(int id) {
		conexao = Conexao.obterConexao();			    
	    Query query = conexao.createQuery("select g from Grupo g where id="+id);
	    return (Grupo) query.getSingleResult();	
	}

	public static List<User> obterTodos(){
		conexao = Conexao.obterConexao();			    
	    Query query = conexao.createQuery("select u from Group u");
	    return query.getResultList();
	}
	
	public static List<Grupo> obterPorGroupId(int id){
		conexao = Conexao.obterConexao();			    
	    Query query = conexao.createQuery("select u from Group u where id='"+id+"'");
	    return query.getResultList();
	}

}

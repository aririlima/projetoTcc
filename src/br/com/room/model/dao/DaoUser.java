package br.com.room.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.room.model.entitys.User;

public class DaoUser {

	private static EntityManager conexao = null;
	private static EntityTransaction transacao = null;

	
	public static void encerraConexao() {
		conexao.close();
	}
	
	public static void incluir(User user){
		user.setLogin(user.getEmail());
		user.setSenha(user.getSenha());

		conexao = Conexao.obterConexao();		
		transacao = conexao.getTransaction();	

		transacao.begin();		
		conexao.persist(user);		
		transacao.commit();	
		//conexao.close();		
	}


	public static void editarGroupId(User u, int id){

		conexao = Conexao.obterConexao();		
		transacao = conexao.getTransaction();	
		User user;
		try { 
			if(!transacao.isActive()) {
				transacao.begin(); 
			}
			user = conexao.find(User.class, u.getUserId());
			user.setGroupId(id);
			user.setLogin(user.getEmail());
			conexao.merge(user);
			transacao.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
		
	
	public static void excluir(User user){
		conexao = Conexao.obterConexao();		
		transacao = conexao.getTransaction();	
		
		User aUser = conexao.find(User.class, user.getUserId());
		conexao.remove(aUser);
		//conexao.close();
	}
	
	public static void excluirPeloId(int id){
		conexao = Conexao.obterConexao();		
		transacao = conexao.getTransaction();	
		
		User user = buscarPorId(id);
		conexao.remove(user);
		//conexao.close();
	}

	public static void cadastraMembro(String email, int id) {
		conexao = Conexao.obterConexao();		
		transacao = conexao.getTransaction();	
		User user;
		try { 
			if(!transacao.isActive()) {
				transacao.begin(); 
			}
			user = buscarPorEmail(email);
			user.setGroupId(id);
			conexao.merge(user);
			transacao.commit();
		}catch (Exception e) {
			System.out.println("algo de errado não está certo");
			e.printStackTrace();
		}	
	}
	
	public static User buscarPorId(int id) {
		return conexao.find(User.class, id);
	}
	
	public static User buscarPorEmail(String email) {
		conexao = Conexao.obterConexao();			    
		System.out.println(email);
	    try {
	    	User u = (User) conexao
					.createQuery("select u from User u where email= '"+email+"'")
					.getSingleResult();	
			if(u == null) {
				System.out.println("Não veio nada nessa porra");
			}else {
				 return  u;

			}
			
			return null;
		
	    }catch(NoResultException ex) {
	    	ex.printStackTrace();
	    	System.out.println("não encontrou usuário por email");
	    	return null;
	    }
	}
	
	public static List<User> buscarPorGrupoId(int id){
		conexao = Conexao.obterConexao();			    
	    Query query = conexao.createQuery("select u from User u where groupId= "+id);
	    List<User> users = query.getResultList();
	    return users;
	}
	
	public static List<User> obterTodos(){
		conexao = Conexao.obterConexao();			    
	    Query query = conexao.createQuery("select u from User u");
	    return query.getResultList();
	}
	
	public static List<User> obterPorGroupId(int id){
		String jpql = "SELECT u FROM User u WHERE groupId = "+id;
	    TypedQuery<User> query = conexao.createQuery(jpql, User.class);
	    return query.getResultList();	
	}

	public User getUsuario(String login, String senha) {
		
		conexao = Conexao.obterConexao();			    

		try{
			User u = (User) conexao
					.createQuery("select u from User u where login= '"+login+"' and senha= '"+ senha+"'")
					.getSingleResult();		
			
			if(u == null) {
				System.out.println("Não veio nada nessa porra");
			}
			 return  u;
		}catch (NoResultException nre){
			System.out.println("Ususário não existe.");
			return null;
		}
		
//		conexao = Conexao.obterConexao();			    
//	    Query query = conexao.createQuery("select u from User u where nome= "+nome+" and senha= "+ senha);
//	    return (User) query.getSingleResult();
	}
	
//	public User getUsuarioForEmail(String email) {
//		
//		conexao = Conexao.obterConexao();			    
//
//		try{
//			 return  (User) conexao
//							.createQuery("select u from User u where login= '"+login+"' and senha= '"+ senha+"'")
//							.getSingleResult();
//		}catch (NoResultException nre){
//			System.out.println("Ususário não existe.");
//			return null;
//		}
//		
//	}
	
	
   public void alteraGroupId(String email) throws SQLException {
	   try {
	    ResultSet resultSet = (ResultSet) conexao.createQuery("SELECT LAST_INSERT_ID() INTO User");
	    System.out.println(resultSet);
	    
//	    if (resultSet.next()) {
//            System.out.println(resultSet.getInt("LAST_INSERT_ID()"));
//        }
	   }catch(Exception ex) {
		   ex.printStackTrace();
	   } 
	    
   }
	
   
	public void removerPorChave(User user) { 
		
		conexao = Conexao.obterConexao();		
		transacao = conexao.getTransaction();
		
	try { 
	
		transacao.begin(); 
		Object x = conexao.find(User.class, user.getUserId()); 
		Object y = conexao.merge(x); 
		
		conexao.remove(y); 
		
		transacao.commit(); 
	
	}catch (RuntimeException e) {
		
	}
		transacao.rollback();
	}
}

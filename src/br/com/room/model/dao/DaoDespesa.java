package br.com.room.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.room.model.entitys.Despesa;

public class DaoDespesa {

	private static EntityManager conexao = null;
	private static EntityTransaction transacao = null;

	public static void incluir(Despesa despesa) {
		conexao = Conexao.obterConexao();
		transacao = conexao.getTransaction();
		try { 
			
			if(!transacao.isActive()) {
				transacao.begin(); 
			}
			conexao.persist(despesa);
			transacao.commit();	
			
		}catch (Exception e) { 
			transacao.rollback(); 
		}
	}

	public static void merge(Despesa despesa){
		conexao = Conexao.obterConexao();		
		transacao = conexao.getTransaction();	
		try { 
			
			if(!transacao.isActive()) {
				transacao.begin(); 
			}
			conexao.merge(despesa);		
			transacao.commit();	
			
		}catch (Exception e) { 
			transacao.rollback(); 
		}
	}
	
	public static void alterar(Despesa despesa) throws SQLException {
		conexao = Conexao.obterConexao();
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE despesas ");
		sql.append("SET nome = ?, valor = ?, vencimento = ? , obs = ? , status = ? ");
		sql.append("WHERE despesa_id = ? ");
		
		PreparedStatement comando = ((Connection) conexao).prepareStatement(sql.toString());
		
		comando.setString(1, despesa.getNome());
		comando.setDouble(2, despesa.getValor());
		comando.setDate(3, (Date) despesa.getVencimento());
		comando.setString(4, despesa.getObs());
		comando.setObject(5, despesa.getStatus());
		comando.setLong(6, despesa.getDespesaId());
	
		comando.executeUpdate();
		
	}
	

	public static void excluir(Despesa despesa) {
		conexao = Conexao.obterConexao();
		transacao = conexao.getTransaction();
		try { 
			
			if(!transacao.isActive()) {
				transacao.begin(); 
			}
			conexao.remove(despesa);	
			transacao.commit();	
			
		}catch (Exception e) { 
			transacao.rollback(); 
		}
	}

	public static void excluirPeloId(int id) {
		conexao = Conexao.obterConexao();
		transacao = conexao.getTransaction();

		Despesa despesa = buscarPorId(id);
		conexao.remove(despesa);
		// conexao.close();
	}

	public static Despesa buscarPorId(int id) {
		return conexao.find(Despesa.class, id);
	}
	
	public static List<Despesa> buscarPorUserId(int userId) {
		
		conexao = Conexao.obterConexao();			    
	   try { 
		   Query query = conexao.createQuery("select d from Despesa d where userId= "+userId);
		   return query.getResultList();
		    
	    }catch(Exception ex) {
	    	ex.printStackTrace();
	    }
	    return null;
	}

	
	public static List<Despesa> obterTodos(){
		
		conexao = Conexao.obterConexao();	    
	    try {
			Query query = conexao.createQuery("select d from Despesa d");
		    return query.getResultList();
		    
	    }catch(Exception ex) {
	    	ex.printStackTrace();
	    }
	    return null;
		}

	public static List<Despesa> buscarPorGroupId(int groupId) {
		conexao = Conexao.obterConexao();			    
	   try {
			Query query = conexao.createQuery("select d from Despesa d where groupId= "+groupId);
		    return query.getResultList();	
	    
	    }catch(Exception ex) {
	    	ex.printStackTrace();
	    }
	    return null;
	}

	/*
	 * pega despesa pelo id pegar lista de despesar Despesa(id) pegar lista de
	 * despesar grupo(id) pegar valor pegar vencimento pegar obs add pagamento
	 * 
	 */
}

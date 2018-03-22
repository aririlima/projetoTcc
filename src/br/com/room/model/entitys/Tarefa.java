package br.com.room.model.entitys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.room.includes.StatusTarefa;

@Entity
@Table(name="tarefa")
public class Tarefa {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int tarefaId;
	@Column (name="nome")
	private String nome;
	@Column (name="descricao")
	private String descricao;
	@Column (name="data")
	private Date data;
	@Column (name="status")
	private StatusTarefa status;
	@Column (name="groupId")
	private int groupId;
	@Column (name="userId")
	private int userId;

	public Tarefa() {}
	
	public Tarefa(String nome, String descricao, Date data, StatusTarefa status) {
		this.nome = nome;
		this.descricao = descricao;
		this.data = data;
		this.status = status;
	}
	
	public int getTarefaId() {
		return tarefaId;
	}
	public void setTarefaId(int tarefaId) {
		this.tarefaId = tarefaId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public StatusTarefa getStatus() {
		return status;
	}
	public void setStatus(StatusTarefa status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}	
	
	
}

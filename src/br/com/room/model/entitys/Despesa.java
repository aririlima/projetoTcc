package br.com.room.model.entitys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.room.includes.StatusDespesa;

@Entity
@Table(name="despesa")
public class Despesa {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int despesaId;
	@Column (name="nome")
	private String nome;
	@Column (name="valor")
	private float valor;
	@Column (name="vencimento")
	private Date vencimento;
	@Column (name="obs")
	private String obs;
	@Column (name="status")
	private StatusDespesa status;
	@Column (name="groupId")
	private int groupId;
	@Column (name="userId")
	private int userId;
	
	public int getDespesaId() {
		return despesaId;
	}
	public void setDespesaId(int despesaId) {
		this.despesaId = despesaId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getValor() {
		return valor;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}

	public Date getVencimento() {
		return vencimento;
	}
	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public StatusDespesa getStatus() {
		return status;
	}
	public void setStatus(StatusDespesa status) {
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

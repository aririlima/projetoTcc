package br.com.room.model.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="grupo")
public class Grupo {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="groupId")
	private int group_id;

	@Column (name="nome")
	private String nome;

	public int getGroup_id() {
		return group_id;
	}

	public void setGroupIdd(int group_id) {
		this.group_id = group_id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
	
}

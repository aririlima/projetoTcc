package br.com.room.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import br.com.room.includes.AlteraStatus;
import br.com.room.model.dao.DaoGroup;
import br.com.room.model.dao.DaoTarefa;
import br.com.room.model.dao.DaoUser;
import br.com.room.model.entitys.Grupo;
import br.com.room.model.entitys.Tarefa;
import br.com.room.model.entitys.User;


@ManagedBean(name="MBGroup")
public class GrupoBean{
	
	private User user = new User();
	private List<User> itens = new ArrayList<User>();
	private Grupo group = new Grupo();
	@ManagedProperty(value = "#{MBLogin}")
	LoginBean loginBean;
	
	public void criaGroup() throws IOException{
		try {
			System.out.println(loginBean.getUsuario().getLogin() + loginBean.getUsuario().getUserId());
			System.out.println(loginBean.getUsuario().getCpf());
			DaoGroup.incluir(group);
			user = loginBean.getUsuario();
			user.setGroupId(group.getGroup_id());
			System.out.println("-----------------------");
			System.out.println(user.getNome() + user.getGroupId());
			DaoUser.editarGroupId(user, user.getGroupId());
			
			FacesContext.getCurrentInstance().getExternalContext().redirect(
			FacesContext.getCurrentInstance().
		    getExternalContext().getRequestContextPath() + "/pages/cadastroRealizado.xhtml?faces-redirect=true");
			System.out.println("Group cadastrada com sucesso");
			
		}catch(Exception ex){
			ex.printStackTrace();
			FacesContext.getCurrentInstance().getExternalContext().redirect(
			FacesContext.getCurrentInstance().
			getExternalContext().getRequestContextPath() + "/pages/cadastroNaoRealizado.xhtml?faces-redirect=true");
			System.out.println("Group não cadastrada");
		}
	}
	
	public List<User> getMembros() {
		this.itens = DaoUser.buscarPorGrupoId(loginBean.getUsuario().getGroupId());
		for(User u : itens){
			System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkk");
			System.out.println(u.getNome());
			System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkk");

		}
		return itens;
	}

	public void addMembroToGroup() throws IOException{
		System.out.println("EMAIL: "+ user.getEmail());
		try {
			DaoUser.cadastraMembro(user.getEmail(), loginBean.getUsuario().getGroupId());
			
			FacesContext.getCurrentInstance().getExternalContext().redirect(
			FacesContext.getCurrentInstance().
		    getExternalContext().getRequestContextPath() + "/pages/cadastroRealizado.xhtml?faces-redirect=true");
			System.out.println("cadastrada com sucesso");
			
		}catch(Exception ex) {
			ex.printStackTrace();
			
			FacesContext.getCurrentInstance().getExternalContext().redirect(
			FacesContext.getCurrentInstance().
			getExternalContext().getRequestContextPath() + "/pages/cadastroNaoRealizado.xhtml?faces-redirect=true");
		}
	}
	
	public String nomeDoGrupo() throws IOException {
		
		if(loginBean.getUsuario().getGroupId() != 0){
			Grupo group = DaoGroup.buscarPorId(loginBean.getUsuario().getGroupId());
				return group.getNome();
		}else {
				FacesContext.getCurrentInstance().getExternalContext().redirect(
					FacesContext.getCurrentInstance().
					getExternalContext().getRequestContextPath() + "/pages/grupoNaoExiste.xhtml?faces-redirect=true");
				return null;

		}
	}
	
	public void deixarGrupo() throws IOException {
		DaoGroup.excluirPeloId(loginBean.getUsuario().getGroupId());
		FacesContext.getCurrentInstance().getExternalContext().redirect(
		FacesContext.getCurrentInstance().
		getExternalContext().getRequestContextPath() + "/pages/login.xhtml?faces-redirect=true");
	}
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public List<User> getItens() {
		return itens;
	}


	public void setItens(List<User> itens) {
		this.itens = itens;
	}


	public Grupo getGroup() {
		return group;
	}


	public void setGroup(Grupo group) {
		this.group = group;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

}
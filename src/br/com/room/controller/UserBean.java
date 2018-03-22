package br.com.room.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.room.model.dao.DaoUser;
import br.com.room.model.entitys.User;

@ManagedBean(name="MBUser")
public class UserBean{

	private User user = new User();
	private List<User> itens = new ArrayList<User>();
	private String senha2;
	

	public void cadastraUser() throws IOException {
		
		if(user.getSenha().equals(senha2)) {
			try {
				System.out.println(user);
				DaoUser.incluir(user);
				FacesContext.getCurrentInstance().getExternalContext().redirect(
				FacesContext.getCurrentInstance().
			    getExternalContext().getRequestContextPath() + "/pages/cadastroRealizadoLogin.xhtml?faces-redirect=true");
				System.out.println("Usuário cadastrado com sucesso");
			}catch(Exception ex){
				FacesContext.getCurrentInstance().getExternalContext().redirect(
				FacesContext.getCurrentInstance().
				getExternalContext().getRequestContextPath() + "/pages/cadastroNaoRealizadoLogin.xhtml?faces-redirect=true");
				
				System.out.println("Usuário não cadastrado");
			}
		}else {
			FacesContext.getCurrentInstance().getExternalContext().redirect(
			FacesContext.getCurrentInstance().
			getExternalContext().getRequestContextPath() + "/pages/cadastroNaoRealizadoLogin.xhtml?faces-redirect=true");
			System.out.println("Usuário não cadastrado2");
		}
	}
	
	
    public void logout() throws IOException {
    	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("MBLogin");
		System.out.println("Efetuou Logout");

        FacesContext.getCurrentInstance().getExternalContext().redirect(
    	FacesContext.getCurrentInstance().
    	getExternalContext().getRequestContextPath() + "/pages/login.xhtml?faces-redirect=true");
    }
    
	public User getuser() {
		return user;
	}

	public void setuser(User user) {
		this.user = user;
	}

	public List<User> getItens() {
		this.itens = DaoUser.obterTodos();

		return itens;
	}
	
	public List<User> buscaUsers(){
		return null;
	}
	
	public List<User> buscaUsersPagas(){
		return null;
	}
	
	public List<User> buscaUsersVencidas(){
		return null;
	}
	
	public List<User> buscaUsersAVencer(){
		return null;
	}
	
	public void excluirUser() {
		
	}
	
	public void editarUser() {
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSenha2() {
		return senha2;
	}

	public void setSenha2(String senha2) {
		this.senha2 = senha2;
	}
	
	public void setItens(List<User> itens) {
		this.itens = itens;
	}

}

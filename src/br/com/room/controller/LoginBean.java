package br.com.room.controller;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.room.model.dao.DaoUser;
import br.com.room.model.entitys.User;

@ManagedBean(name = "MBLogin")
@SessionScoped
public class LoginBean {

	private DaoUser usuarioDAO = new DaoUser();
	private User usuario = new User();

	public void logar() throws IOException {
		usuario = usuarioDAO.getUsuario(usuario.getLogin(), usuario.getSenha());

		if (usuario == null) {
	
			FacesContext.getCurrentInstance().getExternalContext().redirect(
			FacesContext.getCurrentInstance().
		    getExternalContext().getRequestContextPath() + "/pages/cadastroNaoRealizadoLogin.xhtml?faces-redirect=true");
			
		} else {
			
			System.out.println("Efetuou Login");
            UserBean u = new UserBean();
            TarefaBean t = new TarefaBean();
            DespesaBean d = new DespesaBean();
            GrupoBean g = new GrupoBean();
            u.setUser(usuario);
           	t.setUser(usuario);
           	d.setUser(usuario);
           	g.setUser(usuario);
           	
			FacesContext.getCurrentInstance().getExternalContext().redirect(
			FacesContext.getCurrentInstance().
            getExternalContext().getRequestContextPath() + "/pages/principal.xhtml?faces-redirect=true");	
		}

	}
	

	public void cadastro() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect(
		FacesContext.getCurrentInstance().
	    getExternalContext().getRequestContextPath() + "/pages/cadastro.xhtml?faces-redirect=true");	
	}
	
	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public DaoUser getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(DaoUser usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

}
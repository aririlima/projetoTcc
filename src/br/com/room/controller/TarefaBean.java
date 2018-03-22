package br.com.room.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import br.com.room.includes.AlteraStatus;
import br.com.room.includes.StatusTarefa;
import br.com.room.model.dao.DaoTarefa;
import br.com.room.model.entitys.Tarefa;
import br.com.room.model.entitys.User;


@ManagedBean(name="MBTarefa")
public class TarefaBean {

	private Tarefa tarefa = new Tarefa();
	private List<Tarefa> itens = new ArrayList<Tarefa>();
	private User user = new User();
	@ManagedProperty(value = "#{MBLogin}")
	LoginBean loginBean;
	private boolean addToGroup;
	
	
	public void cadastraTarefa() throws IOException {
		try {
			
	        tarefa.setUserId(loginBean.getUsuario().getUserId());
			tarefa.setStatus(StatusTarefa.A_FAZER);	
			
			if((this.addToGroup == true) && (loginBean.getUsuario().getGroupId() != 0)) {
				tarefa.setGroupId(loginBean.getUsuario().getGroupId());
				System.out.println("-------------------------------");
				System.out.println("Entrou no if");
				DaoTarefa.incluir(tarefa);
			}else {
				DaoTarefa.incluir(tarefa);
			}
			
			FacesContext.getCurrentInstance().getExternalContext().redirect(
			FacesContext.getCurrentInstance().
		    getExternalContext().getRequestContextPath() + "/pages/cadastroRealizado.xhtml?faces-redirect=true");
			System.out.println("Tarefa cadastrada com sucesso");
		}catch(Exception ex){
			ex.printStackTrace();
			FacesContext.getCurrentInstance().getExternalContext().redirect(
			FacesContext.getCurrentInstance().
			getExternalContext().getRequestContextPath() + "/pages/cadastroNaoRealizado.xhtml?faces-redirect=true");
			System.out.println("Tarefa não cadastrada");
		}
	}
	
	public void editaTarefa(Tarefa t) {
		DaoTarefa.merge(t);
	}
	public void excluirTarefa(Tarefa t) throws ParseException {
		DaoTarefa.excluir(t);
	}

	public List<Tarefa> buscarPorUserId() {
		this.itens = (DaoTarefa.buscarPorUserId(loginBean.getUsuario().getUserId()));
		AlteraStatus at = new AlteraStatus();
		for(Tarefa t : itens){
			at.alteraStatusTarefa(t);
			System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkk");
		}
		return itens;
	}
	
	public List<Tarefa> buscarPorGroupId() {
		this.itens = (DaoTarefa.buscarPorGroupId(loginBean.getUsuario().getGroupId()));
		AlteraStatus at = new AlteraStatus();
			for(Tarefa t : itens){
				at.alteraStatusTarefa(t);
				if(t.getGroupId() != 0) {
					return itens;
				}
				return null;
		}
		return null;
	}

	public void addGroupId(Tarefa t) {
		t.setGroupId(user.getGroupId());
		DaoTarefa.merge(t);
	}
	
	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public List<Tarefa> getItens() {
		this.itens = DaoTarefa.obterTodos();

		return itens;
	}

	public void setItens(List<Tarefa> itens) {
		this.itens = itens;
	}

	public DaoTarefa getDaotarefa() {
		return daotarefa;
	}

	public void setDaotarefa(DaoTarefa daotarefa) {
		this.daotarefa = daotarefa;
	}

	private DaoTarefa daotarefa = new DaoTarefa();
	
	
	public List<Tarefa> buscatarefas(){
		return null;
	}
	
	public List<Tarefa> buscatarefasPagas(){
		return null;
	}
	
	public List<Tarefa> buscatarefasVencidas(){
		return null;
	}
	
	public List<Tarefa> buscatarefasAVencer(){
		return null;
	}
	
	public void excluirtarefa() {
		
	}
	
	public void editartarefa() {
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public boolean isAddToGroup() {
		return addToGroup;
	}

	public void setAddToGroup(boolean addToGroup) {
		this.addToGroup = addToGroup;
	}

	
}

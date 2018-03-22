package br.com.room.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import br.com.room.includes.AlteraStatus;
import br.com.room.includes.StatusDespesa;
import br.com.room.model.dao.DaoDespesa;
import br.com.room.model.entitys.Despesa;
import br.com.room.model.entitys.User;


@ManagedBean(name="MBDespesa")

public class DespesaBean{

	private Despesa despesa = new Despesa();
	private List<Despesa> itens = new ArrayList<Despesa>();
	private User user = new User();
	private DaoDespesa daoDespesa = new DaoDespesa();
	@ManagedProperty(value = "#{MBLogin}")
	LoginBean loginBean;
	private boolean addToGroup;

	
	public void cadastraDespesa() throws IOException {
		
		try {
			
	        despesa.setUserId(loginBean.getUsuario().getUserId());	
			despesa.setStatus(StatusDespesa.A_VENCER);
			
			
			if((this.addToGroup == true) && (loginBean.getUsuario().getGroupId() != 0)) {
				despesa.setGroupId(loginBean.getUsuario().getGroupId());
				System.out.println("-------------------------------");
				System.out.println("Entrou no if");
				DaoDespesa.incluir(despesa);
			}else {
				DaoDespesa.incluir(despesa);
			}
	
			FacesContext.getCurrentInstance().getExternalContext().redirect(
			FacesContext.getCurrentInstance().
		    getExternalContext().getRequestContextPath() + "/pages/cadastroRealizado.xhtml?faces-redirect=true");
			System.out.println("Despesa cadastrada com sucesso");
		}catch(Exception ex){
			FacesContext.getCurrentInstance().getExternalContext().redirect(
			FacesContext.getCurrentInstance().
			getExternalContext().getRequestContextPath() + "/pages/cadastroNaoRealizado.xhtml?faces-redirect=true");
			System.out.println("Despesa não cadastrada");

		}
		
	}
	
	public void alteraDespesa(Despesa d) {
		DaoDespesa.merge(d);
	}
	public void excluiDespesa(Despesa d) throws ParseException{
		DaoDespesa.excluir(d);
	}
	
	public List <Despesa> buscarPorUserId() {
		this.itens = (DaoDespesa.buscarPorUserId(loginBean.getUsuario().getUserId()));
		AlteraStatus at = new AlteraStatus();
		for(Despesa d : itens){
			at.alteraStatusDespesa(d);
			System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkk");
		}
		return itens;
	}
	
	public List<Despesa> buscarPorGroupId() {
		
		this.itens = (DaoDespesa.buscarPorGroupId(loginBean.getUsuario().getGroupId()));
		AlteraStatus at = new AlteraStatus();
		for(Despesa d : itens){
			at.alteraStatusDespesa(d);
			if(d.getGroupId() != 0) {
				return itens;
			}
			return null;
		}
		return null;
	}
	
	public void addGroupId(Despesa d) {
		d.setGroupId(user.getGroupId());
		DaoDespesa.merge(d);
	}
	
	public Despesa getDespesa() {
		return despesa;
	}

	public void setDespesa(Despesa despesa) {
		this.despesa = despesa;
	}

	public List<Despesa> getItens() {
		this.itens = DaoDespesa.obterTodos();

		return itens;
	}

	public void setItens(List<Despesa> itens) {
		this.itens = itens;
	}

	public DaoDespesa getDaoDespesa() {
		return daoDespesa;
	}

	public void setDaoDespesa(DaoDespesa daoDespesa) {
		this.daoDespesa = daoDespesa;
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

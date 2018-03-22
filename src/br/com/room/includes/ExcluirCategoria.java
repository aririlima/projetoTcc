package br.com.room.includes;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.convert.FacesConverter;

import br.com.room.controller.LoginBean;
import br.com.room.model.dao.DaoTarefa;
import br.com.room.model.entitys.Tarefa;

@FacesConverter(value="converter")
public class ExcluirCategoria {

	private List<Tarefa> itens = new ArrayList<Tarefa>();
	@ManagedProperty(value = "#{MBLogin}")
	LoginBean loginBean;
	
	public void excluirTarefa(Tarefa t) throws ParseException {
		DaoTarefa.excluir(t);
	}

	@PostConstruct
	public List<Tarefa> buscarPorUserId() {
		this.itens = (DaoTarefa.buscarPorUserId(loginBean.getUsuario().getUserId()));
		AlteraStatus at = new AlteraStatus();
		
		for(Tarefa t : itens){
			at.alteraStatusTarefa(t);
			return itens;
		}
		return null;
	}
}

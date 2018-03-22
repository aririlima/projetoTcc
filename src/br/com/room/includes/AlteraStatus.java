package br.com.room.includes;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.room.model.dao.DaoDespesa;
import br.com.room.model.dao.DaoTarefa;
import br.com.room.model.entitys.Despesa;
import br.com.room.model.entitys.Tarefa;

public class AlteraStatus {

	private Tarefa t = new Tarefa();
	private Despesa d = new Despesa();
	
	public boolean verificaVencimento(Date vencimento, Date dataAtual){
		boolean data;
		System.out.println("Vim AKI2");
		if (vencimento.before(dataAtual) == true){
			data = false;
			System.out.println("if "+vencimento);
			System.out.println("Data" + data);
		}
		else{
			data = true;			
			System.out.println("else "+vencimento);
			System.out.println("Data" + data);
		}
		return data;
	}
	
	public Date getPegaDataAtual() {
		Calendar calendar = new GregorianCalendar();
		Date date = new Date();
		calendar.setTime(date);
		System.out.println("Data de hjjjjjjj");
		System.out.println(date);
		return calendar.getTime();
	}
	
	public void alteraStatusTarefa(Tarefa tarefa) {

		if(verificaVencimento(tarefa.getData(), getPegaDataAtual())==false) {
			tarefa.setStatus(StatusTarefa.INCOMPLETO);
			System.out.println(tarefa.getStatus());

			DaoTarefa.merge(tarefa);
		}else {
			tarefa.setStatus(StatusTarefa.A_FAZER);
			System.out.println(tarefa.getStatus());

			DaoTarefa.merge(tarefa);
		}
	}
	
	public void alteraStatusDespesa(Despesa despesa) {
		
		if(verificaVencimento(despesa.getVencimento(), getPegaDataAtual())==false) {
			despesa.setStatus(StatusDespesa.VENCIDA);
			System.out.println(despesa.getStatus());

			DaoDespesa.merge(despesa);
		}else {
			despesa.setStatus(StatusDespesa.A_VENCER);
			System.out.println(despesa.getStatus());

			DaoDespesa.merge(despesa);
		}
	}

	public Tarefa getT() {
		return t;
	}

	public void setT(Tarefa t) {
		this.t = t;
	}

	public Despesa getD() {
		return d;
	}

	public void setD(Despesa d) {
		this.d = d;
	}
	
}

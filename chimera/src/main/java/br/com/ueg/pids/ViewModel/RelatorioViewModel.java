package br.com.ueg.pids.ViewModel;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.Messagebox;

import br.com.ueg.pids.Control.DepartamentoController;
import br.com.ueg.pids.Control.RelatorioController;
import br.com.ueg.pids.Converter.DateUtils;
import br.com.ueg.pids.Model.Departamento;
import br.com.ueg.pids.Model.Relatorio;

@SuppressWarnings("serial")
public class RelatorioViewModel extends GenericViewModel<Relatorio, RelatorioController>{

	private Date data01;
	private Date data02;
	private List<Departamento> departamentoList;
	private List<Departamento> departamentosSelecionados;
	
	@Init
	public void init() {
		super.init();
		
	}
	@Command
	public void gerarRelatorioDepartamento() throws SQLException{
		Map<String , Object>param = new HashMap<String, Object>();
		if(departamentosSelecionados != null || departamentosSelecionados.size() > 0){
			String n = "";
			if(departamentosSelecionados.size()  <= 1  ){
				for(int i = 0; i < departamentosSelecionados.size(); i++){
					n = n + String.valueOf(departamentosSelecionados.get(i).getIddepartamento());
				}
				param.put("codigo",n);
				param.put("usuario", user.getName());
			}else if(departamentosSelecionados.size()  > 1 ){
				for(int i = 0; i < departamentosSelecionados.size(); i++){
					if(i == (departamentosSelecionados.size()-1)){
						n = n +  String.valueOf(departamentosSelecionados.get(i).getIddepartamento());
					}else{
						n = n + String.valueOf(departamentosSelecionados.get(i).getIddepartamento()) + ",";
					}
				}
				
				Object object = n;
				param.put("codigo",object);
				param.put("usuario", user.getName());
			}
			
			
				try {
						super.gerarRelatorio("reportDepartamento", param);
				} catch (JRException e) {
					e.printStackTrace();
				}
		}else{
			Messagebox.show("Nenhum departamento selecionado!", "Error",
					Messagebox.OK, Messagebox.EXCLAMATION);
			
		}
		
		}
	
	@Command
	public void gerarRelatorioData() throws SQLException{
		
		 
		 	DateUtils dateUtils = new DateUtils();

			String newDate01 = dateUtils.DateToString(data01);
			String newDate02 = dateUtils.DateToString(data02);
			if(newDate01 != null && newDate02 != null){
				Map<String , Object>param = new HashMap<String, Object>();
				 param.put("data01",newDate01);
				 param.put("data02",newDate02);
				 param.put("usuario", user.getName());
				try {
					super.gerarRelatorio("reportData", param);
			} catch (JRException e) {
				e.printStackTrace();
			}
			}else{
				Messagebox.show("Selecione a data!", "Error",
						Messagebox.OK, Messagebox.EXCLAMATION);
			}
			
		}

	@Override
	public Relatorio getObject() {
		return new Relatorio();
	}

	@Override
	public RelatorioController getControl() {
		return new RelatorioController();
	}
	
	public Date getData01() {
		return data01;
	}
	
	public void setData01(Date data01) {
		this.data01 = data01;
	}
	
	public Date getData02() {
		return data02;
	}
	
	public void setData02(Date data02) {
		this.data02 = data02;
	}
	
	@NotifyChange("departamentoList")
	public List<Departamento> getDepartamentoList() {
		DepartamentoController departamentoController = new DepartamentoController();
		departamentoList = departamentoController.getLstEntities();
		return departamentoList;
	}
	public void setDepartamentoList(List<Departamento> departamentoList) {
		this.departamentoList = departamentoList;
	}
	public List<Departamento> getDepartamentosSelecionados() {
		return departamentosSelecionados;
	}
	public void setDepartamentosSelecionados(
			List<Departamento> departamentosSelecionados) {
		this.departamentosSelecionados = departamentosSelecionados;
	}
	
}

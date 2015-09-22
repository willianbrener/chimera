package br.com.ueg.pids.Colections;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.ueg.pids.Control.DepartamentoController;
import br.com.ueg.pids.Model.Departamento;


public class ColecaoDepartamento {

	
	//categoria = categoriaController.getEntity((String) hashMap.get("idcategoria"));
private ArrayList<Departamento> listaDepartamentos = new ArrayList<Departamento>();
	
	public  ArrayList<Departamento> getAll() {
        return listaDepartamentos;
    }
	
	public  void setAll(ArrayList<HashMap<String,Object>> result) {
		if(result != null){
			for (HashMap<String, Object> hashMap : result) {
				Departamento departamento = new Departamento(	
												Integer.parseInt((String) hashMap.get("iddepartamento")),
												(String) hashMap.get("nome"),
												(String) hashMap.get("responsavel"),
												(String) hashMap.get("nivel"),
												(hashMap.get("ativo").equals("t")));
				
				listaDepartamentos.add(departamento);
			}
		}
    }

	public Departamento getIndice(int indice) {
		if(listaDepartamentos.isEmpty()){
			return null;
		}
		   return listaDepartamentos.get(indice);
	}
}

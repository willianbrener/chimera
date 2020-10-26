package br.com.ueg.pids.Colections;


import java.util.ArrayList;
import java.util.HashMap;

import br.com.ueg.pids.Control.DepartamentoController;
import br.com.ueg.pids.Model.Cargo;
import br.com.ueg.pids.Model.Departamento;


public class ColecaoCargo {
	private ArrayList<Cargo> listaCargos = new ArrayList<Cargo>();
	
	public  ArrayList<Cargo> getAll() {
        return listaCargos;
    }
	
	public  void setAll(ArrayList<HashMap<String,Object>> result) {
		DepartamentoController departamentoController = new DepartamentoController();
		Departamento departamento = null;
		if(result != null){
			for (HashMap<String, Object> hashMap : result) {
				departamento = departamentoController.getEntity((String) hashMap.get("iddepartamento"));
				Cargo cargo = new Cargo(	Integer.parseInt((String) hashMap.get("idcargo")),
												(String) hashMap.get("nome"),
												departamento,
												(String) hashMap.get("descricao"),
												(hashMap.get("ativo").equals("t")));
				listaCargos.add(cargo);
			}
		}
    }

	public Cargo getIndice(int indice) {
		if(listaCargos.isEmpty()){
			return null;
		}
		   return listaCargos.get(indice);
	}
}
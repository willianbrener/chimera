package br.com.ueg.pids.Colections;


import java.util.ArrayList;
import java.util.HashMap;

import br.com.ueg.pids.Model.Cargo;


public class ColecaoCargo {
	private ArrayList<Cargo> listaCargos = new ArrayList<Cargo>();
	
	public  ArrayList<Cargo> getAll() {
        return listaCargos;
    }
	
	public  void setAll(ArrayList<HashMap<String,Object>> result) {
		if(result != null){
			for (HashMap<String, Object> hashMap : result) {
				
				Cargo cargo = new Cargo(	Integer.parseInt((String) hashMap.get("idcargo")),
												(String) hashMap.get("nome"),
												(String) hashMap.get("departamento"),
												(String) hashMap.get("descricao"));
				listaCargos.add(cargo);
			}
		}
    }

	public br.com.ueg.pids.Model.Cargo getIndice(int indice) {
		if(listaCargos.isEmpty()){
			return null;
		}
		   return listaCargos.get(indice);
	}
}
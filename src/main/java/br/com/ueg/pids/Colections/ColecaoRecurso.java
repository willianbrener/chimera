package br.com.ueg.pids.Colections;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.ueg.pids.Control.DepartamentoController;
import br.com.ueg.pids.Model.Departamento;
import br.com.ueg.pids.Model.Recurso;

public class ColecaoRecurso {
private ArrayList<Recurso> listaRecursos = new ArrayList<Recurso>();
	
	public  ArrayList<Recurso> getAll() {
        return listaRecursos;
    }
	
	public  void setAll(ArrayList<HashMap<String,Object>> result) {
		DepartamentoController departamentoController = new DepartamentoController();
		Departamento departamento = null;
		if(result != null){
			for (HashMap<String, Object> hashMap : result) {
				departamento = departamentoController.getEntity((String) hashMap.get("iddepartamento"));
				Recurso recurso = new Recurso(	Integer.parseInt((String) hashMap.get("idrecurso")),
												(String) hashMap.get("nome"),
												(String) hashMap.get("descricao"),
												departamento,
												(hashMap.get("ativo").equals("t")));
				listaRecursos.add(recurso);
			}
		}
    }

	public Recurso getIndice(int indice) {
		if(listaRecursos.isEmpty()){
			return null;
		}
		   return listaRecursos.get(indice);
	}
}

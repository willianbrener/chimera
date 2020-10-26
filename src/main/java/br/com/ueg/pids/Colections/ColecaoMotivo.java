package br.com.ueg.pids.Colections;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.ueg.pids.Control.GerenciarSolicitacoesController;
import br.com.ueg.pids.Model.GerenciarSolicitacoes;
import br.com.ueg.pids.Model.Motivo;


public class ColecaoMotivo {

	
private ArrayList<Motivo> listaMotivos = new ArrayList<Motivo>();
	
	public  ArrayList<Motivo> getAll() {
        return listaMotivos;
    }
	
	public  void setAll(ArrayList<HashMap<String,Object>> result) {
		GerenciarSolicitacoesController solicitacoesController = new GerenciarSolicitacoesController();
		GerenciarSolicitacoes solicitacoes = null;
		if(result != null){
			
			for (HashMap<String, Object> hashMap : result) {
				solicitacoes = solicitacoesController.getEntity((String) hashMap.get("idsolicitacoes"));
				Motivo motivo = new Motivo(	
												Integer.parseInt((String) hashMap.get("idmotivo")),
												solicitacoes,
												(String) hashMap.get("descricao"),
												(hashMap.get("ativo").equals("t")));
				
				listaMotivos.add(motivo);
			}
		}
    }

	public Motivo getIndice(int indice) {
		if(listaMotivos.isEmpty()){
			return null;
		}
		   return listaMotivos.get(indice);
	}
}

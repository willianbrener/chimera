package br.com.ueg.pids.Colections;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import br.com.ueg.pids.Control.DepartamentoController;
import br.com.ueg.pids.Control.RecursoController;
import br.com.ueg.pids.Control.UsuarioController;
import br.com.ueg.pids.Model.Cargo;
import br.com.ueg.pids.Model.Departamento;
import br.com.ueg.pids.Model.GerenciarSolicitacoes;
import br.com.ueg.pids.Model.Recurso;
import br.com.ueg.pids.Model.Usuario;

public class ColecaoGerenciarSolicitacoes {
private ArrayList<GerenciarSolicitacoes> listaSolicitacoes = new ArrayList<GerenciarSolicitacoes>();
	
	public  ArrayList<GerenciarSolicitacoes> getAll() {
        return listaSolicitacoes;
    }
	
	public  void setAll(ArrayList<HashMap<String,Object>> result) {
		UsuarioController usuarioController = new UsuarioController();
		Usuario usuario = null;
		
		RecursoController recursoController = new RecursoController();
		Recurso recurso = null;
		if(result != null){
			for (HashMap<String, Object> hashMap : result) {
				usuario = usuarioController.getEntity((String) hashMap.get("idusuario"));
				recurso = recursoController.getEntity((String) hashMap.get("idrecurso"));
				GerenciarSolicitacoes solicitacoes = new GerenciarSolicitacoes(	Integer.parseInt((String) hashMap.get("idgerenciar_solicitacoes")),
												(String) hashMap.get("titulo"),
												(String) hashMap.get("descricao"),
												(String) hashMap.get("permissao"),
												(Date) hashMap.get("data"),
												(String) hashMap.get("hora"),
												usuario,		
												recurso,
												(hashMap.get("ativo").equals("t")));
				listaSolicitacoes.add(solicitacoes);
			}
		}
    }

	public GerenciarSolicitacoes getIndice(int indice) {
		if(listaSolicitacoes.isEmpty()){
			return null;
		}
		   return listaSolicitacoes.get(indice);
	}
}

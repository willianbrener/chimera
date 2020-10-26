package br.com.ueg.pids.Colections;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.ueg.pids.Control.GerenciarSolicitacoesController;
import br.com.ueg.pids.Control.MotivoController;
import br.com.ueg.pids.Model.GerenciarSolicitacoes;
import br.com.ueg.pids.Model.Motivo;
import br.com.ueg.pids.Model.SolicitacaoMotivo;

public class ColecaoSolicitacaoMotivo {
private ArrayList<SolicitacaoMotivo> listaSolicitacaoMotivo = new ArrayList<SolicitacaoMotivo>();
	
	public  ArrayList<SolicitacaoMotivo> getAll() {
        return listaSolicitacaoMotivo;
    }
	
	public  void setAll(ArrayList<HashMap<String,Object>> result) {
		GerenciarSolicitacoesController solicitacoesController = new GerenciarSolicitacoesController();
		GerenciarSolicitacoes solicitacoes = null;
		MotivoController motivoController = new MotivoController();
		Motivo motivo = null;
		if(result != null){
			for (HashMap<String, Object> hashMap : result) {
				solicitacoes = solicitacoesController.getEntity((String) hashMap.get("idsolicitacoes"));
				motivo = motivoController.getEntity((String) hashMap.get("idmotivo"));
				SolicitacaoMotivo solicitacaoMotivo = new SolicitacaoMotivo(
								Integer.parseInt((String) hashMap.get("idsolicitacaomotivo")),
												(String) hashMap.get("data"),
												solicitacoes,
												motivo);
				listaSolicitacaoMotivo.add(solicitacaoMotivo);
			}
		}
    }

	public SolicitacaoMotivo getIndice(int indice) {
		if(listaSolicitacaoMotivo.isEmpty()){
			return null;
		}
		   return listaSolicitacaoMotivo.get(indice);
	}
}

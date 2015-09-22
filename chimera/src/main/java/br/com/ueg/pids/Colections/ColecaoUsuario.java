package br.com.ueg.pids.Colections;


import java.util.ArrayList;
import java.util.HashMap;

import br.com.ueg.pids.Control.CargoController;
import br.com.ueg.pids.Control.DepartamentoController;
import br.com.ueg.pids.Model.Cargo;
import br.com.ueg.pids.Model.Departamento;
import br.com.ueg.pids.Model.Usuario;


public class ColecaoUsuario {
	private ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
	
	public  ArrayList<Usuario> getAll() {
        return listaUsuarios;
    }
	
	public  void setAll(ArrayList<HashMap<String,Object>> result) {
		
		CargoController cargoController = new CargoController();
		Cargo cargo = null;
		if(result != null){
			for (HashMap<String, Object> hashMap : result) {
				cargo = cargoController.getEntity((String) hashMap.get("idcargo"));
				
				Usuario usuario = new Usuario(	Integer.parseInt((String) hashMap.get("idusuario")),
												(String) hashMap.get("account"),
												(String) hashMap.get("fullName"),
												(String) hashMap.get("password"),
												(String) hashMap.get("email"),
												(String) hashMap.get("permissao"),
												cargo,
												(hashMap.get("ativo").equals("t")));
				listaUsuarios.add(usuario);
			}
		}
    }

	public Usuario getIndice(int indice) {
		if(listaUsuarios.isEmpty()){
			return null;
		}
		   return listaUsuarios.get(indice);
	}
}
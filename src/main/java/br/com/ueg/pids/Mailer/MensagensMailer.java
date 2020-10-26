package br.com.ueg.pids.Mailer;

import br.com.ueg.pids.Model.Usuario;

public class MensagensMailer {
	
	public String mensagemAoAprovar(Usuario usuario, String string){
		return "O usuário"+usuario.getNome()+" está solicitando o recurso "+string+"";
	}
	public String mensagemAoExecutar(Usuario usuario, String string){
		return "O usuário"+usuario.getNome()+" está solicitando o recurso"+string+""
				+ " e já foi aprovado.";
	}
	public String mensagemAoReprovar(Usuario usuario){
		return "Sua solicitação foi reprovada pelo responsável por "
				+ "aprovação da área.";
	}
	public String mensagemAoCadastrar(Usuario usuario){
		return "Segue abaixo seus dados de acesso ao sistema CHIMERA e em anexo "
				+ "instruções de uso e login no mesmo."
				+ ""
				+ "/nLogin:"+usuario.getAccount()+""
				+ "/nSenha:"+usuario.getPassword()+"";
	}

}

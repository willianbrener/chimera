package br.com.ueg.pids.Utils;

import br.com.ueg.pids.Enum.SituacaoSolicitacao;
import br.com.ueg.pids.Model.Departamento;
import br.com.ueg.pids.Model.Recurso;


public class Utils {
	/**
	 * Método que retorna string com a primeira letra em maiúscula, o restante
	 * permanece o mesmo.
	 * 
	 * @param str
	 *            valor
	 * @return valor com a primeira letra em maiúsculo
	 */
	public static String firstToUpperCase(String str) {
		return str.substring(0, 1).toUpperCase().concat(str.substring(1));
	}

	/**
	 * Método que retorna string com a primeira letra em minúsculo, o restante
	 * permanece o mesmo.
	 * 
	 * @param str
	 *            valor
	 * @return valor com a primeira letra em minúsculo
	 */
	public static String firstToLowerCase(String str) {
		return str.substring(0, 1).toLowerCase().concat(str.substring(1));
	}

	public static String totalLowerCase(String str) {
		return str.toLowerCase();
	}

	public static String retirarExtensao(String string) {
		String resposta = null;
		if (string == null)
			return null;
		String extensao = null;
		int tamanho = string.length();
		extensao = string.substring((tamanho - 4), tamanho);
		if (extensao.substring(0, 1).equalsIgnoreCase(".")) {
			resposta = extensao;
		} else {
			resposta = string.substring((tamanho - 5), tamanho);
		}
		return Utils.totalLowerCase(resposta);
	}

	public String verificaPaginaSolicitacoes(String string) {
		String retorno = null;
		String criar = "cadastrar";
		String listar = "pesquisar";
		if (string.contains(criar)) {
			retorno = "criar";
		} else if (string.contains(listar)) {
			retorno = "listar";
		}
		return retorno;

	}

	public String PermissaoZulSolicitacoes(String string) {
		String retorno = null;
		String administrador = "administrador";
		String aprovador = "approver";
		String executor = "executioner";
		String usuario = "user";

		if (string.contains(administrador)) {
			return "administrador";
		} else if (string.contains(aprovador)) {
			return "approver";
		} else if (string.contains(executor)) {
			return "executioner";
		} else if (string.contains(usuario)) {
			return "user";
		}
		return retorno;
	}
	
	
}
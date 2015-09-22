package br.com.ueg.pids.Model;

import java.io.Serializable;
import java.util.Date;

import br.com.ueg.pids.Annotations.Campo;
import br.com.ueg.pids.Annotations.Table;
import br.com.ueg.pids.Enum.Permissao;


@Table(nome="usuario")
public class Usuario extends GenericModel<Integer> implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;
	
	@Campo(nome="idusuario",pk=true)
	int idusuario;
	
	@Campo(nome="account",obrigatorio=true)
	String account;
	
	@Campo(nome="fullName",obrigatorio=true)
	String fullName;
	
	@Campo(nome="password",obrigatorio=true)
	String password;
	
	@Campo(nome="email",obrigatorio=true)
	String email;
	
	@Campo(nome="permissao",obrigatorio=true)
	private String permissao;

	@Campo(nome="idcargo",obrigatorio=true)
	private Cargo cargo;
	
	@Campo(nome="ativo",obrigatorio=true)
	private boolean ativo;
	
	public Usuario(int idusuario, String account,  String fullName, String password, 
			String email, String permissao, Cargo cargo, boolean ativo){
		this.idusuario = idusuario;
		this.account = account;
		this.fullName = fullName;
		this.password = password;
		this.email = email;
		this.permissao = permissao;
		this.cargo = cargo;
		this.ativo = ativo;
		
	}
	
	public Usuario(String account, String password, String fullName,String email) {
		this.account = account;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
	}

	public Usuario() {
	}

	public String getAccount() {
		return account;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		return result;
	}
	
	
	

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		return true;
	}
	
	public static Usuario clone(Usuario user){
		try {
			return (Usuario)user.clone();
		} catch (CloneNotSupportedException e) {
			
		}
		return null;
	}

	public String getOrdenacao() {
		return "fullName";
	}

	@Override
	public String getVariaveisPesquisarNome() {
		return "fullName";
	}
}
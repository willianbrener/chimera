package br.com.ueg.pids.Model;



import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserCredential implements Serializable{
	private static final long serialVersionUID = 1L;
	
	String account;
	String name;
	String permission;
	
	Set<String> roles = new HashSet<String>();

	public UserCredential(String account, String name,String permission) {
		this.account = account;
		this.name = name;
		this.permission = permission;
	}

	public UserCredential() {
		this.account = "anonymous";
		this.name = "Anonymous";
		this.permission= "Anonymous";
		roles.add("anonymous");
	}

	public boolean isAnonymous() {
		return hasRole("anonymous") || "anonymous".equals(permission);
	}
	
	public boolean isSolicitante(){
		return hasRole("SOLICITANTE") || "SOLICITANTE".equals(permission);
		
	}
	
	public boolean isAprovador(){
		return hasRole("APROVADOR") || "APROVADOR".equals(permission);
		
	}
	
	public boolean isExecutor(){
		return hasRole("EXECUTOR") || "EXECUTOR".equals(permission);
		
	}
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean hasRole(String role){
		return roles.contains(role);
	}
	
	public void addRole(String role){
		roles.add(role);
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

}

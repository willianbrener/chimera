package br.com.ueg.pids.Model;



import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserCredential implements Serializable{
	private static final long serialVersionUID = 1L;
	
	String account;
	String name;
	
	Set<String> roles = new HashSet<String>();

	public UserCredential(String account, String name) {
		this.account = account;
		this.name = name;
	}

	public UserCredential() {
		this.account = "anonymous";
		this.name = "Anonymous";
		roles.add("anonymous");
	}

	public boolean isAnonymous() {
		return hasRole("anonymous") || "anonymous".equals(account);
	}
	
	public boolean isUser(){
		return hasRole("user") || "user".equals(account);
		
	}
	
	public boolean isAdmin(){
		return hasRole("admin") || "admin".equals(account);
		
	}
	
	public boolean isExecutioner(){
		return hasRole("executioner") || "executioner".equals(account);
		
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

}

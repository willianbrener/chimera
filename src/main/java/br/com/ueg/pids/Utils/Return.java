package br.com.ueg.pids.Utils;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.ueg.pids.Enum.TypeMessage;

public class Return {
	
	private Serializable serializable;
	private boolean valid;
	private List<?> list;
	private ArrayList<Message> messages;
	private String mensagem;
	private TypeMessage typeMessage;
	
	public Return(boolean valid, String mensagem) {
		super();
		this.valid = valid;
		this.mensagem = mensagem;
	}
	
	public Return(boolean valid){
		this.valid = valid;
	}
	
	
	public Return(Serializable serializable){
		this.serializable = serializable;
		this.valid = true;
	}
	
	public Return(boolean valid, Message message){
		this.valid = valid;
		this.messages = new ArrayList<Message>();
		this.messages.add(message);
	}
	
	public Return(boolean valid, List<?> list){
		this.valid = valid;
		this.list = list;
	}
	
	public Return(boolean valid, ArrayList<Message> messages){
		this.valid = valid;
		this.messages = messages;
	}
	
	public Return(boolean valid, List<?> list, Message message){
		this.valid = valid;
		this.list = list;
		this.messages = new ArrayList<Message>();
		this.messages.add(message);
	}
	
	public Return(boolean valid, String mensagem, TypeMessage typeMessage) {
		this.valid = valid;
		this.mensagem = mensagem;
		this.typeMessage = typeMessage;
	}
		
	public Return(boolean valid, List<?> list, ArrayList<Message> messages){
		this.valid = valid;
		this.list = list;
		this.messages = messages;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}
	
	public void addMessage(Message message) {
		if(getMessages() == null){
			this.messages = new ArrayList<Message>();
		}
		this.messages.add(message);
	}

	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public TypeMessage getTypeMessage() {
		return typeMessage;
	}

	public void setTypeMessage(TypeMessage typeMessage) {
		this.typeMessage = typeMessage;
	}

	public Serializable getSerializable() {
		return serializable;
	}

	public void setSerializable(Serializable serializable) {
		this.serializable = serializable;
	}

	public void concat(Return ret) {
		setSerializable(ret.getSerializable());
		
		setValid(isValid() && ret.isValid());
		
		if(getList() == null || getList().isEmpty()){
			setList(ret.getList());
		}
		
		if(getMessages() == null || getMessages().isEmpty()){
			setMessages(ret.getMessages());
		} else {
			if(ret.getMessages() != null){
				getMessages().addAll(ret.getMessages());
			}
		}
	}
}

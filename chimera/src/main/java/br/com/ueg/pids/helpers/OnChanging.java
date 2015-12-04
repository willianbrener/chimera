package br.com.ueg.pids.helpers;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;

import br.com.ueg.pids.Utils.Return;

public class OnChanging {
	private String selection;

	
	@Init
	public Return init(@ContextParam(ContextType.VIEW) Component view){
		Return ret = new Return(true);
		Selectors.wireComponents(view, this, false);
		return ret;
	}
	@Command
	public void doCommand(@BindingParam("self_value") String self_value,
			@BindingParam("self_value") String event_value,
			@ContextParam(ContextType.TRIGGER_EVENT) InputEvent event) {
		System.out.println("doCommand: selection: " + selection
				+ " self_value: " + self_value + " event_value: " + event_value
				+ " event_value2: " + event.getValue());
	}

	@Listen("onChanging=#t1")
	public void onChanging(InputEvent event) {
		System.out.println("onChanging=#t1: event_value3: " + event.getValue());
	}
	

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}
}

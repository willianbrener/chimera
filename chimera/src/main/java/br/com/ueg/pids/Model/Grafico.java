package br.com.ueg.pids.Model;

import org.zkoss.zul.PieModel;
import org.zkoss.zul.SimplePieModel;

public class Grafico extends GenericModel<Integer> {

	public static PieModel getModel() {
		PieModel model = new SimplePieModel();
		model.setValue("C#", new Double(21.2));
		model.setValue("VB", new Double(10.2));
		model.setValue("Java", new Double(40.4));
		model.setValue("PHP", new Double(28.2));
		return model;
	}

	public String getOrdenacao() {
		return null;
	}

	@Override
	public String getVariaveisPesquisarNome() {
		return null;
	}

}

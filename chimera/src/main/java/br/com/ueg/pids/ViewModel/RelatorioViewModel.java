package br.com.ueg.pids.ViewModel;

import java.io.*;
import java.io.File;
import java.util.Arrays;
import java.util.Date;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import br.com.ueg.pids.Control.RelatorioController;
import br.com.ueg.pids.Model.Relatorio;
import br.com.ueg.pids.reporting.CustomDataSource;
import br.com.ueg.pids.reporting.ReportConfig;
import br.com.ueg.pids.reporting.ReportType;

@SuppressWarnings("serial")
public class RelatorioViewModel extends GenericViewModel<Relatorio, RelatorioController>{

	ReportType reportType = null;
	private ReportConfig reportConfig = null;
	private Window windowRelatorio;
	
	private ListModelList<ReportType> reportTypesModel = new ListModelList<ReportType>(
			Arrays.asList(
					new ReportType("PDF", "pdf"), 
					new ReportType("HTML", "html"), 
					new ReportType("Word (RTF)", "rtf"), 
					new ReportType("Excel", "xls"), 
					new ReportType("Excel (JXL)", "jxl"), 
					new ReportType("CSV", "csv"), 
					new ReportType("OpenOffice (ODT)", "odt")));

	@Command("showReport")
	@NotifyChange("reportConfig")
	public void showReport() {
		reportConfig = new ReportConfig();
		reportConfig.setType(reportType);
		reportConfig.setDataSource(new CustomDataSource());
	}
	@Command
	@NotifyChange("reportConfig")
	public void gerarRelatorio(BindContext ctx,
			@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireEventListeners(view, this);
		setWindowRelatorio((Window) view.getFellow("windowRelatorio"));
		getWindowRelatorio().setVisible(true);
		getWindowRelatorio().doModal();
	}
	
	@Command
    public void close(BindContext ctx,
			@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireEventListeners(view, this);
		setWindowRelatorio((Window) view.getFellow("windowRelatorio"));
	    getWindowRelatorio().setVisible(false);

    }
	
	public ListModelList<ReportType> getReportTypesModel() {
		return reportTypesModel;
	}

	public ReportConfig getReportConfig() {
		return reportConfig;
	}
	
	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}

	@Override
	public Relatorio getObject() {
		return new Relatorio();
	}

	@Override
	public RelatorioController getControl() {
		return new RelatorioController();
	}

	public Window getWindowRelatorio() {
		return windowRelatorio;
	}

	public void setWindowRelatorio(Window windowRelatorio) {
		this.windowRelatorio = windowRelatorio;
	}
	
}

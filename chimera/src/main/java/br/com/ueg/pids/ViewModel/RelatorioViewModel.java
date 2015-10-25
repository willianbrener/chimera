package br.com.ueg.pids.ViewModel;


import java.util.Arrays;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModelList;

import br.com.ueg.pids.Control.RelatorioController;
import br.com.ueg.pids.Model.Relatorio;
import br.com.ueg.pids.reporting.CustomDataSource;
import br.com.ueg.pids.reporting.ReportConfig;
import br.com.ueg.pids.reporting.ReportType;

@SuppressWarnings("serial")
public class RelatorioViewModel extends GenericViewModel<Relatorio, RelatorioController>{

	ReportType reportType = null;
	private ReportConfig reportConfig = null;
	
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
}

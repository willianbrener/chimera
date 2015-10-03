package br.com.ueg.pids.Converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	
	
public String DateToString(Date data) {
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String reportDate = df.format(data);
		return reportDate;
	}
	
}

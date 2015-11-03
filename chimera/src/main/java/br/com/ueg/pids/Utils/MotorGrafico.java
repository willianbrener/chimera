package br.com.ueg.pids.Utils;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
 


import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PiePlot;
import org.zkoss.zkex.zul.impl.JFreeChartEngine;
import org.zkoss.zul.Chart;
 


import br.com.ueg.pids.Model.GraficoCor;
 
@SuppressWarnings("serial")
public class MotorGrafico extends JFreeChartEngine {
     
    private boolean explode = false;
     
    public boolean prepareJFreeChart(JFreeChart jfchart, Chart chart) {
        jfchart.setBackgroundPaint(Color.white);
 
        PiePlot piePlot = (PiePlot) jfchart.getPlot();
        piePlot.setLabelBackgroundPaint(GraficoCor.COLOR_4);
 
        Paint[] colors = new Paint[] {GraficoCor.COLOR_6, GraficoCor.COLOR_7, GraficoCor.COLOR_8, GraficoCor.COLOR_9};
        DefaultDrawingSupplier defaults = new DefaultDrawingSupplier();
        piePlot.setDrawingSupplier(new DefaultDrawingSupplier(colors, new Paint[]{defaults.getNextFillPaint()}, new Paint[]{defaults.getNextOutlinePaint()}, 
                new Stroke[]{defaults.getNextStroke()}, new Stroke[] {defaults.getNextOutlineStroke()}, new Shape[] {defaults.getNextShape()}));
         
        piePlot.setShadowPaint(null);
 
        piePlot.setSectionOutlinesVisible(false);
 
        piePlot.setExplodePercent("Java", explode ? 0.2 : 0);
 
        return false;
    }
 
    public void setExplode(boolean explode) {
        this.explode = explode;
    }
}
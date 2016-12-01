package hu.tb.view;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.data.LineDataset;
import com.byteowls.vaadin.chartjs.options.Position;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tivadar Bocz on 2016.11.02..
 */
@SpringView(name = ChartView.VIEW_NAME)
public class ChartView extends VerticalLayout implements View {

    public final static String VIEW_NAME = "chart";

    @PostConstruct
    void init() {
        buildLayout();
        configureComponents();
    }

    private void buildLayout() {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        addComponent(layout);
        layout.addComponent(getDummyChart());
    }

    private void configureComponents(){

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    private ChartJs getDummyChart(){
        BarChartConfig config = new BarChartConfig();
        config
                .data()
                .labels("January", "February", "March", "April", "May", "June", "July")
                .addDataset(new BarDataset().type().label("Dataset 1").backgroundColor("rgba(151,187,205,0.5)").borderColor("white").borderWidth(2))
                .addDataset(new LineDataset().type().label("Dataset 2").backgroundColor("rgba(151,187,205,0.5)").borderColor("white").borderWidth(2))
                .addDataset(new BarDataset().type().label("Dataset 3").backgroundColor("rgba(220,220,220,0.5)"))
                .and();

        config.
                options()
                .responsive(true)
                .title()
                .display(true)
                .position(Position.LEFT)
                .text("Chart.js Combo Bar Line Chart")
                .and()
                .done();

        List<String> labels = config.data().getLabels();
        for (Dataset<?, ?> ds : config.data().getDatasets()) {
            List<Double> data = new ArrayList<>();
            for (int i = 0; i  < labels.size(); i++) {
                data.add((Math.random() > 0.5 ? 1.0 : -1.0) * Math.round(Math.random() * 100));
            }

            if (ds instanceof BarDataset) {
                BarDataset bds = (BarDataset) ds;
                bds.dataAsList(data);
            }

            if (ds instanceof LineDataset) {
                LineDataset lds = (LineDataset) ds;
                lds.dataAsList(data);
            }
        }

        ChartJs chart = new ChartJs(config);
        chart.setJsLoggingEnabled(true);
        chart.setWidth(1024,Unit.PIXELS);

        return chart;
    }
}
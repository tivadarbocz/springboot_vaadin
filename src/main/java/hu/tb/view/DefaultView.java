package hu.tb.view;

import com.jarektoro.responsivelayout.ResponsiveColumn;
import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.PostConstruct;


/**
 * Created by Admin on 2016.11.02..
 */
@SpringView(name = DefaultView.VIEW_NAME)
public class DefaultView extends ResponsiveLayout implements View {

    public final static String VIEW_NAME = "";

    @PostConstruct
    void init() {
        buildResponsiveLayout();
    }

    private void buildResponsiveLayout() {

// Here we are creating a new responsiveLayout to house the multiple rows
        // for the main content


        // simple row with one column that takes 3/12 spaces
        // and then the row centers that column to the middle
        ResponsiveRow titleRow = new ResponsiveRow();
        titleRow.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        Label title = new Label("Test Subjects");
        titleRow.setMargin(true);


        ResponsiveColumn titleCol = new ResponsiveColumn(3);
        titleCol.setComponent(title);
        titleRow.addColumn(titleCol);

        addRow(titleRow);


        // Here we have a new Row just for the test subjects

        ResponsiveRow testSubjectsRow = new ResponsiveRow();

        for (int x = 0; x < 10; x++) {

            // We want each column to take
            // 12/12 on mobile
            // 6/12 on tablet
            // 4/12 on computer screens
            // 3/12 on wide computer screens
            Button btn = new Button("", FontAwesome.EDIT);
            btn.addStyleName(ValoTheme.BUTTON_FRIENDLY);
            btn.setSizeFull();

            Panel panel = new Panel(btn);
            panel.setSizeFull();
            ResponsiveColumn testerCol = new ResponsiveColumn(12, 6, 4, 3);
            testerCol.setComponent(btn);
            testSubjectsRow.addColumn(testerCol);

        }

        // sets spacing between the columns and margin around the whole row

        testSubjectsRow.setHorizontalSpacing(true);
        testSubjectsRow.setVerticalSpacing(true);
        testSubjectsRow.setMargin(true);

        addRow(testSubjectsRow);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // the view is constructed in the init() method()
    }
}
/*
@SpringView(name = DefaultView.VIEW_NAME)
public class DefaultView extends VerticalLayout implements View {

    public final static String VIEW_NAME = "";

    @PostConstruct
    void init(){
        addComponent(new Label("This is the default view."));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // the view is constructed in the init() method()
    }
}*/
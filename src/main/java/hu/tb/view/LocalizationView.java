package hu.tb.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

/**
 * Created by Tivadar Bocz on 2016.11.08..
 */
@SpringView(name = LocalizationView.VIEW_NAME)
public class LocalizationView extends VerticalLayout implements View {

    public final static String VIEW_NAME = "localization";

    @PostConstruct
    void init() {
        addComponent(new Label("This is the default view."));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // the view is constructed in the init() method()
    }
}
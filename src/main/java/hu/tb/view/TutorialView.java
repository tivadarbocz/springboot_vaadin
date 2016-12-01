package hu.tb.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Tivadar Bocz on 2016.11.23..
 */
@UIScope
@SpringView(name = TutorialView.VIEW_NAME)
public class TutorialView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "tutorial";

    void init() {
        buildLayout();
        configureComponents();

    }

    private void configureComponents() {
    }

    private void buildLayout() {

    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}

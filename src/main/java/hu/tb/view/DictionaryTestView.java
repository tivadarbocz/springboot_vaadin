package hu.tb.view;

import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;

/**
 * Created by Tivadar Bocz on 2016.12.01..
 */
@SpringView(name = DictionaryTestView.VIEW_NAME)
public class DictionaryTestView extends ResponsiveLayout implements View {

    public final static String VIEW_NAME = "dictionarytest";

    void init() {
        buildResponsiveLayout();
    }

    private void buildResponsiveLayout() {

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}

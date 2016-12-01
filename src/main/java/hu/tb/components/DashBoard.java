package hu.tb.components;

import com.vaadin.ui.VerticalLayout;

/**
 * Created by Tivadar Bocz on 2016.11.29..
 */
@Deprecated
public class DashBoard extends VerticalLayout {

    private VerticalMenuBar verticalMenuBar;

    public DashBoard() {
        verticalMenuBar = new VerticalMenuBar();
        addComponent(verticalMenuBar);
    }

    public void hideComponents() {
        verticalMenuBar.hideComponents();
    }

    public void showComponents() {
        verticalMenuBar.showComponents();
    }
}

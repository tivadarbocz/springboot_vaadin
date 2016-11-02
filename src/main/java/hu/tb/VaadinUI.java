package hu.tb;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import hu.tb.view.ChartView;
import hu.tb.view.DefaultView;
import hu.tb.view.TableView;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Admin on 2016.11.02..
 */
@SpringUI()
@Theme("valo")
public class VaadinUI extends UI {

    private static final String START_VIEW = "Start";
    private static final String TABLE_VIEW = "Table View";
    private static final String CHART_VIEW = "Chart View";
    // We can use either constructor autowiring or field autowiring
    @Autowired
    private SpringViewProvider viewProvider;


    @Override
    protected void init(VaadinRequest request) {

        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        //root.setMargin(true);
        //root.setSpacing(true);
        setContent(root);


        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.MENUBAR_SMALL);
        navigationBar.addComponent(createNavigationButton(START_VIEW, DefaultView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton(TABLE_VIEW, TableView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton(CHART_VIEW, ChartView.VIEW_NAME));
/*
https://dev.vaadin.com/svn/doc/book-examples/branches/vaadin-7/src/com/vaadin/book/examples/component/MenuBarExample.java
        MenuBar menuBar = new MenuBar();
        menuBar.addItem(START_VIEW, new Navigator().navigateTo(DefaultView.VIEW_NAME),null,null);
        menuBar.addItem("Beverages",null,null);
        menuBar.addItem("Beverages",null,null);



        root.addComponent(menuBar);*/
        root.addComponent(navigationBar);
        final Panel viewContainer = new Panel();
        viewContainer.setSizeFull();
        root.addComponent(viewContainer);
        root.setExpandRatio(viewContainer, 1.0f);

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addProvider(viewProvider);
    }

    private Button createNavigationButton(String s, String viewName) {
        Button button = new Button(s);

        switch (s) {
            case START_VIEW:
                button.setIcon(FontAwesome.HOME);
                break;
            case TABLE_VIEW:
                button.setIcon(FontAwesome.TABLE);
                break;
            case CHART_VIEW:
                button.setIcon(FontAwesome.BAR_CHART_O);
                break;

            default:
                button.setIcon(FontAwesome.FILE);
                break;
        }
        button.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_RIGHT);
        button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
        return button;
    }
}

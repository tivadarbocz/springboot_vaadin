package hu.tb;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import hu.tb.components.TopMenuBar;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Admin on 2016.11.02..
 */
@SpringUI()
@Theme("valo")
public class VaadinUI extends UI {


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

        TopMenuBar topMenuBar = new TopMenuBar();
        root.addComponent(topMenuBar.getMenubar(getUI()));

        final Panel viewContainer = new Panel();
        viewContainer.setSizeFull();
        root.addComponent(viewContainer);
        root.setExpandRatio(viewContainer, 1.0f);

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addProvider(viewProvider);
    }
}

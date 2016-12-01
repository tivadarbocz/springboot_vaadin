package hu.tb;

import com.jarektoro.responsivelayout.ResponsiveColumn;
import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Viewport;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import hu.tb.components.ResponsiveComponents;
import hu.tb.i18n.I18N;
import hu.tb.i18n.support.TranslatableSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

/**
 * Created by Admin on 2016.11.02..
 */
@SpringUI()
@Theme("valo")
@Viewport(value = "width=device-width, initial-scale=1.0")
public class VaadinUI extends UI {

    // We can use either constructor autowiring or field autowiring
    @Autowired
    private SpringViewProvider viewProvider;
    @Autowired
    private I18N i18n;
    private final TranslatableSupport translatableSupport = new TranslatableSupport(this);

    @Override
    protected void init(VaadinRequest request) {
        setLocale(Locale.ENGLISH);
        buildResponsiveLayout();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method will also update the message strings of all {@link Translatable} components currently attached
     * to the UI.
     *
     * @see #updateMessageStrings()
     */
    @Override
    public void setLocale(Locale locale) {
        super.setLocale(locale);
        updateMessageStrings();
    }


    /**
     * Returns the {@link TranslatableSupport} delegate owned by this UI.
     */
    protected TranslatableSupport getTranslatableSupport() {
        return translatableSupport;
    }

    /**
     * Updates the message strings of all {@link Translatable} components attached to this UI.
     */
    protected void updateMessageStrings() {
        getTranslatableSupport().updateMessageStrings(getLocale());
    }

    private void buildResponsiveLayout() {
        ResponsiveComponents responsiveComponents = new ResponsiveComponents();
         /*Responsive menu*/
        setSizeFull(); // set the size of the UI to fill the screen
        ResponsiveLayout responsiveLayout = responsiveComponents.getRootLayout();
        setContent(responsiveLayout);

        // ResponsiveLayouts have rows
        // Our first row will contain our 2 Columns
        // The Menu Column & the Main Column
        ResponsiveRow rootRow = responsiveLayout.addRow();
        rootRow.setHeight("100%");
        /*------------------------------------------------------*/
        //This is a convienece constructor
        //First Param is the size for DisplaySize.XS
        //Second Param is the size for DisplaySize.SM
        //Third Param is the size for DisplaySize.MD
        //Fourth Param is the size for DisplaySize.LG

        ResponsiveColumn sideMenuCol = new ResponsiveColumn(12, 12, 2, 2);
        rootRow.addColumn(sideMenuCol);
        // Fluent API
        ResponsiveColumn mainContentCol = rootRow.addColumn().withDisplayRules(12, 12, 10, 10);


        /**
         * Set the menubar to first column
         */
        sideMenuCol.setComponent(responsiveComponents.getMenuBar(getUI()));
        /**
         * Set the layout to second (main content) column
         */
        final ResponsiveLayout viewContainer = new ResponsiveLayout();
        viewContainer.setSizeFull();
        responsiveLayout.addComponent(viewContainer);
        //root.setExpandRatio(viewContainer, 1.0f);

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addProvider(viewProvider);
        mainContentCol.setComponent(viewContainer);
    }

}

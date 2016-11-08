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
import hu.tb.i18n.I18N;
import hu.tb.i18n.support.TranslatableSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

/**
 * Created by Admin on 2016.11.02..
 */
@SpringUI()
@Theme("valo")
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
        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        //root.setMargin(true);
        //root.setSpacing(true);
        setContent(root);

        TopMenuBar topMenuBar = new TopMenuBar(i18n);
        topMenuBar.setLastTimeStamp(getSession().getLastRequestTimestamp());
        root.addComponent(topMenuBar.getMenubar(getUI()));

        final Panel viewContainer = new Panel();
        viewContainer.setSizeFull();
        root.addComponent(viewContainer);
        root.setExpandRatio(viewContainer, 1.0f);

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addProvider(viewProvider);
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


}

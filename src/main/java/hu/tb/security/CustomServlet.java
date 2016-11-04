package hu.tb.security;

import com.vaadin.server.*;
import com.vaadin.spring.server.SpringVaadinServlet;
import hu.tb.util.PropertyContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;

@Component("vaadinServlet")
public class CustomServlet extends SpringVaadinServlet implements SessionInitListener, SessionDestroyListener {

    private final Logger logger = LoggerFactory.getLogger(CustomServlet.class);

    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();
        getService().setSystemMessagesProvider((SystemMessagesProvider) systemMessagesInfo -> {
            CustomizedSystemMessages messages = new CustomizedSystemMessages();
            // Don't show any messages, redirect immediately to the session expired URL
            messages.setSessionExpiredNotificationEnabled(false);
            // Force a logout to also end the HTTP session and not only the Vaadin session
            messages.setSessionExpiredURL(PropertyContainer.getServerContextPath().concat( "/logout"));
            // Don't show any message, reload the page instead
            messages.setCommunicationErrorNotificationEnabled(false);
            return messages;
        });

        getService().addSessionInitListener(this);
        getService().addSessionDestroyListener(this);
    }

    @Override
    public void sessionDestroy(SessionDestroyEvent sessionDestroyEvent) {
        logger.info("Session destroyed");
    }

    @Override
    public void sessionInit(SessionInitEvent sessionInitEvent) throws ServiceException {
        logger.info("Session created");
    }
}

package hu.tb.components;

import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Configurable;

import java.io.File;

/**
 * Created by Tivadar Bocz on 2016.11.29..
 */
@Configurable
@Deprecated
public class VerticalMenuBar extends VerticalLayout {
    private Label l1;
    private Label l2;
    Image image;

    public VerticalMenuBar() {
        initComponents();
        buildLayout();
    }

    private void buildLayout() {
        setStyleName("red");
        addComponents(image, l1, l2);
        setComponentAlignment(image, Alignment.TOP_CENTER);
    }

    private void initComponents() {
        // Find the application directory
        String basepath = VaadinService.getCurrent()
                .getBaseDirectory().getAbsolutePath();

        // Image as a file resource
        /*FileResource resource = new FileResource(new java.io.File(basepath +
                "/images/anonymous.jpg"));*/
        FileResource resource = new FileResource(new File("C:\\Users\\Admin\\workspace\\github\\springboot_vaadin\\src\\main\\resources\\images\\anonymous.jpg"));
        image = new Image("", resource);
        image.setWidth(75, Unit.PIXELS);
        image.setHeight(100, Unit.PIXELS);

        l1 = new Label("Label 1");
        l2 = new Label("Label 2");
    }

    public void hideComponents() {
        removeAllComponents();
    }

    public void showComponents() {
        initComponents();
        buildLayout();
    }
}

package hu.tb.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by Tivadar Bocz on 2016.11.29..
 */
@Deprecated
public class MainHorizontalSplitPanel extends HorizontalSplitPanel {
    private DashBoard dashBoard;

    public MainHorizontalSplitPanel() {
        buildLayout();
        initComponents();
    }

    private void buildLayout() {
        Button collapseBtn = new Button("", FontAwesome.ANGLE_DOUBLE_LEFT);
        //collapseBtn.addClickListener(collapseClickListener());
        collapseBtn.setSizeFull();
        collapseBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if ((int) getSplitPosition() <= 2) {
                    setSplitPosition(10, Unit.PERCENTAGE);
                    collapseBtn.setIcon(FontAwesome.ANGLE_DOUBLE_LEFT);
                    dashBoard.showComponents();
                } else if ((int) getSplitPosition() >= 10) {
                    setSplitPosition(2, Unit.PERCENTAGE);
                    collapseBtn.setIcon(FontAwesome.ANGLE_DOUBLE_RIGHT);
                    dashBoard.hideComponents();
                }

            }
        });

        VerticalLayout layout = new VerticalLayout();
        dashBoard = new DashBoard();
        layout.addComponents(dashBoard, collapseBtn);

        layout.setComponentAlignment(collapseBtn, Alignment.TOP_CENTER);

        setFirstComponent(layout);
    }

    private void initComponents() {
        getFirstComponent().addStyleName("dashboard");
        setSplitPosition(10, Unit.PERCENTAGE);
        setResponsive(true);
        setLocked(true);
    }


    /*private Button.ClickListener collapseClickListener(){

        return new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                getFirstComponent().setVisible(false);
            }
        };
    }*/

}

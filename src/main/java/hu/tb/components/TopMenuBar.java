package hu.tb.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Component;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import hu.tb.util.PropertyContainer;
import hu.tb.view.ChartView;
import hu.tb.view.DefaultView;
import hu.tb.view.TableView;

/**
 * Created by Tivadar Bocz on 2016.11.03..
 */
public class TopMenuBar {

    private  UI ui;

    public Component getMenubar(UI ui){
        this.ui = ui;
        MenuBar menuBar = new MenuBar();
        menuBar.addItem(Constant.START_VIEW, FontAwesome.HOME, handleMenuBarItemClick);
        menuBar.addItem(Constant.TABLE_VIEW, FontAwesome.TABLE, handleMenuBarItemClick);
        menuBar.addItem(Constant.CHART_VIEW, FontAwesome.BAR_CHART_O, handleMenuBarItemClick);
        //Charts
        MenuBar.MenuItem charts = menuBar.addItem("Charts",FontAwesome.BAR_CHART_O,null);
        charts.addItem("Horizontal bar chart",FontAwesome.LOCK,handleMenuBarItemClick);
        charts.addItem("Vertical bar chart",FontAwesome.LOCK,handleMenuBarItemClick);
        charts.addItem("Line chart",FontAwesome.LOCK,handleMenuBarItemClick);
        charts.addItem("Donut chart",FontAwesome.LOCK,handleMenuBarItemClick);
        charts.addItem("Pie chart",FontAwesome.LOCK,handleMenuBarItemClick);
        charts.addItem("Polar area chart",FontAwesome.LOCK,handleMenuBarItemClick);
        charts.addItem("Bubble chart",FontAwesome.LOCK,handleMenuBarItemClick);
        charts.addItem("Radar chart",FontAwesome.LOCK,handleMenuBarItemClick);
        charts.addItem("Scatter line chart",FontAwesome.LOCK,handleMenuBarItemClick);
        //Question
        menuBar.addItem(Constant.QUESTION_VIEW,FontAwesome.QUESTION_CIRCLE,handleMenuBarItemClick);
        //Sign out
        menuBar.addItem(Constant.LOGOUT_VIEW,FontAwesome.SIGN_OUT,handleMenuBarItemClick);
        return menuBar;
    }

    /**
     * Menu selection is handled by executing a command when the user selects an item from the menu.
     * A command is a call-back class that implements the MenuBar.Command interface.
     */
    private MenuBar.Command handleMenuBarItemClick = new MenuBar.Command() {
        @Override
        public void menuSelected(MenuBar.MenuItem menuItem) {
            switch (menuItem.getText()) {
                case Constant.START_VIEW:
                   ui.getNavigator().navigateTo(DefaultView.VIEW_NAME);
                    break;
                case Constant.TABLE_VIEW:
                   ui.getNavigator().navigateTo(TableView.VIEW_NAME);
                    break;
                case Constant.CHART_VIEW:
                    ui.getNavigator().navigateTo(ChartView.VIEW_NAME);
                    break;
                case Constant.LOGOUT_VIEW:
                    ui.getSession().close();
                    // Redirect to avoid keeping the removed UI open in the browser
                    ui.getPage().setLocation(PropertyContainer.getServerContextPath().concat( "/logout"));
                    break;
                default:
                    Notification notification = new Notification("Menu item is not implemented yet",Notification.Type.TRAY_NOTIFICATION);
                    notification.setStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);
                    notification.setIcon(FontAwesome.WARNING);
                    break;
            }
        }
    };
}

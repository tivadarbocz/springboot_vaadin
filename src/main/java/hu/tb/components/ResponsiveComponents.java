package hu.tb.components;

import com.jarektoro.responsivelayout.ResponsiveColumn;
import com.jarektoro.responsivelayout.ResponsiveLayout;
import com.jarektoro.responsivelayout.ResponsiveRow;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import hu.tb.util.Constant;
import hu.tb.util.PropertyContainer;
import hu.tb.view.ChartView;
import hu.tb.view.DefaultView;
import hu.tb.view.DictionaryTestView;
import hu.tb.view.TableView;

import java.io.File;

/**
 * Created by Tivadar Bocz on 2016.12.01..
 */
public class ResponsiveComponents {

    ResponsiveRow menuBar;
    ResponsiveLayout mainContentLayout;

    public ResponsiveComponents() {

    }

    public ResponsiveRow getMenuBar(UI ui) {
        // The menu itself is just a Row
        // Oh did i mention that rows and columns are nestable - Rad

        menuBar = new ResponsiveRow();
        menuBar.setVerticalSpacing(ResponsiveRow.SpacingSize.SMALL, true);
        // This is the profile image we just set 12
        // because no matter what we want 12/12 spaces taken

        /**
         * Menu bar profile picture
         */
        ResponsiveColumn profileColumn = new ResponsiveColumn(12);
        FileResource resource = new FileResource(new File("C:\\Users\\Admin\\workspace\\github\\springboot_vaadin\\src\\main\\resources\\images\\anonymous.jpg"));
        Image image = new Image("", resource);
        image.setWidth(75, Sizeable.Unit.PIXELS);
        image.setHeight(100, Sizeable.Unit.PIXELS);

        profileColumn.setComponent(image);
        //profileCol.setAlignment(ResponsiveColumn.ColumnComponentAlignment.CENTER);

        // For the menu buttons they need to be going from top to bottom
        // while on a computer then go left to right while on tablet then
        // go back to top and bottom while on phones

        // Because Rows wrap there content
        // if each Column has a width of 12
        // they will just stack on top of each other
        // so for mobile and computers we say 12

        // For tablets we give each column 3
        // 3+3+3+3 = 12
        // 4 buttons will fit perfectly in one row

        /**
         * Menubar home button
         */
        ResponsiveColumn homeColumn = new ResponsiveColumn(12, 3, 12, 12);
        Button homeButton = new Button("", FontAwesome.HOME);
        homeButton.addStyleName(ValoTheme.BUTTON_HUGE);
        homeButton.setSizeFull();
        homeButton.addClickListener(menuElementsonClickListener(Constant.START_VIEW, ui));
        homeColumn.setComponent(homeButton);

        /**
         * Menubar table button
         */
        Button tableButton = new Button("", FontAwesome.TABLE);
        tableButton.addStyleName(ValoTheme.BUTTON_HUGE);
        tableButton.setSizeFull();
        tableButton.addClickListener(menuElementsonClickListener(Constant.TABLE_VIEW, ui));
        ResponsiveColumn tableColumn = new ResponsiveColumn(12, 3, 12, 12);
        tableColumn.setComponent(tableButton);

        /**
         * Menubar charts button
         */
        Button chartButton = new Button("", FontAwesome.BAR_CHART_O);
        chartButton.addStyleName(ValoTheme.BUTTON_HUGE);
        chartButton.setSizeFull();
        chartButton.addClickListener(menuElementsonClickListener(Constant.CHART_VIEW, ui));
        ResponsiveColumn chartColumn = new ResponsiveColumn(12, 3, 12, 12);
        chartColumn.setComponent(chartButton);
        // We can also set the visibility depending on what screen they are on
        // this hids the menu buttons when on mobile
        // chartColumn.setVisibility(ResponsiveLayout.DisplaySize.XS, false);


        /**
         * Menubar dictionary test button
         */
        Button dictionaryTestButton = new Button("", FontAwesome.TASKS);
        dictionaryTestButton.addStyleName(ValoTheme.BUTTON_HUGE);
        dictionaryTestButton.setSizeFull();
        dictionaryTestButton.addClickListener(menuElementsonClickListener(Constant.DICTIONARY_TEST_VIEW, ui));
        ResponsiveColumn dictionaryTestColumn = new ResponsiveColumn(12, 3, 12, 12);
        dictionaryTestColumn.setComponent(dictionaryTestButton);

        /**
         * Menubar Logout button
         */
        Button logoutButton = new Button("", FontAwesome.POWER_OFF);
        logoutButton.addStyleName(ValoTheme.BUTTON_HUGE);
        logoutButton.setSizeFull();
        logoutButton.addClickListener(menuElementsonClickListener(Constant.LOGOUT_VIEW, ui));
        ResponsiveColumn logoutColumn = new ResponsiveColumn(12, 3, 12, 12);
        logoutColumn.setComponent(logoutButton);

        menuBar.addColumn(profileColumn);
        menuBar.addColumn(homeColumn);
        menuBar.addColumn(tableColumn);
        menuBar.addColumn(chartColumn);
        menuBar.addColumn(dictionaryTestColumn);
        menuBar.addColumn(logoutColumn);

        return menuBar;
    }

    public ResponsiveLayout getDummyMainContentResponsiveLayout() {

// Here we are creating a new responsiveLayout to house the multiple rows
        // for the main content

        mainContentLayout = new ResponsiveLayout();
        // simple row with one column that takes 3/12 spaces
        // and then the row centers that column to the middle
        ResponsiveRow titleRow = new ResponsiveRow();
        titleRow.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        Label title = new Label("Test Subjects");
        titleRow.setMargin(true);


        ResponsiveColumn titleCol = new ResponsiveColumn(3);
        titleCol.setComponent(title);
        titleRow.addColumn(titleCol);

        mainContentLayout.addRow(titleRow);


        // Here we have a new Row just for the test subjects

        ResponsiveRow testSubjectsRow = new ResponsiveRow();

        for (int x = 0; x < 10; x++) {

            // We want each column to take
            // 12/12 on mobile
            // 6/12 on tablet
            // 4/12 on computer screens
            // 3/12 on wide computer screens
            Button btn = new Button("", FontAwesome.EDIT);
            btn.addStyleName(ValoTheme.BUTTON_FRIENDLY);
            btn.setSizeFull();

            Panel panel = new Panel(btn);
            panel.setSizeFull();
            ResponsiveColumn testerCol = new ResponsiveColumn(12, 6, 4, 3);
            testerCol.setComponent(btn);
            testSubjectsRow.addColumn(testerCol);

        }

        // sets spacing between the columns and margin around the whole row

        testSubjectsRow.setHorizontalSpacing(true);
        testSubjectsRow.setVerticalSpacing(true);
        testSubjectsRow.setMargin(true);

        mainContentLayout.addRow(testSubjectsRow);

        return mainContentLayout;
    }

    public ResponsiveLayout getRootLayout() {
        ResponsiveLayout responsiveLayout = new ResponsiveLayout();
        responsiveLayout.setSizeFull();
        responsiveLayout.setScrollable(true);

        return responsiveLayout;
    }

    private Button.ClickListener menuElementsonClickListener(String name, UI ui) {

        return e -> {
            switch (name) {
                case Constant.START_VIEW:
                    ui.getNavigator().navigateTo(DefaultView.VIEW_NAME);
                    break;
                case Constant.TABLE_VIEW:
                    ui.getNavigator().navigateTo(TableView.VIEW_NAME);
                    break;
                case Constant.CHART_VIEW:
                    ui.getNavigator().navigateTo(ChartView.VIEW_NAME);
                    break;
                case Constant.DICTIONARY_TEST_VIEW:
                    ui.getNavigator().navigateTo(DictionaryTestView.VIEW_NAME);
                    break;
                case Constant.LOGOUT_VIEW:
                    ui.getSession().close();
                    // Redirect to avoid keeping the removed UI open in the browser
                    ui.getPage().setLocation(PropertyContainer.getServerContextPath().concat("/logout"));
                    break;
                default:
                    Notification notification = new Notification("Menu item is not implemented yet", Notification.Type.TRAY_NOTIFICATION);
                    notification.setStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);
                    notification.setIcon(FontAwesome.WARNING);
                    break;
            }
        };
    }
}

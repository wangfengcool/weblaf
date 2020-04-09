package com.alee.demo.test;

import com.alee.api.jdk.SerializableSupplier;
import com.alee.demo.skin.DemoStyles;
import com.alee.extended.dock.SidebarButtonVisibility;
import com.alee.extended.dock.WebDockablePane;
import com.alee.extended.link.WebLink;
import com.alee.extended.statusbar.WebStatusBar;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.button.WebButton;
import com.alee.laf.rootpane.WindowState;
import com.alee.laf.toolbar.WebToolBar;
import com.alee.laf.window.WebFrame;
import com.alee.managers.notification.NotificationManager;
import com.alee.managers.settings.Configuration;
import com.alee.managers.settings.SettingsManager;
import com.alee.managers.style.StyleId;
import com.alee.utils.CoreSwingUtils;

import javax.swing.*;
import java.awt.*;

public class DemoHome extends WebFrame {

    private WebDockablePane dockablePane;

    private static DemoHome instance;

    public DemoHome() {
        super();

        setIconImages ( WebLookAndFeel.getImages () );


        initializeDocks();
        initializeToolBar ();
        initializeDocks();

        initializeStatus();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        registerSettings(new Configuration<WindowState>( "application", new SerializableSupplier<WindowState>()
        {
            @Override
            public WindowState get ()
            {
                return new WindowState ( new Dimension( 1200, 820 ) );
            }
        } ) );
    }

    private void initializeDocks (){
        dockablePane = new WebDockablePane ( StyleId.dockablepaneCompact );
        dockablePane.setSidebarButtonVisibility ( SidebarButtonVisibility.anyMinimized );

        add ( dockablePane, BorderLayout.CENTER);
    }

    private void initializeToolBar(){
        final WebToolBar toolBar = new WebToolBar ( StyleId.toolbarAttachedNorth );
        toolBar.setFloatable ( false );
        WebButton webButton = new WebButton(">>>");
        toolBar.add(webButton);
        toolBar.addSeparator ();
        add ( toolBar, BorderLayout.NORTH );
    }
    private void initializeStatus (){
        final WebStatusBar statusBar = new WebStatusBar ();

        statusBar.add ( new WebLink( DemoStyles.resourceLink, "link") );

        add ( statusBar, BorderLayout.SOUTH );

        // Custom status bar margin for notification manager
        NotificationManager.setMargin ( 0, 0, statusBar.getPreferredSize ().height, 0 );
    }

    public static DemoHome getInstance ()
    {
        if ( instance == null )
        {
            instance = new DemoHome ();
        }
        return instance;
    }

    public void display ()
    {
        setVisible ( true );
    }

    public static void main(String[] args) {
        CoreSwingUtils.enableEventQueueLogging ();
        CoreSwingUtils.invokeAndWait(()->{
            // Configuring settings location
            SettingsManager.setDefaultSettingsDirName ( ".weblaf-demo" );
            SettingsManager.setDefaultSettingsGroup ( "WebLookAndFeelDemo" );
            SettingsManager.setSaveOnChange ( true );


            // Installing Look and Feel
            WebLookAndFeel.setForceSingleEventsThread ( true );
            WebLookAndFeel.install ();
            DemoHome.getInstance().display();
        });
    }
}

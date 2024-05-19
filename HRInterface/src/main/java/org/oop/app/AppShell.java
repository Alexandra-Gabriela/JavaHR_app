package org.oop.app;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.AppShellSettings;
import com.vaadin.flow.server.PWA;

/**
 * This class is used to configure the generated html host page used by the app
 */
@PWA(name = "My Application", shortName = "My Application")
public class AppShell implements AppShellConfigurator {

    @Override
    public void configurePage(AppShellSettings settings) {
        settings.addMetaTag("viewport", "width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no");
    }
}
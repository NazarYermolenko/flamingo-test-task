package com.flamingo.framework.ui.browser;

import com.flamingo.framework.ui.config.ConfigKey;
import com.flamingo.framework.ui.config.ConfigReader;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;

public class ContextFactory {
    private final Browser browser;
    private final ConfigReader configReader;

    public ContextFactory(Browser browser, ConfigReader configReader) {
        this.browser = browser;
        this.configReader = configReader;
    }

    public BrowserContext createContext() {
        int width = Integer.parseInt(configReader.getProperty(ConfigKey.VIEWPORT_WIDTH));
        int height = Integer.parseInt(configReader.getProperty(ConfigKey.VIEWPORT_HEIGHT));
        return browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(width, height));
    }
}

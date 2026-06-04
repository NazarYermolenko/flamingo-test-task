package com.flamingo.framework.ui.browser;

import com.flamingo.framework.ui.config.ConfigKey;
import com.flamingo.framework.ui.config.ConfigReader;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class BrowserFactory {
    private final Playwright playwright;
    private final ConfigReader configReader;

    public BrowserFactory(Playwright playwright, ConfigReader configReader) {
        this.playwright = playwright;
        this.configReader = configReader;
    }

    public Browser createBrowser() {
        String browserType = configReader.getProperty(ConfigKey.BROWSER).toLowerCase();
        boolean headless = Boolean.parseBoolean(configReader.getProperty(ConfigKey.HEADLESS));

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(headless);

        switch (browserType) {
            case "firefox":
                return playwright.firefox().launch(options);
            case "webkit":
                return playwright.webkit().launch(options);
            case "chromium":
            default:
                return playwright.chromium().launch(options);
        }
    }
}

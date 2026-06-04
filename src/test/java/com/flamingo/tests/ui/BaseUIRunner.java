package com.flamingo.tests.ui;

import com.flamingo.framework.ui.browser.BrowserFactory;
import com.flamingo.framework.ui.browser.ContextFactory;
import com.flamingo.framework.ui.config.ConfigReader;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestResultWatcher.class)
public class BaseUIRunner {
    protected static Playwright playwright;
    protected static Browser browser;
    protected static ConfigReader configReader;
    protected BrowserContext context;
    public Page page;

    @BeforeAll
    public static void setUpBrowser() {
        playwright = Playwright.create();
        configReader = new ConfigReader();
        
        BrowserFactory browserFactory = new BrowserFactory(playwright, configReader);
        browser = browserFactory.createBrowser();
    }

    @AfterAll
    public static void tearDownBrowser() {
        if (browser != null) {
            browser.close();
            browser = null;
        }
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }

    @BeforeEach
    public void setUpContext() {
        ContextFactory contextFactory = new ContextFactory(browser, configReader);
        context = contextFactory.createContext();
        page = context.newPage();
    }

    @AfterEach
    public void tearDownContext() {
        if (page != null) {
            page.close();
            page = null;
        }
        if (context != null) {
            context.close();
            context = null;
        }
    }
}

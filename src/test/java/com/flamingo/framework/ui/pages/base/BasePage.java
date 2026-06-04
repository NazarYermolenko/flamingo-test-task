package com.flamingo.framework.ui.pages.base;

import com.flamingo.framework.ui.config.ConfigReader;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public abstract class BasePage {
    protected final Page page;
    protected final ConfigReader configReader;

    public BasePage(Page page, ConfigReader configReader) {
        this.page = page;
        this.configReader = configReader;
    }

    public void open(String url) {
        page.navigate(url);
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }
}

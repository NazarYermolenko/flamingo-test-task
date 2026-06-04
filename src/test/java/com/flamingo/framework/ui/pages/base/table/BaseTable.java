package com.flamingo.framework.ui.pages.base.table;

import com.microsoft.playwright.Locator;

public abstract class BaseTable {
    protected final Locator root;
    protected final Locator headerRow;
    protected final Locator bodyRows;

    public BaseTable(Locator root) {
        this.root = root;
        this.headerRow = root.locator("thead tr");
        this.bodyRows = root.locator("tbody tr");
    }

    

}

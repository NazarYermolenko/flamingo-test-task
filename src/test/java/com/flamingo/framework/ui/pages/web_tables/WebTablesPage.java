package com.flamingo.framework.ui.pages.web_tables;

import com.flamingo.framework.ui.config.ConfigKey;
import com.flamingo.framework.ui.config.ConfigReader;
import com.flamingo.framework.ui.pages.base.BasePage;
import com.flamingo.framework.ui.pages.web_tables.create_record_modal.CreateRecordModal;
import com.flamingo.framework.ui.pages.web_tables.table.Table;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class WebTablesPage extends BasePage {
    
    private final Locator addNewRecordButton;
    private final Locator tableRoot;
    private final Locator searchBox;

    public WebTablesPage(Page page, ConfigReader configReader) {
        super(page, configReader);
        this.addNewRecordButton = page.locator("#addNewRecordButton");
        this.tableRoot = page.locator("table");
        this.searchBox = page.locator("#searchBox");
    }

    public void open() {
        String url = configReader.getProperty(ConfigKey.WEBTABLES_URL);
        open(url);
    }

    public CreateRecordModal clickAddRecord() {
        addNewRecordButton.click();
        return new CreateRecordModal(page);
    }

    public void searchFor(String query) {
        searchBox.fill(query);
    }

    public void clearSearch() {
        searchBox.click();
        searchBox.press("Control+A");
        searchBox.press("Backspace");
        searchBox.press("Backspace");
    }

    public Table getTable() {
        return new Table(page, this.tableRoot);
    }
}

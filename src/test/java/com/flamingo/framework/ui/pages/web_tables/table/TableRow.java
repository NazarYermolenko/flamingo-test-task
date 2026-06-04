package com.flamingo.framework.ui.pages.web_tables.table;

import com.flamingo.framework.ui.pages.web_tables.create_record_modal.CreateRecordModal;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class TableRow {
    private final Page page;
    private final Locator root;

    public TableRow(Page page, Locator root) {
        this.page = page;
        this.root = root;
    }

    public CreateRecordModal edit() {
        this.root.locator("[id^='edit-record']").click();
        return new CreateRecordModal(page);
    }

    public void delete() {
        this.root.locator("[id^='delete-record']").click();
    }

    public Locator getLocator() {
        return this.root;
    }

    public Locator getCell(int colIndex) {
        return this.root.locator("td").nth(colIndex);
    }

    @Override
    public String toString() {
        return this.root.innerText();
    }
}

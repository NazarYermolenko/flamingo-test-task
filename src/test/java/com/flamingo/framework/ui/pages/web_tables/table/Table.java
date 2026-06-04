package com.flamingo.framework.ui.pages.web_tables.table;

import java.util.List;

import com.flamingo.framework.ui.pages.base.table.BaseTable;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Table extends BaseTable {
    private final Page page;

    public Table(Page page, Locator root) {
        super(root);
        this.page = page;
    }

    public List<TableRow> getRows() {
        return bodyRows.all().stream()
            .map(row -> new TableRow(page, row))
            .toList();
    } 

    public TableRow getRowWithText(List<String> texts) {
        Locator matchedRow = bodyRows;
        for (String text : texts) {
            matchedRow = matchedRow.filter(new Locator.FilterOptions().setHasText(text));
        }
        matchedRow = matchedRow.first();
        matchedRow.waitFor();
        return new TableRow(page, matchedRow);
    }

    public TableRow getRowWithText(String text) {
        return getRowWithText(List.of(text));
    }

    public TableRow getRow(int index) {
        return new TableRow(page, bodyRows.nth(index));
    }

    public TableRow getRow(String text) {
        return new TableRow(page, bodyRows.filter(new Locator.FilterOptions().setHasText(text)));
    }
}

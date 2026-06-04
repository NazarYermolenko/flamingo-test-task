package com.flamingo.tests.ui.webtables;

import com.flamingo.framework.ui.pages.web_tables.WebTablesPage;
import com.flamingo.tests.ui.BaseUIRunner;
import org.junit.jupiter.api.BeforeEach;

public class WebTablesRunner extends BaseUIRunner {
    protected WebTablesPage webTablesPage;

    @BeforeEach
    public void setUpWebTables() {
        webTablesPage = new WebTablesPage(page, configReader);
    }
}

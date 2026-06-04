package com.flamingo.framework.ui.pages.practice_form;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SuccessModal {
    private final Locator modalContent;
    private final Locator title;

    public SuccessModal(Page page) {
        this.modalContent = page.locator(".modal-content");
        this.title = page.locator("#example-modal-sizes-title-lg");
    }

    public void waitForLoad() {
        modalContent.waitFor();
    }

    public Locator getLocator() {
        return modalContent;
    }

    public String getTitle() {
        return title.textContent();
    }

    public String getFieldValue(SuccessModalField field) {
        Locator row = modalContent.locator("tbody tr").filter(new Locator.FilterOptions().setHasText(field.getLabel()));
        return row.locator("td").nth(1).textContent();
    }
}

package com.flamingo.framework.ui.pages.web_tables.create_record_modal;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CreateRecordModal {
    private final Locator modalContent;
    private final Locator firstNameInput;
    private final Locator lastNameInput;
    private final Locator emailInput;
    private final Locator ageInput;
    private final Locator salaryInput;
    private final Locator departmentInput;
    private final Locator submitButton;

    public CreateRecordModal(Page page) {
        this.modalContent = page.locator(".modal-content");
        this.firstNameInput = page.locator("#firstName");
        this.lastNameInput = page.locator("#lastName");
        this.emailInput = page.locator("#userEmail");
        this.ageInput = page.locator("#age");
        this.salaryInput = page.locator("#salary");
        this.departmentInput = page.locator("#department");
        this.submitButton = page.locator("#submit");
    }

    public void waitForLoad() {
        modalContent.waitFor();
    }

    public void fillForm(CreateRecordDTO record) {
        if (record.getFirstName() != null) {
            firstNameInput.fill(record.getFirstName());
        }
        if (record.getLastName() != null) {
            lastNameInput.fill(record.getLastName());
        }
        if (record.getUserEmail() != null) {
            emailInput.fill(record.getUserEmail());
        }
        if (record.getAge() != null) {
            ageInput.fill(record.getAge());
        }
        if (record.getSalary() != null) {
            salaryInput.fill(record.getSalary());
        }
        if (record.getDepartment() != null) {
            departmentInput.fill(record.getDepartment());
        }
    }

    public void submit() {
        submitButton.click();
    }

    public void clickSubmit() {
        submitButton.click();
    }
}

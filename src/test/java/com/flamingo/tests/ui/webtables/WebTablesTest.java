package com.flamingo.tests.ui.webtables;

import com.flamingo.framework.ui.pages.web_tables.create_record_modal.CreateRecordModal;
import com.flamingo.framework.ui.pages.web_tables.create_record_modal.CreateRecordDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class WebTablesTest extends WebTablesRunner {

    @Test
    @DisplayName("Perform Web Table Operations: Add, Edit, Delete, Search, and Sort")
    void testWebTableOperations() {
        webTablesPage.open();

        // 1. Add a new record
        CreateRecordDTO recordToCreate = CreateRecordDTO.builder()
                .firstName("Alice")
                .lastName("Smith")
                .userEmail("alice.smith@example.com")
                .age("28")
                .salary("85000")
                .department("Finance")
                .build();

        CreateRecordModal modal = webTablesPage.clickAddRecord();
        modal.waitForLoad();
        modal.fillForm(recordToCreate);
        modal.submit();

        var createdRecordRow = webTablesPage.getTable()
            .getRowWithText(recordToCreate.getUserEmail());

        // Verify the record is in the table
        assertThat(createdRecordRow.getLocator()).isVisible();
        org.assertj.core.api.Assertions.assertThat(createdRecordRow.toString())
                .contains(recordToCreate.getFirstName(), recordToCreate.getLastName(), recordToCreate.getAge(), recordToCreate.getSalary(), recordToCreate.getDepartment());

        // 2. Edit existing record
        var editedRecord = recordToCreate.toBuilder()
                .firstName("Alicia")
                .department("Treasury")
                .build();

        CreateRecordModal editModal = createdRecordRow.edit();
        editModal.waitForLoad();
        editModal.fillForm(editedRecord);
        editModal.submit();

        var editedRow = webTablesPage.getTable().getRowWithText(
            List.of(editedRecord.getFirstName(), editedRecord.getLastName(), editedRecord.getDepartment())
        );

        webTablesPage.searchFor(editedRecord.getFirstName());
        assertThat(editedRow.getLocator()).isVisible();

        webTablesPage.clearSearch();
        editedRow.delete();

        webTablesPage.searchFor(editedRecord.getUserEmail());
        assertThat(webTablesPage.getTable().getRow(editedRecord.getUserEmail()).getLocator()).isHidden();

    }
}

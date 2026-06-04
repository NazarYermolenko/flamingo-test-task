package com.flamingo.framework.ui.pages.web_tables.create_record_modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecordDTO {
    private String firstName;
    private String lastName;
    private String userEmail;
    private String age;
    private String salary;
    private String department;
}

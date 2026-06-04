package com.flamingo.framework.ui.pages.practice_form;

public enum SuccessModalField {
    STUDENT_NAME("Student Name"),
    STUDENT_EMAIL("Student Email"),
    GENDER("Gender"),
    MOBILE("Mobile"),
    DATE_OF_BIRTH("Date of Birth"),
    SUBJECTS("Subjects"),
    HOBBIES("Hobbies"),
    PICTURE("Picture"),
    ADDRESS("Address"),
    STATE_AND_CITY("State and City");

    private final String label;

    SuccessModalField(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

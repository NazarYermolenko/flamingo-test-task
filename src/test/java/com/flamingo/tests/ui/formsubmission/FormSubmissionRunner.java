package com.flamingo.tests.ui.formsubmission;

import com.flamingo.framework.ui.pages.practice_form.PracticeFormPage;
import com.flamingo.tests.ui.BaseUIRunner;
import org.junit.jupiter.api.BeforeEach;

public class FormSubmissionRunner extends BaseUIRunner {
    protected PracticeFormPage practiceFormPage;

    @BeforeEach
    public void setUpFormSubmission() {
        practiceFormPage = new PracticeFormPage(page, configReader);
    }
}

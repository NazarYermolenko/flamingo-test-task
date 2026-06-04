package com.flamingo.tests.ui.formsubmission;

import com.flamingo.framework.ui.pages.practice_form.PracticeFormDTO;
import com.flamingo.framework.ui.pages.practice_form.SuccessModal;
import com.flamingo.framework.ui.pages.practice_form.SuccessModalField;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class FormSubmissionTest extends FormSubmissionRunner {

    @Test
    @DisplayName("Submit Practice Form and Verify Success Modal")
    void testFormSubmission() {
        practiceFormPage.open();

        PracticeFormDTO formData = PracticeFormDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .userEmail("john.doe@example.com")
                .gender("Male")
                .userNumber("1234567890")
                .dateOfBirthDay("15")
                .dateOfBirthMonth("5") // June (0-based)
                .dateOfBirthYear("1995")
                .subjects(List.of("Maths"))
                .hobbies(List.of("Sports", "Reading"))
                .picturePath("src/test/resources/ui/sample-upload.txt")
                .currentAddress("123 Main Street, Apt 4B")
                .state("NCR")
                .city("Delhi")
                .build();

        practiceFormPage.fillForm(formData);
        SuccessModal successModal = practiceFormPage.submit();
        successModal.waitForLoad();

        // Verify Success Modal is visible and title is correct
        assertThat(successModal.getLocator()).isVisible();
        assertThat(successModal.getTitle()).isEqualTo("Thanks for submitting the form");

        // Verify Submitted Data using fields enum and formatted getters
        assertThat(successModal.getFieldValue(SuccessModalField.STUDENT_NAME)).isEqualTo(formData.getFullName());
        assertThat(successModal.getFieldValue(SuccessModalField.STUDENT_EMAIL)).isEqualTo(formData.getUserEmail());
        assertThat(successModal.getFieldValue(SuccessModalField.GENDER)).isEqualTo(formData.getGender());
        assertThat(successModal.getFieldValue(SuccessModalField.MOBILE)).isEqualTo(formData.getUserNumber());
        assertThat(successModal.getFieldValue(SuccessModalField.DATE_OF_BIRTH)).isEqualTo(formData.getFormattedDateOfBirth());
        assertThat(successModal.getFieldValue(SuccessModalField.SUBJECTS)).isEqualTo(formData.getFormattedSubjects());
        assertThat(successModal.getFieldValue(SuccessModalField.HOBBIES)).isEqualTo(formData.getFormattedHobbies());
        assertThat(successModal.getFieldValue(SuccessModalField.PICTURE)).isEqualTo(formData.getPictureFileName());
        assertThat(successModal.getFieldValue(SuccessModalField.ADDRESS)).isEqualTo(formData.getCurrentAddress());
        assertThat(successModal.getFieldValue(SuccessModalField.STATE_AND_CITY)).isEqualTo(formData.getFormattedStateAndCity());
    }
}

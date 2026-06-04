package com.flamingo.framework.ui.pages.practice_form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PracticeFormDTO {
    private String firstName;
    private String lastName;
    private String userEmail;
    private String gender; // Male, Female, Other
    private String userNumber;
    private String dateOfBirthDay; // e.g. "15"
    private String dateOfBirthMonth; // e.g. "5" (for June)
    private String dateOfBirthYear; // e.g. "1995"
    private List<String> subjects;
    private List<String> hobbies; // Sports, Reading, Music
    private String picturePath;
    private String currentAddress;
    private String state;
    private String city;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFormattedDateOfBirth() {
        if (dateOfBirthDay == null || dateOfBirthMonth == null || dateOfBirthYear == null) {
            return "";
        }
        int monthValue = Integer.parseInt(dateOfBirthMonth) + 1;
        String monthName = java.time.Month.of(monthValue)
                .getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH);
        return String.format("%s %s,%s", dateOfBirthDay, monthName, dateOfBirthYear);
    }

    public String getFormattedSubjects() {
        return subjects == null ? "" : String.join(", ", subjects);
    }

    public String getFormattedHobbies() {
        return hobbies == null ? "" : String.join(", ", hobbies);
    }

    public String getPictureFileName() {
        if (picturePath == null) {
            return "";
        }
        return java.nio.file.Paths.get(picturePath).getFileName().toString();
    }

    public String getFormattedStateAndCity() {
        return state + " " + city;
    }
}

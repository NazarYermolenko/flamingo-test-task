package com.flamingo.framework.ui.pages.practice_form;

import com.flamingo.framework.ui.config.ConfigKey;
import com.flamingo.framework.ui.config.ConfigReader;
import com.flamingo.framework.ui.pages.base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;
import java.util.regex.Pattern;

public class PracticeFormPage extends BasePage {

    private final Locator firstNameInput;
    private final Locator lastNameInput;
    private final Locator emailInput;
    private final Locator userNumberInput;
    private final Locator dateOfBirthInput;
    private final Locator subjectsInput;
    private final Locator uploadPictureInput;
    private final Locator currentAddressInput;
    private final Locator stateDropdown;
    private final Locator stateInput;
    private final Locator cityDropdown;
    private final Locator cityInput;
    private final Locator submitButton;

    public PracticeFormPage(Page page, ConfigReader configReader) {
        super(page, configReader);
        this.firstNameInput = page.locator("#firstName");
        this.lastNameInput = page.locator("#lastName");
        this.emailInput = page.locator("#userEmail");
        this.userNumberInput = page.locator("#userNumber");
        this.dateOfBirthInput = page.locator("#dateOfBirthInput");
        this.subjectsInput = page.locator("#subjectsInput");
        this.uploadPictureInput = page.locator("#uploadPicture");
        this.currentAddressInput = page.locator("#currentAddress");
        this.stateDropdown = page.locator("#state");
        this.stateInput = page.locator("#state input");
        this.cityDropdown = page.locator("#city");
        this.cityInput = page.locator("#city input");
        this.submitButton = page.locator("#submit");
    }

    public void open() {
        String url = configReader.getProperty(ConfigKey.PRACTICEFORM_URL);
        open(url);
        removeAdsAndBanners();
    }

    public void removeAdsAndBanners() {
        page.evaluate("() => {" +
                "  const ads = document.querySelectorAll('#close-fixedban, footer, iframe, [id^=google_ads_iframe]');" +
                "  ads.forEach(el => el.remove());" +
                "}");
    }

    public void fillForm(PracticeFormDTO data) {
        if (data.getFirstName() != null) {
            firstNameInput.fill(data.getFirstName());
        }
        if (data.getLastName() != null) {
            lastNameInput.fill(data.getLastName());
        }
        if (data.getUserEmail() != null) {
            emailInput.fill(data.getUserEmail());
        }
        if (data.getGender() != null) {
            selectGender(data.getGender());
        }
        if (data.getUserNumber() != null) {
            userNumberInput.fill(data.getUserNumber());
        }
        if (data.getDateOfBirthDay() != null && data.getDateOfBirthMonth() != null && data.getDateOfBirthYear() != null) {
            setDateOfBirth(data.getDateOfBirthDay(), data.getDateOfBirthMonth(), data.getDateOfBirthYear());
        }
        if (data.getSubjects() != null) {
            for (String subject : data.getSubjects()) {
                addSubject(subject);
            }
        }
        if (data.getHobbies() != null) {
            for (String hobby : data.getHobbies()) {
                selectHobby(hobby);
            }
        }
        if (data.getPicturePath() != null) {
            uploadPicture(data.getPicturePath());
        }
        if (data.getCurrentAddress() != null) {
            currentAddressInput.fill(data.getCurrentAddress());
        }
        if (data.getState() != null) {
            selectState(data.getState());
        }
        if (data.getCity() != null) {
            selectCity(data.getCity());
        }
    }

    public void selectGender(String gender) {
        page.locator("#genterWrapper label")
                .filter(new Locator.FilterOptions().setHasText(Pattern.compile("^\\s*" + gender + "\\s*$")))
                .click();
    }

    public void selectHobby(String hobby) {
        page.locator("#hobbiesWrapper label")
                .filter(new Locator.FilterOptions().setHasText(Pattern.compile("^\\s*" + hobby + "\\s*$")))
                .click();
    }

    public void setDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();
        page.locator(".react-datepicker__month-select").selectOption(month);
        page.locator(".react-datepicker__year-select").selectOption(year);
        String daySelector = String.format(".react-datepicker__day--%03d:not(.react-datepicker__day--outside-month)", Integer.parseInt(day));
        page.locator(daySelector).click();
    }

    public void addSubject(String subject) {
        subjectsInput.fill(subject);
        subjectsInput.press("Enter");
    }

    public void uploadPicture(String path) {
        uploadPictureInput.setInputFiles(Paths.get(path));
    }

    public void selectState(String state) {
        stateDropdown.click();
        stateInput.fill(state);
        stateInput.press("Enter");
    }

    public void selectCity(String city) {
        cityDropdown.click();
        cityInput.fill(city);
        cityInput.press("Enter");
    }

    public SuccessModal submit() {
        submitButton.scrollIntoViewIfNeeded();
        submitButton.click();
        return new SuccessModal(page);
    }
}

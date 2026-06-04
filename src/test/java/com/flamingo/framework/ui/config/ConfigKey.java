package com.flamingo.framework.ui.config;

public enum ConfigKey {
    BROWSER("browser", "chromium"),
    HEADLESS("headless", "false"),
    VIEWPORT_WIDTH("viewportWidth", "1920"),
    VIEWPORT_HEIGHT("viewportHeight", "1080"),
    WEBTABLES_URL("webtables.url", ""),
    PRACTICEFORM_URL("practiceform.url", "");

    private final String key;
    private final String defaultValue;

    ConfigKey(String key, String defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public String getKey() {
        return key;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}

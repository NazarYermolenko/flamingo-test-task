package com.flamingo.tests.ui;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class TestResultWatcher implements TestWatcher {
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Object testInstance = context.getRequiredTestInstance();
        if (testInstance instanceof BaseUIRunner) {
            BaseUIRunner runner = (BaseUIRunner) testInstance;
            if (runner.page != null) {
                try {
                    byte[] screenshot = runner.page.screenshot();
                    saveScreenshot(context.getDisplayName() + "_failed", screenshot);
                } catch (Exception e) {
                    System.err.println("Failed to take screenshot: " + e.getMessage());
                }
            }
        }
    }

    @Attachment(value = "{name}", type = "image/png")
    public byte[] saveScreenshot(String name, byte[] screenshot) {
        return screenshot;
    }
}

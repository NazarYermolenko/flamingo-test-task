package com.flamingo.tests.api.restfulbooker;

import com.flamingo.tests.api.BaseApiTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseRestfulBookerTest extends BaseApiTest {

    protected String token;

    @BeforeAll
    void setup() {
        token = api.restfulBooker().auth().login("admin", "password123");
    }
}

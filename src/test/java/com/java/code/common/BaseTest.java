package com.java.code.common;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {

    protected SoftAssertJ softAssert;
    
    @BeforeClass
    public void beforeClass() {
        softAssert = SoftAssertJ.getInstance();
    }

    @AfterClass
    public void afterClass() {
        try {
            softAssert.assertAll();
        } catch (Error e) {
        }
    }
}

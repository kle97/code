package com.java.code.common;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {

    protected SoftAssertJ softAssert;
    
    @BeforeClass
    public void beforeClass() {
        softAssert = new SoftAssertJ();
    }

    @AfterClass
    public void afterClass() {
        softAssert.assertAll();
    }
}

package com.java.code.common;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

public abstract class BaseTest {

    protected SoftAssert softAssert;
    
    @BeforeClass
    public void beforeClass() {
        softAssert = new SoftAssertion();
    }

    @AfterClass
    public void afterClass() {
        softAssert.assertAll();
    }
}

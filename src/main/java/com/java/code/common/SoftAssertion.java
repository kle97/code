package com.java.code.common;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

@Slf4j
public class SoftAssertion extends SoftAssert {

    @Override
    public void onAssertSuccess(IAssert<?> assertCommand) {
        Logger logger = LoggerFactory.getLogger(Reporter.getCurrentTestResult().getMethod().getTestClass().getRealClass());
        logger.info("    " + AnsiColor.GREEN_BOLD + "PASS" + AnsiColor.RESET + "    " + assertCommand.getMessage()
                            + " expected [" + objectToString(assertCommand.getExpected()) + "]");
    }

    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
        Logger logger = LoggerFactory.getLogger(Reporter.getCurrentTestResult().getMethod().getTestClass().getRealClass());
        logger.error("    " + AnsiColor.RED_BOLD + "FAIL" + AnsiColor.RESET + "    " + assertCommand.getMessage() 
                             + " expected [" + objectToString(assertCommand.getExpected()) 
                             + "] but found [" + objectToString(assertCommand.getActual()) + "]");
    }
    
    private <T> String objectToString(T object) {
        String result;
        Class<?> objectClass = object.getClass();
        if (objectClass.isArray()) {
            try {
                result = Arrays.deepToString((Object[]) object);
            } catch (ClassCastException e) {
                result = Arrays.deepToString(new Object[]{object});
            }
            result = result.replaceAll("[\\[\\]]", "");
        } else {
            result = String.valueOf(object);
        }
        
        return result;
    }
}

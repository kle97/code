package com.java.code.common;

import lombok.extern.slf4j.Slf4j;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

@Slf4j
public class SoftAssertion extends SoftAssert {

    @Override
    public void onAssertSuccess(IAssert<?> assertCommand) {
        String expected = assertCommand.getExpected().toString();
        String message = assertCommand.getMessage();
        log.info("  " + AnsiColor.GREEN_BOLD + "PASS" + AnsiColor.RESET + "   " + message + " expected [" + expected + "]");
    }

    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
        String actual = assertCommand.getActual().toString();
        String expected = assertCommand.getExpected().toString();
        String message = assertCommand.getMessage();
        log.error("  " + AnsiColor.RED_BOLD + "FAIL" + AnsiColor.RESET + "   " 
                         + message + " expected [" + expected + "] but found [" + actual + "]");
    }
}

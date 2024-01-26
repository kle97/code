package com.java.code.common;

public class SoftAssertJ {
    
    private final SoftAssertion softAssertion = new SoftAssertion();
    
    public SoftAssertJ() {
    }
    
    public void assertAll() {
        softAssertion.assertAll();
    }
    
    public AssertWithMessage as(String message) {
        return new AssertWithMessage(softAssertion, message);
    }
    
    public <T> BaseSoftAssertion<T> assertThat(T actual) {
        return new BaseSoftAssertion<>(softAssertion, null, actual);
    }

    public BooleanSoftAssertion assertThat(boolean actual) {
        return new BooleanSoftAssertion(softAssertion, null, actual);
    }

    public StringSoftAssertion assertThat(String actual) {
        return new StringSoftAssertion(softAssertion, null, actual);
    }

    public <T extends Comparable<T>> NumberSoftAssertion<T> assertThat(T actual) {
        return new NumberSoftAssertion<>(softAssertion, null, actual);
    }
    
    public static class AssertWithMessage {
        
        private final SoftAssertion softAssertion;
        
        private final String message;
        
        public AssertWithMessage(SoftAssertion softAssertion, String message) {
            this.softAssertion = softAssertion;
            this.message = message;
        }
        
        public <T> BaseSoftAssertion<T> assertThat(T actual) {
            return new BaseSoftAssertion<>(softAssertion, message, actual);
        }

        public BooleanSoftAssertion assertThat(boolean actual) {
            return new BooleanSoftAssertion(softAssertion, message, actual);
        }

        public StringSoftAssertion assertThat(String actual) {
            return new StringSoftAssertion(softAssertion, message, actual);
        }

        public <T extends Comparable<T>> NumberSoftAssertion<T> assertThat(T actual) {
            return new NumberSoftAssertion<>(softAssertion, message, actual);
        }
    }

    public static class BooleanSoftAssertion extends BaseSoftAssertion<Boolean> {

        public BooleanSoftAssertion(SoftAssertion softAssertion, String message, Boolean actual) {
            super(softAssertion, message, actual);
        }

        public BooleanSoftAssertion as(String message) {
            this.innerMessage = message;
            return this;
        }

        public BooleanSoftAssertion assertTrue(String prefix) {
            softAssertion.assertTrue(actual, innerMessage);
            return this;
        }

        public BooleanSoftAssertion assertFalse() {
            softAssertion.assertFalse(actual, innerMessage);
            return this;
        }
    }

    public static class StringSoftAssertion extends BaseSoftAssertion<String> {

        public StringSoftAssertion(SoftAssertion softAssertion, String message, String actual) {
            super(softAssertion, message, actual);
        }

        public StringSoftAssertion as(String message) {
            this.innerMessage = message;
            return this;
        }

        public StringSoftAssertion startWith(String prefix) {
            softAssertion.assertTrue(actual.startsWith(prefix), innerMessage);
            return this;
        }

        public StringSoftAssertion endsWith(String suffix) {
            softAssertion.assertTrue(actual.endsWith(suffix), innerMessage);
            return this;
        }

        public StringSoftAssertion contains(CharSequence s) {
            softAssertion.assertTrue(actual.contains(s), innerMessage);
            return this;
        }

        public StringSoftAssertion isEqualIgnoringCase(String expected) {
            softAssertion.assertEquals(actual.toLowerCase(), expected.toLowerCase(), innerMessage);
            return this;
        }
    }
    
    public static class NumberSoftAssertion<T extends Comparable<T>> extends BaseSoftAssertion<T> {

        public NumberSoftAssertion(SoftAssertion softAssertion, String message, T actual) {
            super(softAssertion, message, actual);
        }
        
        public NumberSoftAssertion<T> as(String message) {
            this.innerMessage = message;
            return this;
        }

        public NumberSoftAssertion<T> isLargerThan(T expected) {
            softAssertion.assertTrue(actual.compareTo(expected) > 0, innerMessage);
            return this;
        }

        public NumberSoftAssertion<T> isLessThan(T expected) {
            softAssertion.assertTrue(actual.compareTo(expected) < 0, innerMessage);
            return this;
        }
    }
    
    public static class BaseSoftAssertion<T> {
        
        protected SoftAssertion softAssertion;

        protected String innerMessage;
        
        T actual;

        public BaseSoftAssertion(SoftAssertion softAssertion, String message, T actual) {
            this.softAssertion = softAssertion;
            this.innerMessage = message;
            this.actual = actual;
        }

        public BaseSoftAssertion<T> as(String message) {
            this.innerMessage = message;
            return this;
        }

        public BaseSoftAssertion<T> isEqualTo(T expected) {
            softAssertion.assertEquals(actual, expected, innerMessage);
            return this;
        }

        public BaseSoftAssertion<T> isNotEqualTo(T expected) {
            softAssertion.assertNotEquals(actual, expected, innerMessage);
            return this;
        }
    }
}

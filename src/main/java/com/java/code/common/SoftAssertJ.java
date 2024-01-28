package com.java.code.common;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Streams;
import org.testng.Assert;
import org.testng.asserts.IAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

@Slf4j
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

    public <T extends Iterable<?>> IterableSoftAssertion<T> assertThat(T actual) {
        return new IterableSoftAssertion<>(softAssertion, null, actual);
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

        public <T extends Iterable<?>> IterableSoftAssertion<T> assertThat(T actual) {
            return new IterableSoftAssertion<>(softAssertion, message, actual);
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
    
    public static class IterableSoftAssertion<T extends Iterable<?>> extends BaseSoftAssertion<T> {

        public IterableSoftAssertion(SoftAssertion softAssertion, String message, T actual) {
            super(softAssertion, message, actual);
        }

        public IterableSoftAssertion<T> as(String message) {
            this.innerMessage = message;
            return this;
        }

        public IterableSoftAssertion<T> isEqualNoOrder(T expected) {
            doAssert(new SimpleAssert<>(actual, expected, innerMessage) {
                @Override
                public void doAssert() {
                    final List<?> actualAsList = newArrayList(actual);
                    final List<?> expectedAsList = newArrayList(expected);
                    if (actualAsList == null || expectedAsList == null || actualAsList.size() != expectedAsList.size()) {
                        failNoOrder(innerMessage, actual, expected);
                        return;
                    }

                    for (Object item : actual) {
                       if (item instanceof Iterable<?> || item.getClass().isArray()) {
                           if (expectedAsList.stream().noneMatch(o -> isEqualNoOrder(item, o))) {
                               failNoOrder(innerMessage, actual, expected);
                               return;
                           }
                       } else {
                           if (expectedAsList.stream().noneMatch(item::equals)) {
                               failNoOrder(innerMessage, actual, expected);
                               return;
                           }
                       }
                    }
                }
            });
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

        protected boolean isEqualNoOrder(Object iterable, Object other) {
            List<?> iterableAsList = newArrayList(iterable);
            List<?> otherAsList = newArrayList(other);

            if (iterableAsList == null || otherAsList == null || iterableAsList.size() != otherAsList.size()) {
                return false;
            }

            for (Object item : iterableAsList) {
                otherAsList.remove(item);
            }
            return otherAsList.isEmpty();
        }

        protected void doAssert(IAssert<?> assertCommand) {
            softAssertion.onBeforeAssert(assertCommand);
            try {
                softAssertion.executeAssert(assertCommand);
                softAssertion.onAssertSuccess(assertCommand);
            } catch (AssertionError e) {
                softAssertion.onAssertFailure(assertCommand, e);
                throw e;
            } finally {
                softAssertion.onAfterAssert(assertCommand);
            }
        }

        protected <E extends Iterable<?>> void failNoOrder(String message, E actual, E expected) {
            StringBuilder actualString = new StringBuilder();
            actual.forEach(actualString::append);
            StringBuilder expectedString = new StringBuilder();
            expected.forEach(expectedString::append);
            Assert.fail(message + " expected (no order) " + expectedString + " but found " + actualString);
        }

        protected List<?> newArrayList(Object object) {
            List<Object> list;
            if (object == null) {
                return null;
            } else if (object.getClass().isArray()) {
                list = new ArrayList<>(Arrays.asList((Object[]) object));
            } else if (object instanceof Collection<?>) {
                list = new ArrayList<>((Collection<?>) object);
            } else if (object instanceof Iterable<?>) {
                list = Streams.stream((Iterable<?>) object).collect(toCollection(ArrayList::new));
            } else {
                list = new ArrayList<>();
                list.add(object);
            }

            return list;
        }
    }
    
    private abstract static class SimpleAssert<T> implements IAssert<T> {
        private final T actual;
        private final T expected;
        private final String message;

        public SimpleAssert(String message) {
            this(null, null, message);
        }

        public SimpleAssert(T actual, T expected) {
            this(actual, expected, null);
        }

        public SimpleAssert(T actual, T expected, String message) {
            this.actual = actual;
            this.expected = expected;
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }

        @Override
        public T getActual() {
            return actual;
        }

        @Override
        public T getExpected() {
            return expected;
        }

        @Override
        public abstract void doAssert();
    }
}

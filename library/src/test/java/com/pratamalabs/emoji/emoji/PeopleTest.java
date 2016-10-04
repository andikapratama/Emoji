package com.pratamalabs.emoji.emoji;

import com.pushtorefresh.private_constructor_checker.PrivateConstructorChecker;

import org.junit.Test;

public class PeopleTest {
    @Test
    public void constructorShouldBePrivate() {
        PrivateConstructorChecker.forClass(People.class).expectedTypeOfException(AssertionError.class).expectedExceptionMessage("No instances.").check();
    }
}

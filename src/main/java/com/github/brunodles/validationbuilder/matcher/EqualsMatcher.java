package com.github.brunodles.validationbuilder.matcher;

/**
 * Created by bruno on 03/06/16.
 */
public interface EqualsMatcher<T, SubClass extends EqualsMatcher> {

    SubClass isEqualsTo(T expected);
}

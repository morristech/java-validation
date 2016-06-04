package com.github.brunodles.validationbuilder.matcher;

import com.github.brunodles.validationbuilder.Errors;

import java.util.function.IntConsumer;

import static com.github.brunodles.validationbuilder.matcher.Common._if;

/**
 * Created by bruno on 03/06/16.
 */
class NumberMatcherImpl<T extends Number & Comparable, S extends NumberMatcher & ObjectMatcher>
        implements NumberMatcher<T, S>, ObjectMatcher<S> {
    private T value;
    private IntConsumer adder;

    public NumberMatcherImpl(T value, IntConsumer adder) {
        this.value = value;
        this.adder = adder;
    }

    @Override
    public S isEqualsTo(T expected) {
        _if(() -> value.equals(expected), adder, Errors.EQUAL);
        return (S) this;
    }

    @Override
    public S isGreater(T expected) {
        _if(() -> gt(expected), adder, Errors.GREATER);
        return (S) this;
    }

    @Override
    public S isLower(T expected) {
        _if(() -> lt(expected), adder, Errors.LOWER);
        return (S) this;
    }

    @Override
    public S isBetween(T min, T max) {
        _if(() -> bt(min, max), adder, Errors.BETWEEN);
        return (S) this;
    }

    public boolean gt(T expected) {
        return value.compareTo(expected) > 0;
    }

    public boolean lt( T expected) {
        return value.compareTo(expected) < 0;
    }

    private boolean bt(T min, T max) {
        return gt(min) && lt(max);
    }

    @Override
    public S isNull() {
        _if(() -> value == null, adder, Errors.NULL);
        return (S) this;
    }
}

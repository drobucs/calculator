package com.drobucs.android_hw1.expression;

import androidx.annotation.NonNull;

public abstract class AbstractElement extends AbstractExpression {

    public AbstractElement(final Object value) {
        super(value, 100);
    }

    @Override
    public abstract double evaluate(double varValue);

    @NonNull
    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object exp) {
        if (exp == null || exp.getClass() != this.getClass()) {
            return false;
        }
        return exp.toString().equals(value.toString());
    }
}

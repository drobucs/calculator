package com.drobucs.android_hw1.expression;


public abstract class AbstractExpression implements MarkExpression {
    protected final int priority;
    protected final Object value;

    public AbstractExpression(final Object value, final int priority) {
        this.value = value;
        this.priority = priority;
    }

    @Override
    public abstract double evaluate(double x);
}

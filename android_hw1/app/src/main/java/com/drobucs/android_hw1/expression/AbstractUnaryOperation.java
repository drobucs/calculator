package com.drobucs.android_hw1.expression;

import androidx.annotation.NonNull;

public abstract class AbstractUnaryOperation extends AbstractExpression {
    protected final MarkExpression exp;

    public AbstractUnaryOperation(final MarkExpression exp, final String operation) {
        super(operation, 0);
        this.exp = exp;
    }

    protected abstract double calculate(double val);

    @Override
    public double evaluate(double x) {
        return calculate(exp.evaluate(x));
    }

    @NonNull
    @Override
    public String toString() {
        return value.toString() + "(" + exp.toString() + ")";
    }

    @Override
    public int hashCode() {
        return value.hashCode() * exp.hashCode();
    }

}

package com.drobucs.android_hw1.expression;

import androidx.annotation.NonNull;

import java.util.Objects;

public abstract class AbstractBinaryOperation extends AbstractExpression {
    protected final MarkExpression expLeft;
    protected final MarkExpression expRight;

    public AbstractBinaryOperation(final String operation, final int priority, final MarkExpression expLeft,
                                   final MarkExpression expRight) {
        super(" " + operation + " ", priority);
        this.expLeft = expLeft;
        this.expRight = expRight;
    }


    protected abstract double calculate(double left, double right);

    @Override
    public double evaluate(double x) {
        return calculate(expLeft.evaluate(x), expRight.evaluate(x));
    }

    @Override
    public boolean equals(Object exp) {
        if (exp == null || exp.getClass() != this.getClass()) {
            return false;
        }
        return ((AbstractBinaryOperation) exp).expLeft.equals(expLeft)
                && ((AbstractBinaryOperation) exp).expRight.equals(expRight);
    }

    @NonNull
    @Override
    public String toString() {
        return "(" + expLeft.toString() + value.toString() + expRight.toString() + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(expLeft, expRight, priority);
    }

}

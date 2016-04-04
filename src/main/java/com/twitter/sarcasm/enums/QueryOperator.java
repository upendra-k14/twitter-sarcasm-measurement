package com.twitter.sarcasm.enums;

/**
 * Created by Dmitry on 2016-04-03.
 */
public enum  QueryOperator {
    OR(" OR "),
    AND(" AND ");

    private final String operatorValue;
    QueryOperator(final String operatorValue){
        this.operatorValue = operatorValue;
    }

    public String getOperatorValue() {
        return operatorValue;
    }
}

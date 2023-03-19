package com.sub.sublayer.specifications;


public class SpecSearchCriteria {
    private boolean orPredicate;
    private String key;
    private String operation;
    private Object value;

    public void setOrPredicate(boolean orPredicate) {
        this.orPredicate = orPredicate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public SpecSearchCriteria(boolean orPredicate, String key, String operation, Object value) {
        this.orPredicate = orPredicate;
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public boolean isOrPredicate() {
        return orPredicate;
    }
}

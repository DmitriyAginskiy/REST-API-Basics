package com.epam.esm.dao.creator;

public class FieldCondition {

    private static final String EQUAL_SYMBOL = "=";
    private static final String QUESTION_MARK = "?";
    private static final String WHITESPACE = " ";

    private String name;
    private String value;

    public FieldCondition(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public void addToQuery(StringBuilder finalQuery) {
        finalQuery.append(name);
        finalQuery.append(WHITESPACE);
        finalQuery.append(EQUAL_SYMBOL);
        finalQuery.append(WHITESPACE);
        finalQuery.append(QUESTION_MARK);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

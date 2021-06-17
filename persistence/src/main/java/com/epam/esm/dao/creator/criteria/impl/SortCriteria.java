package com.epam.esm.dao.creator.criteria.impl;

import com.epam.esm.dao.creator.criteria.Criteria;

/**
 * Class of sort criteria.
 *
 * @author Dzmitry Ahinski
 */
public class SortCriteria implements Criteria {

    public static final String SORT_ASC = "ASC";
    public static final String SORT_DESC = "DESC";
    private static final String WHITESPACE = " ";

    private String sortField;
    private String sortType;

    public SortCriteria(String sortField, String sortType) {
        this.sortField = sortField;
        this.sortType = sortType;
    }

    @Override
    public void addCriteria(StringBuilder query) {
        query.append(WHITESPACE);
        query.append(sortField);
        query.append(WHITESPACE);
        query.append(sortType);
    }
}

package com.epam.esm.dao.creator.criteria.impl;

import com.epam.esm.dao.creator.criteria.Criteria;

/**
 * Class of search criteria.
 *
 * @author Dzmitry Ahinski
 */
public class SearchCriteria implements Criteria {

    private static final String WHITESPACE = " ";
    private static final String LIKE_WORD = "LIKE";
    private static final String PERCENT_SYMBOL = "%";
    private static final String APOSTROPHE = "'";

    private String fieldForSearch;
    private String searchWord;

    public SearchCriteria(String fieldForSearch, String searchWord) {
        this.fieldForSearch = fieldForSearch;
        this.searchWord = searchWord;
    }

    @Override
    public void addCriteria(StringBuilder query) {
        query.append(fieldForSearch);
        query.append(WHITESPACE);
        query.append(LIKE_WORD);
        query.append(WHITESPACE);
        query.append(APOSTROPHE);
        query.append(PERCENT_SYMBOL);
        query.append(searchWord);
        query.append(PERCENT_SYMBOL);
        query.append(APOSTROPHE);
    }
}

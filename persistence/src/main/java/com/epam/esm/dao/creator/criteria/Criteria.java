package com.epam.esm.dao.creator.criteria;

/**
 * Interface of search or sort criteria
 *
 * @author Dzmitry Ahinski
 */
public interface Criteria {

    /**
     * Add search or sort criteria to query string.
     *
     * @param query current query
     */
    void addCriteria(StringBuilder query);
}

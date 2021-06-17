package com.epam.esm.dao.creator;

import com.epam.esm.dao.creator.criteria.Criteria;
import com.epam.esm.dao.creator.criteria.impl.SearchCriteria;
import com.epam.esm.dao.creator.criteria.impl.SortCriteria;

import java.util.List;
import java.util.stream.Collectors;

public class GiftCertificateQueryCreator {

    private static final String QUERY_WITHOUT_SEARCH_CRITERIA = "SELECT gift_certificates.*, tags.* FROM gift_certificates"
            + " LEFT JOIN gift_certificates_has_tags ON certificate_id = gift_certificates_id_fk LEFT JOIN tags ON tag_id = tags_id_fk";
    private static final String AND_WORD = "AND";
    private static final String WHERE_WORD = "WHERE";
    private static final String ORDER_BY = "ORDER BY";
    private static final String COMMA = ",";
    private static final String SEMICOLON = ";";
    private static final String WHITESPACE = " ";

    public static String createQuery(List<Criteria> criteriaList) {
        if(criteriaList == null || criteriaList.isEmpty()) {
            return QUERY_WITHOUT_SEARCH_CRITERIA;
        }
        StringBuilder finalQuery = new StringBuilder(QUERY_WITHOUT_SEARCH_CRITERIA);
        List<Criteria> searchCriteriaList = criteriaList.stream().filter(t -> t instanceof SearchCriteria).collect(Collectors.toList());
        if(!searchCriteriaList.isEmpty()) {
            finalQuery.append(WHITESPACE).append(WHERE_WORD);
            boolean isFirstSearchCriteria = true;
            for(Criteria c : searchCriteriaList) {
                if(isFirstSearchCriteria) {
                    finalQuery.append(WHITESPACE);
                    isFirstSearchCriteria = false;
                } else {
                    finalQuery.append(WHITESPACE).append(AND_WORD).append(WHITESPACE);
                }
                c.addCriteria(finalQuery);
            }
        }
        List<Criteria> sortCriteriaList = criteriaList.stream().filter(t -> t instanceof SortCriteria).collect(Collectors.toList());
        if(!sortCriteriaList.isEmpty()) {
            boolean isFirstSortCriteria = true;
            for(Criteria c : sortCriteriaList) {
                if(isFirstSortCriteria) {
                    finalQuery.append(WHITESPACE).append(ORDER_BY);
                    isFirstSortCriteria = false;
                } else {
                    finalQuery.append(WHITESPACE).append(COMMA);
                }
                c.addCriteria(finalQuery);
            }
        }
        finalQuery.append(SEMICOLON);
        return finalQuery.toString();
    }
}

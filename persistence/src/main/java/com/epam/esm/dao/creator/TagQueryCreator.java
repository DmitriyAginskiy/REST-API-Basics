package com.epam.esm.dao.creator;

import com.epam.esm.dao.constant.TagColumnName;

/**
 * Class that creates tag queries.
 *
 * @author Dzmitry Ahinski
 */
public class TagQueryCreator {

    private static final String SELECTION_QUERY_WITHOUT_TAGS = "SELECT * FROM tags WHERE ";
    private static final String WHITESPACE_SYMBOL = " ";
    private static final String EQUAL = "=";
    private static final String QUESTION_MARK = "?";
    private static final String SEMICOLON = ";";
    private static final String OR = "OR";

    /**
     * Creates search query for existing tags.
     *
     * @param tagsNumber as the number of tags
     * @return String as final search query
     */
    public static String createExistingTagsSelectionQuery(int tagsNumber) {
        StringBuilder finalQuery = new StringBuilder(SELECTION_QUERY_WITHOUT_TAGS);
        boolean isFirstTag = true;
        for(int i = 0; i < tagsNumber; i++) {
            if(isFirstTag) {
                isFirstTag = false;
            } else {
                finalQuery.append(WHITESPACE_SYMBOL);
                finalQuery.append(OR);
                finalQuery.append(WHITESPACE_SYMBOL);
            }
            finalQuery.append(TagColumnName.TAG_NAME);
            finalQuery.append(WHITESPACE_SYMBOL);
            finalQuery.append(EQUAL);
            finalQuery.append(WHITESPACE_SYMBOL);
            finalQuery.append(QUESTION_MARK);
        }
        finalQuery.append(SEMICOLON);
        return finalQuery.toString();
    }
}

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.epam.esm.app;

import com.epam.esm.list.LinkedList;

import static com.epam.esm.utilities.StringUtils.join;
import static com.epam.esm.utilities.StringUtils.split;
import static com.epam.esm.app.MessageUtils.getMessage;

import org.apache.commons.text.WordUtils;

public class App {
    public static void main(String[] args) {
        LinkedList tokens;
        tokens = split(getMessage());
        String result = join(tokens);
        System.out.println(WordUtils.capitalize(result));
    }
}

package ru.javawebinar.topjava.util;

import org.apache.commons.lang3.StringEscapeUtils;

public class SandBox {
    public static void main(String[] args) {
        String newS = StringEscapeUtils.escapeJava("обновить");
        String unEscape = StringEscapeUtils.unescapeJava("\\u043E\\u0442\\u043C\\u0435\\u043D\\u0430");
       //слово отмена на русском языке
        System.out.println(unEscape);
    }
}

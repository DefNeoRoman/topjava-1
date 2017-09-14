package ru.javawebinar.topjava.util.sandbox;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.IOException;

public class SandBox {
    public static void main(String[] args) {
        String newS = StringEscapeUtils.escapeJava("обновить");
        String unEscape = StringEscapeUtils.unescapeJava("\\u043E\\u0442\\u043C\\u0435\\u043D\\u0430");
       //слово отмена на русском языке
        //Написать скрипт, который преобразовывает один файл в другой
        System.out.println(unEscape);
    }
    public void method() throws Exception {
        //...
    }


}

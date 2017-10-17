package ru.ezhov.util;

import org.jsoup.Jsoup;

/**
 *
 * @author deonisius
 */
public class HtmlToText {

    public static synchronized String convert(String html) {
        return Jsoup.parse(html).text();
    }
}

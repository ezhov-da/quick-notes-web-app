package ru.ezhov.util;

/**
 *
 * @author deonisius
 */
public class LinkChecker {

    private final String link;

    public LinkChecker(String link) {
        this.link = link;
    }

    public boolean check() {
        return !"".equals(link);
    }
}

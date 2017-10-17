package ru.ezhov.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author deonisius
 */
public class ReplacesHtmlXml {

    private final String text;

    public ReplacesHtmlXml(String text) {
        this.text = text;
    }

    public void replace() {
        Pattern pattern = Pattern.compile("\\[code:\\].+\\[:code\\]");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {

        }

    }

    public static void main(String[] args) {
        String text = "[code:]html[:code]\n"
                + "<b>FUCK</b>\n"
                + "[/code]\n"
                + "sadasdgadg\n"
                + "[code:]html[:code]\n"
                + "<b>FUCK</b>\n"
                + "[/code]\n"
                + "\n"
                + "sdfhshshdfhsf\n"
                + "\n"
                + "\n"
                + "[code:]html[:code]\n"
                + "<b>FUCK</b>\n"
                + "[/code]";
        ReplacesHtmlXml replacesHtmlXml = new ReplacesHtmlXml(text);
        replacesHtmlXml.replace();

    }
    
    private class PositionHolder{
        private int startCodeTag;
        private int middletCodeTag;
        private int endCodeTag;

        public PositionHolder(int startCodeTag, int middletCodeTag, int endCodeTag) {
            this.startCodeTag = startCodeTag;
            this.middletCodeTag = middletCodeTag;
            this.endCodeTag = endCodeTag;
        }

        public int getStartCodeTag() {
            return startCodeTag;
        }

        public int getMiddletCodeTag() {
            return middletCodeTag;
        }

        public int getEndCodeTag() {
            return endCodeTag;
        }
        
        
    }
}

package ru.ezhov.util;

/**
 * Ищем наш тег с кодом и меняем на код
 *
 * @author deonisius
 */
public class CodeFinder {

    private final String start[];
    private final String middle[];
    private final String end[];

    public CodeFinder() {
        //this.end = new String[]{"\\[/code\\]", "</pre>"};
        //this.middle = new String[]{"\\[:code\\]", " ;\">"};
        //this.start = new String[]{"\\[code:\\]", " <pre class=\"brush: "};
        
        this.end = new String[]{"\\[/code\\]", "</code></pre>"};
        this.middle = new String[]{"\\[:code\\]", "\">"};
        this.start = new String[]{"\\[code:\\]", "<pre><code class=\""};        
    }

    public String replaceCodeTag(String text) {
        text = text.replaceAll(start[0], start[1]);
        text = text.replaceAll(middle[0], middle[1]);
        text = text.replaceAll(end[0], end[1]);
        return text;
    }

}

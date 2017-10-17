package ru.ezhov.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author deonisius
 */
public class PreCodeFinder {

    private static final String SOURCE = "<h4>$notes</h4>\n"
            + "<b>Важно:</b>\n"
            + "\n"
            + "<pre>\n"
            + "    <code class=\"java\">\n"
            + "        <del>\n"
            + "         <li>Убрать перемешивание букв</li>\n"
            + "         <li>На генерировании запроса вынести кнопку преобразовать вниз всех вкладок</li>\n"
            + "        </del>\n"
            + "    </code>\n"
            + "</pre>";

    public void find() {
        Document document = Jsoup.parse(SOURCE);
        Elements elements = document.getElementsByTag("pre");
        elements.stream().forEach((e) -> {
            Elements e1 = e.getElementsByTag("code");
            String stringHtml = e1.html();
            String stringText = e1.text();
            
        });
    }
}

package tools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class JsoupTest {
    public static void main(String[] args) throws IOException {
        Document google = Jsoup.connect("https://mesagarden.com/").get();
        String str = google.getElementById("mega-menu-item-47").getElementsByTag("a").attr("href").trim();
        google = Jsoup.connect("https://mesagarden.com/"+str).get();
        System.out.printf(google.body().toString());
    }
}

package com.avpc.rssreader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLUtils {


    /**
     * Este método recibe una texto en HTML y devuelve el texto sin las etiquetas HTML.
     * Solo se queda con las que estén dentro de una etiqueta 'p'
     * */
    public static String eliminaHTML(String text) {
        return Jsoup.parse(text).text();
    }


    /**
     * Se recibe un texto en HTML y se devuelven las etiquetas 'img' que contenga.
     * */
    public static Elements buscaImagenes(String text) {
        Document doc = Jsoup.parse(text);
        return doc.getElementsByTag("img");
    }


    /**
     * Dada una etiqueta 'img' devuelve la URL del atributo 'href'
     * */
    public static String imgToUrl(Element e) {
        return e.attr("href");
    }
}
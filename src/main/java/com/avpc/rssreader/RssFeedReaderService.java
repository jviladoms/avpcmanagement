package com.avpc.rssreader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class RssFeedReaderService {
    private List<URL> urls = new ArrayList<URL>();

    public static RssFeedReaderService create(String... urls) {
        return new RssFeedReaderService(Arrays.asList(urls));
    }

    public static RssFeedReaderService create(List<String> list) {
        return new RssFeedReaderService(list);
    }

    public static RssFeedReaderService create(String url) {
        List<String> aux = new ArrayList<String>();
        aux.add(url);
        return new RssFeedReaderService(aux);
    }

    private RssFeedReaderService(List<String> ls) {
        for(String feedUrl: ls) {
            try {
                this.urls.add(new URL(feedUrl));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<URL> getURLs() {
        return this.urls;
    }

    public void setURLs(List<URL> ls) {
        this.urls = ls;
    }

    /**
     * Con el URL (si se hubieran dado más de uno, solo coge el primero), crea e inicializa un objeto del tipo Feed.
     * */
    public Feed readFeed() {

        Feed feedRSS = null;

        try {
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(urls.get(0)));

            feedRSS = Feed.create(feed);

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return feedRSS;

    }

    /**
     * Dado un Feed, se le añaden los entries de los RSS dados del contructor.
     * No se alteran los datos del Feed, ni se sustiuyen los FeedMessage, simplemente se añaden al final de la lista.
     *
     * @param feedRes: el Feed que vamos a añadir las propiedades.
     *
     * */
    public void readFeedes(Feed feedRes) {

        for(URL u: urls) {
            try {
                SyndFeedInput input = new SyndFeedInput();
                SyndFeed feed = input.build(new XmlReader(u));

                feedRes.addEntries(feed);


            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (FeedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

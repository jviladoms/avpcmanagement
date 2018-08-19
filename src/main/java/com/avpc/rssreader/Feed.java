package com.avpc.rssreader;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;

/**
 * Feed es la clase que almacena los feeds RSS, con sus propiedades más importantes.
 * Está directamente relacionada con SyndFeed de ROME
 *
 *
 * @author Andrés M. Jiménez
 * @since 24/III/15
 * */
public class Feed implements Iterable<FeedMessage>{
    private String title;
    private String link;
    private String description;
    private SortedSet<FeedMessage> entries;

    private SyndFeed syndFeed = null;

    public static Feed create(String title, String link, String description, String language,
                              String copyright, Date pubDate, SortedSet<FeedMessage> entries) {
        return new Feed(title, link, description, language, copyright, pubDate, entries, null);
    }

    public static Feed create(String title, String link, String description, String language,
                              String copyright, Date pubDate) {
        return new Feed(title, link, description, language, copyright, pubDate, new TreeSet<FeedMessage>(), null);
    }

    /**
     * @param feed: Dado un SyndFeed, lo convierte a Feed
     * */
    @SuppressWarnings("unchecked")
    public static Feed create(SyndFeed feed) {
        return new Feed(feed.getTitle(),
                feed.getLink(),
                feed.getDescription(),
                feed.getLanguage(),
                feed.getCopyright(),
                feed.getPublishedDate(),
                convertFeedEntries(feed.getEntries()),
                feed);
    }

    public static Feed create () {
        return new Feed("", "", "", "", "", new Date(), new TreeSet<FeedMessage>(), null);
    }

    /**
     * @return Convierte nuestra clase en un SyndFeed del typo RSS 2.0
     * */
    public SyndFeed toSyndFeed() {
        SyndFeed res = new SyndFeedImpl();
        if(this.syndFeed == null) {
            res.setTitle(this.title);
            res.setLink(this.link);
            res.setDescription(this.description);
            res.setFeedType("rss_2.0");
            res.setEntries(convertEntries());
        } else {
            res = this.syndFeed;
        }

        return res;
    }


    /**
     * Recibe un SyndFeed y añade sus entradas a nuestro Feed
     * */
    @SuppressWarnings("unchecked")
    public void addEntries(SyndFeed r) {
        this.entries.addAll(convertFeedEntries(r.getEntries()));
    }

    public Feed(String title, String link, String description, String language,
                String copyright, Date pubDate, SortedSet<FeedMessage> entries, SyndFeed syndFeed) {
        super();
        this.title = title;
        this.link = link;
        this.description = description;
        this.entries = new TreeSet<FeedMessage>(entries);
        this.syndFeed = syndFeed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public SortedSet<FeedMessage> getEntries() {
        return entries;
    }

    public void setEntries(SortedSet<FeedMessage> entries) {
        this.entries = entries;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((entries == null) ? 0 : entries.hashCode());
        result = prime * result + ((link == null) ? 0 : link.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Feed other = (Feed) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (entries == null) {
            if (other.entries != null)
                return false;
        } else if (!entries.equals(other.entries))
            return false;
        if (link == null) {
            if (other.link != null)
                return false;
        } else if (!link.equals(other.link))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Feed [title=" + title + ", link=" + link + ", description="
                + description + ", entries=" + entries + "]";
    }

    public Iterator<FeedMessage> iterator() {
        return this.getEntries().iterator();
    }



    private static SortedSet<FeedMessage> convertFeedEntries(List<SyndEntry> entries) {
        SortedSet<FeedMessage> res = new TreeSet<FeedMessage>();
        for(SyndEntry entry: entries) {
            res.add(FeedMessage.create(entry));
        }
        return res;
    }


    private List<SyndEntry> convertEntries() {
        List<SyndEntry> res = new ArrayList<SyndEntry>();
        for(FeedMessage entry: this) {
            res.add(entry.toSyndEntry());
        }
        return res;
    }

}


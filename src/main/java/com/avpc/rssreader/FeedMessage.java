package com.avpc.rssreader;

import java.util.Date;

import org.jsoup.select.Elements;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;

/**
 * FeedMessage es la clase que almacena los items más importantes del Feed.
 * Está directamente relacionada con SyndEntry de ROME
 *
 *
 * @author Andrés M. Jiménez
 * @since 24/III/15
 * */
public class FeedMessage implements Comparable<FeedMessage>{
    private String title;
    private String link;
    private String description;
    private String guid;
    private Date pubDate;

    private SyndEntry syndEntry;

    public static FeedMessage create(String title, String link, String description,
                                     String author, String guid, Date pubDate) {
        return new FeedMessage(title, link, description, author, guid, pubDate, null);
    }

    /**
     * @param entry: Dado un SyndEntry, lo convierte a FeedMessage
     * */
    public static FeedMessage create(SyndEntry entry) {
        return new FeedMessage(entry.getTitle(),
                entry.getLink(),
                entry.getDescription().getValue(),
                entry.getAuthor(),
                entry.getUri(),
                entry.getPublishedDate(),
                entry);
    }

    public static FeedMessage create() {
        return new FeedMessage("", "", "", "", "", new Date(), null);
    }

    /**
     * La descripción de devuelve en formato <i>HTML</i>: imagen al principio y texto plano.
     *
     * @return Convierte nuestra clase en un SyndEntry
     * */
    public SyndEntry toSyndEntry() {
        SyndEntry res = new SyndEntryImpl();
        if(this.syndEntry == null) {
            res.setTitle(this.title);
            res.setLink(this.link);
            SyndContent s = new SyndContentImpl();
            s.setType("text/plain");
            s.setValue(getDescription());
            res.setDescription(s);
            res.setUri(this.guid);
            res.setPublishedDate(this.pubDate);
        } else {
            res = this.syndEntry;
        }

        return res;
    }

    private FeedMessage(String title, String link, String description,
                        String author, String guid, Date pubDate, SyndEntry syndEntry) {
        super();
        this.title = title;
        this.link = link;
        this.description = description;
        this.guid = guid;
        this.pubDate = pubDate;
        this.syndEntry = syndEntry;
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


    /**
     * Devuelve una cadena con la descripción en texto plano
     * */
    public String getDescription() {
        return HTMLUtils.eliminaHTML(this.description);
    }


    /**
     * Devuelve Elements: las imágenes contenidas en la descripción.
     * */
    public Elements getImages() {
        return HTMLUtils.buscaImagenes(this.description);
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getGuid() {
        return guid;
    }


    public void setGuid(String guid) {
        this.guid = guid;
    }


    public Date getPubDate() {
        return pubDate;
    }


    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((guid == null) ? 0 : guid.hashCode());
        result = prime * result + ((link == null) ? 0 : link.hashCode());
        result = prime * result + ((pubDate == null) ? 0 : pubDate.hashCode());
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
        FeedMessage other = (FeedMessage) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (guid == null) {
            if (other.guid != null)
                return false;
        } else if (!guid.equals(other.guid))
            return false;
        if (link == null) {
            if (other.link != null)
                return false;
        } else if (!link.equals(other.link))
            return false;
        if (pubDate == null) {
            if (other.pubDate != null)
                return false;
        } else if (!pubDate.equals(other.pubDate))
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
        return "FeedMessage [title=" + title + ", link=" + link
                + ", description=" + description
                + ", guid=" + guid + ", pubDate=" + pubDate + "]";
    }

    public int compareTo(FeedMessage arg0) {
        int res = - pubDate.compareTo(arg0.getPubDate());
        if(res == 0) {
            res=title.compareTo(arg0.getTitle());
        }
        return res;
    }
}

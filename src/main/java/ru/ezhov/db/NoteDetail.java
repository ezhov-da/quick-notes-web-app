package ru.ezhov.db;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Подробная записка
 *
 * @author deonisius
 */
public class NoteDetail extends Note {

    private DateFormat dateFormat;

    private String text;
    private String link;

    private Timestamp dateAdd;
    private Timestamp dateEdit;

    public NoteDetail(int id, String name) {
        super(id, name);
        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    }

    public NoteDetail(String text, String link, int id, String name, Timestamp dateAdd, Timestamp dateEdit) {
        super(id, name);
        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        this.text = text;
        this.link = link;
        this.dateAdd = dateAdd;
        this.dateEdit = dateEdit;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDateAddStr() {
        if (dateAdd != null) {
            return dateFormat.format(dateAdd);
        } else {
            return "";
        }

    }

    public String getDateEditStr() {
        if (dateEdit != null) {
            return dateFormat.format(dateEdit);
        } else {
            return "";
        }
    }

    public Timestamp getDateEdit() {
        return dateEdit;
    }

}

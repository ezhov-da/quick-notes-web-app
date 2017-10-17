package ru.ezhov.db;

/**
 *
 * @author deonisius
 */
public class Note {

    protected int id;
    protected String name;

    public Note(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

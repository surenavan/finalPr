package ru.anbel.notes.models;

// Модель заметки
public class MyNote {

    private int id;
    private String note = "Text";

    public MyNote() {}

    public MyNote(int id, String note) {
        this.id = id;
        this.note = note;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }
    public void setNote(String text) {
        this.note = text;
    }

}

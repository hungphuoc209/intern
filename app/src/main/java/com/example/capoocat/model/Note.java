package com.example.capoocat.model;

public class Note {
    private String task;
    private boolean isDone;
    private CapooCat imgNote;
    private NoteColor color;

    public Note(String task, boolean isDone, CapooCat imgNote, NoteColor color) {
        this.task = task;
        this.isDone = isDone;
        this.imgNote = imgNote;
        this.color = color;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public CapooCat getImgNote() {
        return imgNote;
    }

    public void setImgNote(CapooCat imgNote) {
        this.imgNote = imgNote;
    }

    public NoteColor getColor() {
        return color;
    }

    public void setColor(NoteColor color) {
        this.color = color;
    }
}

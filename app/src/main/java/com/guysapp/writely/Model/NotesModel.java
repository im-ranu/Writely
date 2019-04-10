package com.guysapp.writely.Model;


public class NotesModel {

    int _id;
    private String nTitle;
    private int nWords;
    public NotesModel(){   }


    public NotesModel(String nTitle, int nWords) {
        this.nTitle = nTitle;
        this.nWords = nWords;
    }


    public NotesModel(int id, String name, int words){
        this._id = id;
        this.nTitle = name;
        this.nWords = words;
    }


    public String getnTitle() {
        return nTitle;
    }

    public void setnTitle(String nTitle) {
        this.nTitle = nTitle;
    }

    public int getnWords() {
        return nWords;
    }

    public void setnWords(int nWords) {
        this.nWords = nWords;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}

package com.example.onleeenoot.data.source;

import com.example.onleeenoot.data.Note;

/**
 * Created by Clarence on 10/20/2016.
 */

public interface NotesDataSource {
    void getNotes();

    void saveNote(Note note);

    void updateNote(Note note);

    void deleteNote(String id);

    void getNote(String id);
}

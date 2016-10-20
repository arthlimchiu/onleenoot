package com.example.onleeenoot.data.source;

import com.example.onleeenoot.data.Note;

/**
 * Created by Clarence on 10/20/2016.
 */

public class NotesRepository implements NotesDataSource {
    private static NotesRepository INSTANCE = null;

    private final NotesDataSource mNotesLocalDataSource;

    private NotesRepository(NotesDataSource notesLocalDataSource) {
        mNotesLocalDataSource = notesLocalDataSource;
    }

    public static NotesRepository getInstance(NotesDataSource notesLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new NotesRepository(notesLocalDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getNotes() {
        mNotesLocalDataSource.getNotes();
    }

    @Override
    public void getNote(String id) {
        mNotesLocalDataSource.getNote(id);
    }

    @Override
    public void saveNote(Note note) {
        mNotesLocalDataSource.saveNote(note);
    }

    @Override
    public void updateNote(Note note) {
        mNotesLocalDataSource.updateNote(note);
    }

    @Override
    public void deleteNote(String id) {
        mNotesLocalDataSource.deleteNote(id);
    }
}

package com.example.onleeenoot.data.source;

import com.example.onleeenoot.data.Note;

import java.util.List;

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
    public void getNotes(LoadNotesCallback callback) {
        mNotesLocalDataSource.getNotes(new LoadNotesCallback() {
            @Override
            public void onNotesLoaded(List<Note> notes) {

            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void getNote(String id, GetNoteCallback callback) {
        mNotesLocalDataSource.getNote(id, new GetNoteCallback() {
            @Override
            public void onNoteLoaded(Note note) {

            }

            @Override
            public void onDataNotAvailable() {

            }
        });
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

    @Override
    public void deleteNotes() {
        mNotesLocalDataSource.deleteNotes();
    }
}

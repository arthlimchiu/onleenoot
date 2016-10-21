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
    public void getNotes(final LoadNotesCallback callback) {
        mNotesLocalDataSource.getNotes(new LoadNotesCallback() {
            @Override
            public void onNotesLoaded(List<Note> notes) {
                callback.onNotesLoaded(notes);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getNote(String id, final GetNoteCallback callback) {
        mNotesLocalDataSource.getNote(id, new GetNoteCallback() {
            @Override
            public void onNoteLoaded(Note note) {
                callback.onNoteLoaded(note);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void saveNote(Note note, final SaveNoteCallback callback) {
        mNotesLocalDataSource.saveNote(note, new SaveNoteCallback() {
            @Override
            public void onNoteSaved(long id) {
                callback.onNoteSaved(id);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
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

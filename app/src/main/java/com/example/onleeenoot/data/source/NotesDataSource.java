package com.example.onleeenoot.data.source;

import com.example.onleeenoot.data.Note;

import java.util.List;

/**
 * Created by Clarence on 10/20/2016.
 */

public interface NotesDataSource {

    interface LoadNotesCallback {
        void onNotesLoaded(List<Note> notes);

        void onDataNotAvailable();
    }

    interface GetNoteCallback {
        void onNoteLoaded(Note note);

        void onDataNotAvailable();
    }

    interface SaveNoteCallback {
        void onNoteSaved(long id);

        void onError();
    }

    void getNotes(LoadNotesCallback callback);

    void getNote(String id, GetNoteCallback callback);

    void saveNote(Note note, SaveNoteCallback callback);

    void updateNote(Note note);

    void deleteNote(String id);

    void deleteNotes();
}

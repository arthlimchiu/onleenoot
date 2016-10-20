package com.example.onleeenoot.notes;

import com.example.onleeenoot.data.Note;

import java.util.List;

public interface NotesContract {

    /**
     * These are all functions that we need to expose from the View layer and make it
     * available to the Presentation Layer
     */
    interface View {

        void setPresenter(Presenter presenter);

        void showLoadingIndicator(boolean isLoading);

        void showNotes(List<Note> notes);

        void launchAddNewNoteScreen();
    }

    /**
     * These are the actions that can be started from the View
     */
    interface Presenter {

        void loadNotes();

        void addNewNote();
    }
}

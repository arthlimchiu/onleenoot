package com.example.onleeenoot.details;

public interface NoteDetailsContract {
    interface View {
        void showText(String text);

        void showNoteDeleted();

        void showEditNote(String id);
    }

    interface Presenter {
        void loadNote();

        void deleteNote();

        void editNote();
    }
}

package com.example.onleeenoot.addnote;

/**
 * Created by Clarence on 10/24/2016.
 */

public interface AddNoteContract {

    interface View {
        void startNotesActivity();

        void showEmptyNoteError();

        void showFailedToSaveError();

        void showText(String text);
    }

    interface Presenter {
        void saveNote(String text);

        void populateNote();
    }

}

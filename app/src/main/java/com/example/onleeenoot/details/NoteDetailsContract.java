package com.example.onleeenoot.details;

public interface NoteDetailsContract {
    interface View {
        void showText(String text);
    }

    interface Presenter {
        void loadNote();
    }
}

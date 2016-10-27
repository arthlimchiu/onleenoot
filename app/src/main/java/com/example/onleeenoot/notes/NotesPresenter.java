package com.example.onleeenoot.notes;

import com.example.onleeenoot.Cheese;
import com.example.onleeenoot.data.Note;
import com.example.onleeenoot.data.source.NotesDataSource;
import com.example.onleeenoot.util.ListUtil;

import java.util.List;

public class NotesPresenter implements NotesContract.Presenter {

    private NotesContract.View mView;
    private NotesDataSource mRepository;

    public NotesPresenter(NotesContract.View view, NotesDataSource repository) {
        mView = view;
        mRepository = repository;
        mView.setPresenter(this);
    }

    @Override
    public void loadNotes() {
        mView.showLoadingIndicator(true);

        // replace code block with network/database/disk activity
        new Thread(new Runnable() {
            @Override
            public void run() {
                mRepository.getNotes(new NotesDataSource.LoadNotesCallback() {
                    @Override
                    public void onNotesLoaded(List<Note> notes) {
                        mView.showNotes(notes);
                    }

                    @Override
                    public void onDataNotAvailable() {

                    }
                });
                mView.showLoadingIndicator(false);
            }
        }).start();
    }

    @Override
    public void addNewNote() {
        mView.launchAddNewNoteScreen();
    }

    @Override
    public void openNoteDetails(Note note) {
        mView.startNoteDetailsActivity(String.valueOf(note.getId()));
    }
}

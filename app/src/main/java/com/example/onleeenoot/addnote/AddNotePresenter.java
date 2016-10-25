package com.example.onleeenoot.addnote;

import com.example.onleeenoot.data.Note;
import com.example.onleeenoot.data.source.NotesDataSource;

public class AddNotePresenter implements AddNoteContract.Presenter {
    private AddNoteContract.View mView;
    private NotesDataSource mRepository;

    public AddNotePresenter(AddNoteContract.View view, NotesDataSource repository) {
        mView = view;
        mRepository = repository;
    }

    @Override
    public void saveNote(String text) {
        if (text.isEmpty()) {
            mView.showEmptyNoteError();
            return;
        }

        Note note = new Note(text);
        mRepository.saveNote(note, new NotesDataSource.SaveNoteCallback() {
            @Override
            public void onNoteSaved(long id) {
                mView.startNotesActivity();
            }

            @Override
            public void onError() {
                mView.showFailedToSaveError();
            }
        });
    }
}

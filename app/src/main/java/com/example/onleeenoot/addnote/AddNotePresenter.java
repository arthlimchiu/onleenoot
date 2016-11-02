package com.example.onleeenoot.addnote;

import com.example.onleeenoot.data.Note;
import com.example.onleeenoot.data.source.NotesDataSource;

public class AddNotePresenter implements AddNoteContract.Presenter {
    private String mId;
    private AddNoteContract.View mView;
    private NotesDataSource mRepository;
    private Note mNote;

    public AddNotePresenter(String id, AddNoteContract.View view, NotesDataSource repository) {
        mId = id;
        mView = view;
        mRepository = repository;
    }

    @Override
    public void saveNote(String text) {
        if (text.isEmpty()) {
            mView.showEmptyNoteError();
            return;
        }

        if (mId != null) {
            mNote.setText(text);
            mRepository.updateNote(mNote);
            mView.startNotesActivity();
            return;
        }

        mNote = new Note(text);
        mRepository.saveNote(mNote, new NotesDataSource.SaveNoteCallback() {
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

    @Override
    public void populateNote() {
        mRepository.getNote(mId, new NotesDataSource.GetNoteCallback() {
            @Override
            public void onNoteLoaded(Note note) {
                mView.showText(note.getText());
                mNote = note;
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
}

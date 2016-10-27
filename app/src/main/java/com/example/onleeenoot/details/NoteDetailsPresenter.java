package com.example.onleeenoot.details;

import com.example.onleeenoot.data.Note;
import com.example.onleeenoot.data.source.NotesDataSource;
import com.example.onleeenoot.data.source.NotesRepository;

public class NoteDetailsPresenter implements NoteDetailsContract.Presenter {

    private String mTaskId;
    private NoteDetailsContract.View mView;
    private NotesRepository mRepository;

    public NoteDetailsPresenter(String taskId, NoteDetailsContract.View view, NotesRepository repository) {
        mTaskId = taskId;
        mView = view;
        mRepository = repository;
    }

    @Override
    public void loadNote() {
        mRepository.getNote(mTaskId, new NotesDataSource.GetNoteCallback() {
            @Override
            public void onNoteLoaded(Note note) {
                mView.showText(note.getText());
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
}

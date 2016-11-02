package com.example.onleeenoot.addnote;

import com.example.onleeenoot.data.Note;
import com.example.onleeenoot.data.source.NotesDataSource;
import com.example.onleeenoot.data.source.NotesRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AddNotePresenterTest {

    private String NOTE_ID = "1";

    @Mock
    private AddNoteContract.View view;

    @Mock
    private NotesRepository repository;

    @Captor
    private ArgumentCaptor<NotesDataSource.SaveNoteCallback> mSaveNoteCallbackCaptor;

    @Captor
    private ArgumentCaptor<NotesDataSource.GetNoteCallback> mGetNoteCallbackCaptor;

    private AddNotePresenter presenter;

    @Test
    public void saveNoteAndReturnToNotesActivity() throws Exception {
        presenter = new AddNotePresenter(null, view, repository);

        presenter.saveNote("Sample Text");

        verify(repository).saveNote(any(Note.class), mSaveNoteCallbackCaptor.capture());

        mSaveNoteCallbackCaptor.getValue().onNoteSaved(1);

        verify(view).startNotesActivity();
    }

    @Test
    public void updateNote() throws Exception {
        presenter = new AddNotePresenter(NOTE_ID, view, repository);
        presenter.populateNote();

        verify(repository).getNote(eq(NOTE_ID), mGetNoteCallbackCaptor.capture());

        mGetNoteCallbackCaptor.getValue().onNoteLoaded(new Note(1, "Sample Text"));

        verify(view).showText(anyString());

        presenter.saveNote("Sample Text");

        verify(repository).updateNote(any(Note.class));

    }

    @Test
    public void showErrorWhenSavingEmptyNote() throws Exception {
        presenter.saveNote("");
        verify(view).showEmptyNoteError();
    }

    @Test
    public void showErrorWhenFailedToSaveToLocalDatabase() throws Exception {
        presenter.saveNote("Sample Text");

        verify(repository).saveNote(any(Note.class), mSaveNoteCallbackCaptor.capture());

        mSaveNoteCallbackCaptor.getValue().onError();

        verify(view).showFailedToSaveError();
    }
}
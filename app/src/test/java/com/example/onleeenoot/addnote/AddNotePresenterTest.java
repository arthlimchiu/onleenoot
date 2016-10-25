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
import static org.mockito.Mockito.verify;

/**
 * Created by Clarence on 10/24/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class AddNotePresenterTest {

    @Mock
    private AddNoteContract.View view;

    @Mock
    private NotesRepository repository;

    @Captor
    private ArgumentCaptor<NotesDataSource.SaveNoteCallback> mSaveNoteCallbackCaptor;

    private AddNotePresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new AddNotePresenter(view, repository);
    }

    @Test
    public void saveNoteAndReturnToNotesActivity() throws Exception {
        presenter.saveNote("Sample Text");

        verify(repository).saveNote(any(Note.class), mSaveNoteCallbackCaptor.capture());

        mSaveNoteCallbackCaptor.getValue().onNoteSaved(1);

        verify(view).startNotesActivity();
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
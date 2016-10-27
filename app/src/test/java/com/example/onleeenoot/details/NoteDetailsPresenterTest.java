package com.example.onleeenoot.details;

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

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class NoteDetailsPresenterTest {
    private static final String TASK_ID = "1";

    @Mock
    private NoteDetailsContract.View view;

    @Mock
    private NotesRepository repository;

    @Captor
    private ArgumentCaptor<NotesDataSource.GetNoteCallback> mGetNoteCallbackCaptor;

    private NoteDetailsPresenter presenter;
    private Note note;

    @Before
    public void setUp() throws Exception {
        presenter = new NoteDetailsPresenter(TASK_ID, view, repository);
        note = new Note(Long.parseLong(TASK_ID), "Sample Text");
    }

    @Test
    public void getTaskFromRepositoryAndLoadIntoView() throws Exception {
        presenter.loadNote();

        verify(repository).getNote(eq(TASK_ID), mGetNoteCallbackCaptor.capture());

        mGetNoteCallbackCaptor.getValue().onNoteLoaded(note);

        verify(view).showText(note.getText());
    }
}
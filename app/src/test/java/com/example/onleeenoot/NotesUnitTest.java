package com.example.onleeenoot;

import com.example.onleeenoot.data.Note;
import com.example.onleeenoot.data.source.NotesDataSource;
import com.example.onleeenoot.data.source.NotesRepository;
import com.example.onleeenoot.notes.NotesContract;
import com.example.onleeenoot.notes.NotesPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class NotesUnitTest {

    private List<Note> NOTES;

    @Mock
    private NotesContract.View view;

    @Mock
    private NotesRepository repository;

    @Captor
    ArgumentCaptor<NotesDataSource.LoadNotesCallback> mLoadNotesCallbackCaptor;
    
    private NotesPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new NotesPresenter(view, repository);
        NOTES = Arrays.asList(new Note(1, "Sample Text"), new Note(2, "Sample"), new Note(3, "Sample ra ni"));
    }

    @Test
    public void loadAllNotesFromRepositoryAndLoadIntoView() throws Exception {
        presenter.loadNotes();

        verify(repository).getNotes(mLoadNotesCallbackCaptor.capture());
        mLoadNotesCallbackCaptor.getValue().onNotesLoaded(NOTES);

        InOrder inOrder = inOrder(view);
        inOrder.verify(view).showLoadingIndicator(true);
        inOrder.verify(view).showLoadingIndicator(false);

        ArgumentCaptor<List> showTasksArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(view).showNotes(showTasksArgumentCaptor.capture());
        assertTrue(showTasksArgumentCaptor.getValue().size() == 3);
    }

    @Test
    public void startAddNoteActivityWhenFabIsClicked() throws Exception {
        presenter.addNewNote();
        verify(view).launchAddNewNoteScreen();
    }
}

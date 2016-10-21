package com.example.onleeenoot.data.source;

import com.example.onleeenoot.data.Note;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class NotesRepositoryTest {
    private NotesRepository mNotesRepository;

    @Mock
    private NotesDataSource mNotesLocalDataSource;

    @Mock
    private NotesDataSource.LoadNotesCallback mLoadNotesCallback;

    @Mock
    private NotesDataSource.GetNoteCallback mGetNoteCallback;

    @Before
    public void setUp() throws Exception {
        mNotesRepository = NotesRepository.getInstance(mNotesLocalDataSource);
    }

    @After
    public void tearDown() throws Exception {
        NotesRepository.destroyInstance();
    }

    @Test
    public void getNotesFromLocalDataSource() throws Exception {
        mNotesRepository.getNotes(mLoadNotesCallback);
        verify(mNotesLocalDataSource).getNotes(any(NotesDataSource.LoadNotesCallback.class));
    }

    @Test
    public void getNoteFromLocalDataSource() throws Exception {
        String id = "123";

        mNotesRepository.getNote(id, mGetNoteCallback);
        verify(mNotesLocalDataSource).getNote(eq(id), any(NotesDataSource.GetNoteCallback.class));
    }

    @Test
    public void saveNoteToLocalDataSource() throws Exception {
        Note note = new Note("Sample Text");

        mNotesRepository.saveNote(note);
        verify(mNotesLocalDataSource).saveNote(note);
    }

    @Test
    public void updateNoteFromLocalDataSource() throws Exception {
        Note note = new Note("Sample Text");

        mNotesRepository.updateNote(note);
        verify(mNotesLocalDataSource).updateNote(note);
    }

    @Test
    public void deleteNoteFromLocalDataSource() throws Exception {
        String id = "123";

        mNotesRepository.deleteNote(id);
        verify(mNotesLocalDataSource).deleteNote(id);
    }

    @Test
    public void deleteAllNotesFromLocalDataSource() throws Exception {
        mNotesRepository.deleteNotes();
        verify(mNotesLocalDataSource).deleteNotes();
    }
}
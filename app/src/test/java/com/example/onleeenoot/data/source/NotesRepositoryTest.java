package com.example.onleeenoot.data.source;

import com.example.onleeenoot.data.Note;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by Clarence on 10/20/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class NotesRepositoryTest {
    private NotesRepository mNotesRepository;

    @Mock
    private NotesDataSource mNotesLocalDataSource;

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
        mNotesRepository.getNotes();
        verify(mNotesLocalDataSource).getNotes();
    }

    @Test
    public void getNoteFromLocalDataSource() throws Exception {
        String id = "123";

        mNotesRepository.getNote(id);
        verify(mNotesLocalDataSource).getNote(id);
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
}
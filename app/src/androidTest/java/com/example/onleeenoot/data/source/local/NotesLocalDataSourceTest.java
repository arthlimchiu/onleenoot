package com.example.onleeenoot.data.source.local;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.onleeenoot.data.Note;
import com.example.onleeenoot.data.source.NotesDataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by Clarence on 10/21/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class NotesLocalDataSourceTest {

    private NotesLocalDataSource mNotesLocalDataSource;

    @Before
    public void setUp() throws Exception {
        mNotesLocalDataSource = NotesLocalDataSource.getInstance(InstrumentationRegistry.getTargetContext());
    }

    @After
    public void tearDown() throws Exception {
        mNotesLocalDataSource.deleteNotes();
    }

    @Test
    public void testPreConditions() throws Exception {
        assertNotNull(mNotesLocalDataSource);
    }

    @Test
    public void saveNoteAndCheckIfItIsSavedInLocalDatabase() throws Exception {
        final Note newNote = new Note("abc123");

        mNotesLocalDataSource.saveNote(newNote, new NotesDataSource.SaveNoteCallback() {
            @Override
            public void onNoteSaved(long id) {
                newNote.setId(id);
            }

            @Override
            public void onError() {
                fail("Data not saved");
            }
        });

        mNotesLocalDataSource.getNote(String.valueOf(newNote.getId()), new NotesDataSource.GetNoteCallback() {
            @Override
            public void onNoteLoaded(Note note) {
                assertEquals(newNote.getId(), note.getId());
            }

            @Override
            public void onDataNotAvailable() {
                fail("Callback Error");
            }
        });
    }

    @Test
    public void updateNoteAndCheckIfItIsUpdated() throws Exception {
        final Note newNote = new Note("abc123");

        mNotesLocalDataSource.saveNote(newNote, new NotesDataSource.SaveNoteCallback() {
            @Override
            public void onNoteSaved(long id) {
                newNote.setId(id);
            }

            @Override
            public void onError() {
                fail("Data not saved");
            }
        });

        newNote.setText("newtext");

        mNotesLocalDataSource.updateNote(newNote);

        mNotesLocalDataSource.getNote(String.valueOf(newNote.getId()), new NotesDataSource.GetNoteCallback() {
            @Override
            public void onNoteLoaded(Note note) {
                assertEquals(note.getText(), "newtext");
            }

            @Override
            public void onDataNotAvailable() {
                fail("Callback Error");
            }
        });
    }

    @Test
    public void deleteAllTasksAndCheckIfLocalDatabaseIsEmpty() throws Exception {
        final Note note = new Note("abc123");

        mNotesLocalDataSource.saveNote(note, mock(NotesDataSource.SaveNoteCallback.class));

        NotesDataSource.LoadNotesCallback callback = mock(NotesDataSource.LoadNotesCallback.class);

        mNotesLocalDataSource.deleteNotes();

        mNotesLocalDataSource.getNotes(callback);

        verify(callback).onDataNotAvailable();
        verify(callback, never()).onNotesLoaded(anyList());
    }

    @Test
    public void deleteANoteAndCheckIfItIsDeletedFromLocalDatabase() throws Exception {
        final Note note = new Note("abc123");

        mNotesLocalDataSource.saveNote(note, new NotesDataSource.SaveNoteCallback() {
            @Override
            public void onNoteSaved(long id) {
                note.setId(id);
            }

            @Override
            public void onError() {
                fail("Callback error");
            }
        });

        mNotesLocalDataSource.deleteNote(String.valueOf(note.getId()));

        NotesDataSource.GetNoteCallback callback = mock(NotesDataSource.GetNoteCallback.class);

        mNotesLocalDataSource.getNote(String.valueOf(note.getId()), callback);

        verify(callback).onDataNotAvailable();
        verify(callback, never()).onNoteLoaded(Matchers.<Note>anyObject());
    }
}
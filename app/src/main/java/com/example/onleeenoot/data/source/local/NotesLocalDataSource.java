package com.example.onleeenoot.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.onleeenoot.data.Note;
import com.example.onleeenoot.data.source.NotesDataSource;

import java.util.ArrayList;
import java.util.List;

public class NotesLocalDataSource implements NotesDataSource {
    private static NotesLocalDataSource INSTANCE;

    private NotesDBHelper mDBHelper;

    private NotesLocalDataSource(Context context) {
        mDBHelper = new NotesDBHelper(context);
    }

    public static NotesLocalDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new NotesLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getNotes(LoadNotesCallback callback) {
        List<Note> notes = new ArrayList<>();
        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        String[] projection = {
                NotesTable.COLUMN_ID,
                NotesTable.COLUMN_TEXT
        };

        Cursor c = db.query(NotesTable.TABLE_NAME, projection, null, null, null, null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                long id = c.getLong(c.getColumnIndexOrThrow(NotesTable.COLUMN_ID));
                String text = c.getString(c.getColumnIndexOrThrow(NotesTable.COLUMN_TEXT));

                Note note = new Note(id, text);
                notes.add(note);
            }
        }
        if (c != null) {
            c.close();
        }

        db.close();

        if (notes.isEmpty()) {
            callback.onDataNotAvailable();
        } else {
            callback.onNotesLoaded(notes);
        }
    }

    @Override
    public void getNote(String id, GetNoteCallback callback) {
        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        String[] projection = {
                NotesTable.COLUMN_ID,
                NotesTable.COLUMN_TEXT
        };

        String selection = NotesTable.COLUMN_ID + "=?";
        String[] selectionArgs = { id };

        Cursor c = db.query(NotesTable.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        Note note = null;

        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            long noteId = c.getLong(c.getColumnIndexOrThrow(NotesTable.COLUMN_ID));
            String text = c.getString(c.getColumnIndexOrThrow(NotesTable.COLUMN_TEXT));

            note = new Note(noteId, text);
        }
        if (c != null) {
            c.close();
        }

        db.close();

        if (note != null) {
            callback.onNoteLoaded(note);
        } else {
            callback.onDataNotAvailable();
        }
    }

    @Override
    public void saveNote(Note note) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NotesTable.COLUMN_TEXT, note.getText());

        db.insert(NotesTable.TABLE_NAME, null, values);

        db.close();
    }

    @Override
    public void updateNote(Note note) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NotesTable.COLUMN_TEXT, note.getText());

        String selection = NotesTable.COLUMN_ID + "=?";
        String[] selectionArgs = { String.valueOf(note.getId()) };

        db.update(NotesTable.TABLE_NAME, values, selection, selectionArgs);

        db.close();
    }

    @Override
    public void deleteNote(String id) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();

        String selection = NotesTable.COLUMN_ID + "=?";
        String[] selectionArgs = { id };

        db.delete(NotesTable.TABLE_NAME, selection, selectionArgs);

        db.close();
    }

    @Override
    public void deleteNotes() {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();

        db.delete(NotesTable.TABLE_NAME, null, null);

        db.close();
    }
}

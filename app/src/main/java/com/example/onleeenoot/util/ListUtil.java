package com.example.onleeenoot.util;

import com.example.onleeenoot.data.Note;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {

    public static List<Note> toList(String[] list) {
        if (list == null) {
            return null;
        }

        List<Note> notes = new ArrayList<>(list.length);
        for (String item : list) {
            Note note = new Note();
            note.setText(item);
            notes.add(note);
        }
        return notes;
    }
}

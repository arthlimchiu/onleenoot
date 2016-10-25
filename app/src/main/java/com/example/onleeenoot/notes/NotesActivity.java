package com.example.onleeenoot.notes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.onleeenoot.R;
import com.example.onleeenoot.data.source.NotesRepository;
import com.example.onleeenoot.data.source.local.NotesLocalDataSource;

public class NotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        NotesFragment notesFragment =
                (NotesFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (notesFragment == null) {
            notesFragment = NotesFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_frame, notesFragment)
                    .commit();
        }

        new NotesPresenter(notesFragment, NotesRepository.getInstance(NotesLocalDataSource.getInstance(this)));
    }

}

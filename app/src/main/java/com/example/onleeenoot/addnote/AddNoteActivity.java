package com.example.onleeenoot.addnote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.onleeenoot.R;
import com.example.onleeenoot.data.source.NotesRepository;
import com.example.onleeenoot.data.source.local.NotesLocalDataSource;

public class AddNoteActivity extends AppCompatActivity implements AddNoteContract.View {

    private AddNotePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        presenter = new AddNotePresenter(this, NotesRepository.getInstance(NotesLocalDataSource.getInstance(this)));
    }

    @Override
    public void startNotesActivity() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showEmptyNoteError() {
        Toast.makeText(AddNoteActivity.this, "Please enter a text", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedToSaveError() {
        Toast.makeText(AddNoteActivity.this, "Failed to save", Toast.LENGTH_SHORT).show();
    }
}

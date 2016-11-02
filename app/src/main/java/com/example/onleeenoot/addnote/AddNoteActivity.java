package com.example.onleeenoot.addnote;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onleeenoot.R;
import com.example.onleeenoot.data.source.NotesRepository;
import com.example.onleeenoot.data.source.local.NotesLocalDataSource;

public class AddNoteActivity extends AppCompatActivity implements AddNoteContract.View {

    private AddNotePresenter presenter;
    private EditText mNote;
    private Button mSave;
    private String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        mId = getIntent().getStringExtra("id");

        presenter = new AddNotePresenter(mId, this, NotesRepository.getInstance(NotesLocalDataSource.getInstance(this)));

        mNote = (EditText) findViewById(R.id.note);
        mSave = (Button) findViewById(R.id.save);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.saveNote(mNote.getText().toString());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mId != null) {
            presenter.populateNote();
        }
    }

    @Override
    public void startNotesActivity() {
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

    @Override
    public void showText(String text) {
        mNote.setText(text);
    }
}

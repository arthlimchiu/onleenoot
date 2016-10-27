package com.example.onleeenoot.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.onleeenoot.R;
import com.example.onleeenoot.data.source.NotesRepository;
import com.example.onleeenoot.data.source.local.NotesLocalDataSource;

public class NoteDetailsActivity extends AppCompatActivity implements NoteDetailsContract.View {

    TextView mText;
    private String mId;
    private NoteDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        mId = getIntent().getStringExtra("id");

        mText = (TextView) findViewById(R.id.text);
        presenter = new NoteDetailsPresenter(mId, this, NotesRepository.getInstance(NotesLocalDataSource.getInstance(this)));
        presenter.loadNote();
    }

    @Override
    public void showText(String text) {
        mText.setText(text);
    }
}

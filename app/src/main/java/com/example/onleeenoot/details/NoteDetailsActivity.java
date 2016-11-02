package com.example.onleeenoot.details;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.onleeenoot.R;
import com.example.onleeenoot.addnote.AddNoteActivity;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                presenter.deleteNote();
                return true;
            case R.id.edit:
                presenter.editNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showText(String text) {
        mText.setText(text);
    }

    @Override
    public void showNoteDeleted() {
        finish();
    }

    @Override
    public void showEditNote(String id) {
        Intent i = new Intent(NoteDetailsActivity.this, AddNoteActivity.class);
        i.putExtra("id", mId);
        startActivity(i);
        finish();
    }
}

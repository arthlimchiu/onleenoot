package com.example.onleeenoot.notes;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.onleeenoot.R;
import com.example.onleeenoot.addnote.AddNoteActivity;
import com.example.onleeenoot.data.Note;

import java.util.List;

public class NotesFragment extends Fragment implements NotesContract.View {

    private RecyclerView mRecycler;
    private NotesContract.Presenter mPresenter;
    private NotesAdapter mAdapter;
    private ProgressDialog mProgressView;
    private FloatingActionButton mFab;

    public NotesFragment() {
    }

    public static NotesFragment newInstance() {
        return new NotesFragment();
    }

    @Override
    public void setPresenter(NotesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle icicle) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mRecycler = (RecyclerView) view.findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(lm);

        mFab = (FloatingActionButton) view.findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.addNewNote();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadNotes();
    }

    @Override
    public void showLoadingIndicator(boolean isLoading) {
        if (isLoading) {
            mProgressView = ProgressDialog.show(getContext(), "", "Loading...", true, false);
            mProgressView.show();
        } else {
            mProgressView.dismiss();
        }
    }

    @Override
    public void showNotes(final List<Note> notes) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                mAdapter = new NotesAdapter(notes);
                mRecycler.setAdapter(mAdapter);
            }
        });
    }

    @Override
    public void launchAddNewNoteScreen() {
        Intent intent = new Intent(getContext(), AddNoteActivity.class);
        startActivity(intent);
    }

    class NotesAdapter extends RecyclerView.Adapter<NotesViewHolder> {

        private List<Note> notes;

        NotesAdapter(List<Note> notes) {
            this.notes = notes;
        }

        @Override
        public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View rootView = LayoutInflater.from(getContext()).inflate(
                    android.R.layout.simple_list_item_1, parent, false);
            return new NotesViewHolder(rootView);
        }

        @Override
        public void onBindViewHolder(NotesViewHolder holder, int position) {
            holder.setTitle(notes.get(position).getText());
        }

        @Override
        public int getItemCount() {
            return notes.size();
        }
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        private TextView mText;

        NotesViewHolder(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(android.R.id.text1);
        }

        void setTitle(String text) {
            mText.setText(text);
        }
    }
}

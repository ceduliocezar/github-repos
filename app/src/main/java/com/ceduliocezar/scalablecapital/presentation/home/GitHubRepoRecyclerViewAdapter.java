package com.ceduliocezar.scalablecapital.presentation.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ceduliocezar.scalablecapital.R;
import com.ceduliocezar.scalablecapital.logging.AppLog;
import com.ceduliocezar.scalablecapital.model.entities.GitHubRepo;

import java.util.List;


public class GitHubRepoRecyclerViewAdapter extends RecyclerView.Adapter<GitHubRepoRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "GitHubRepoRecyclerViewAdapter";
    private final List<GitHubRepo> mValues;

    public GitHubRepoRecyclerViewAdapter(List<GitHubRepo> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_githubrepo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        GitHubRepo gitHubRepo = mValues.get(position);
        holder.mIdView.setText(gitHubRepo.getId());
        holder.mContentView.setText(gitHubRepo.getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppLog.d(TAG, "onClick: ");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void addRepos(List<GitHubRepo> repositories) {
        int oldSize = mValues.size();
        mValues.addAll(repositories);
        notifyItemRangeInserted(oldSize, repositories.size() - 1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}

package ru.mobiledev.demonetwork.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import ru.mobiledev.demonetwork.R;
import ru.mobiledev.demonetwork.data.model.Repository;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> {

    private List<Repository> repositories;

    public RepositoryAdapter() {
        this.repositories = Collections.emptyList();
    }

    public RepositoryAdapter(List<Repository> repositories) {
        this.repositories = repositories;
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_repo, parent, false);
        return new RepositoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        Repository repository = repositories.get(position);
        Context context = holder.titleTextView.getContext();
        holder.repository = repository;
        holder.titleTextView.setText(repository.getName());
        holder.descriptionTextView.setText(repository.getDescription());
        holder.watchersTextView.setText(
                context.getResources().getString(R.string.text_watchers, repository.getWatchers()));
        holder.starsTextView.setText(
                context.getResources().getString(R.string.text_stars, repository.getStars()));
        holder.forksTextView.setText(
                context.getResources().getString(R.string.text_forks, repository.getForks()));
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public static class RepositoryViewHolder extends RecyclerView.ViewHolder {
        public View contentLayout;
        public TextView titleTextView;
        public TextView descriptionTextView;
        public TextView watchersTextView;
        public TextView starsTextView;
        public TextView forksTextView;
        public Repository repository;

        public RepositoryViewHolder(View itemView) {
            super(itemView);
            contentLayout = itemView.findViewById(R.id.layout_content);
            titleTextView = (TextView) itemView.findViewById(R.id.text_repo_title);
            descriptionTextView = (TextView) itemView.findViewById(R.id.text_repo_description);
            watchersTextView = (TextView) itemView.findViewById(R.id.text_watchers);
            starsTextView = (TextView) itemView.findViewById(R.id.text_stars);
            forksTextView = (TextView) itemView.findViewById(R.id.text_forks);
        }
    }
}

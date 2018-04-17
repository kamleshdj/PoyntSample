package com.example.kamlesh.poyntcommits;

import android.support.v7.widget.RecyclerView;
import java.util.List;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import org.w3c.dom.Text;

public class CommitRowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<GitCommit> commitHistoryList;

    public CommitRowAdapter(List<GitCommit> list) {
        this.commitHistoryList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commit, parent, false);
        return new CommitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GitCommit item = commitHistoryList.get(position);
        if (item != null) {
            ((CommitViewHolder) holder).commitId.setText(item.getId());
            ((CommitViewHolder) holder).commitName.setText(item.getName());
            ((CommitViewHolder) holder).commitDate.setText(item.getDateAsString());
        }
    }

    @Override
    public int getItemCount() {
        return  (commitHistoryList == null) ? 0 : commitHistoryList.size();
    }

    public static class CommitViewHolder extends RecyclerView.ViewHolder {
        private TextView commitId;
        private TextView commitName;
        private TextView commitDate;

        public CommitViewHolder(View itemView) {
            super(itemView);

            commitId = itemView.findViewById(R.id.commitIdTextView);
            commitName = itemView.findViewById(R.id.commitNameTextView);
            commitDate = itemView.findViewById(R.id.commitDateTextView);
        }

    }

}

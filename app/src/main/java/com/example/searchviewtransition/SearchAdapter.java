package com.example.searchviewtransition;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Arnold Laishram on 03/04/18.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<String> searchResults;

    public SearchAdapter(List<String> searchResults) {
        this.searchResults = searchResults;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_result_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final String searchResult = searchResults.get(position);

        holder.textResult.setText(searchResult);
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textResult;

        public ViewHolder(View itemView) {
            super(itemView);
            textResult = itemView.findViewById(R.id.text_result);
        }

    }
}


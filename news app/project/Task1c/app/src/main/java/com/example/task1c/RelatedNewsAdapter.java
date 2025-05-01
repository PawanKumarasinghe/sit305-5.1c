package com.example.task1c;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.List;

public class RelatedNewsAdapter extends RecyclerView.Adapter<RelatedNewsAdapter.RelatedNewsViewHolder> {
    private List<NewsItem> relatedNews;
    private Context context;

    public RelatedNewsAdapter(Context context, List<NewsItem> relatedNews) {
        this.context = context;
        this.relatedNews = relatedNews;
    }

    @NonNull
    @Override
    public RelatedNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news_vertical, parent, false);
        return new RelatedNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedNewsViewHolder holder, int position) {
        NewsItem item = relatedNews.get(position);
        holder.title.setText(item.getTitle());
        Glide.with(context).load(item.getImageUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return relatedNews.size();
    }

    public static class RelatedNewsViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        public RelatedNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.news_title);
            image = itemView.findViewById(R.id.news_image);
        }
    }
}


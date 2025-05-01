package com.example.vibetube.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vibetube.R;
import com.example.vibetube.data.ClipItem;
import java.util.List;

public class VideoEntryAdapter extends RecyclerView.Adapter<VideoEntryAdapter.ViewHolder> {

    public interface ClipClickListener {
        void onClipClick(ClipItem item);
    }

    private final Context context;
    private final List<ClipItem> data;
    private final ClipClickListener listener;

    public VideoEntryAdapter(Context context, List<ClipItem> data, ClipClickListener listener) {
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_clip_entry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClipItem item = data.get(position);
        holder.label.setText(item.getLabel());
        holder.itemView.setOnClickListener(v -> listener.onClipClick(item));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView label;
        ViewHolder(View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.clip_label);
        }
    }
}

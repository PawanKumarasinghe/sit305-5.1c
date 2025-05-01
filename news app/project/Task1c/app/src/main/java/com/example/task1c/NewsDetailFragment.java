package com.example.task1c;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class NewsDetailFragment extends Fragment {
    private static final String ARG_TITLE = "title";
    private static final String ARG_DESC = "description";
    private static final String ARG_IMAGE = "imageUrl";

    public static NewsDetailFragment newInstance(NewsItem item) {
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, item.getTitle());
        args.putString(ARG_DESC, item.getDescription());
        args.putString(ARG_IMAGE, item.getImageUrl());
        NewsDetailFragment fragment = new NewsDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail, container, false);

        TextView title = view.findViewById(R.id.detail_title);
        TextView desc = view.findViewById(R.id.detail_description);
        ImageView image = view.findViewById(R.id.detail_image);
        RecyclerView relatedRv = view.findViewById(R.id.rv_related_news);

        Bundle args = getArguments();
        if (args != null) {
            title.setText(args.getString(ARG_TITLE));
            desc.setText(args.getString(ARG_DESC));
            Glide.with(this).load(args.getString(ARG_IMAGE)).into(image);
        }

        relatedRv.setLayoutManager(new LinearLayoutManager(getContext()));
        relatedRv.setAdapter(new RelatedNewsAdapter(getContext(), new ArrayList<>()));

        return view;
    }
}

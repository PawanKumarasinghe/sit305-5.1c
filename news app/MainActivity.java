//HomeFragment.java

package com.example.task1c;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView topStoriesRv, newsRv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        topStoriesRv = view.findViewById(R.id.rv_top_stories);
        newsRv = view.findViewById(R.id.rv_news);

        List<NewsItem> fullList = getDummyNews();
        List<NewsItem> topStories = new ArrayList<>();
        List<NewsItem> latestNews = new ArrayList<>();

        // Split into top stories and others
        for (NewsItem item : fullList) {
            if (item.isTopStory()) {
                topStories.add(item);
            } else {
                latestNews.add(item);
            }
        }

        NewsAdapter.OnItemClickListener listener = item -> {
            NewsDetailFragment fragment = NewsDetailFragment.newInstance(item);
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        };

        topStoriesRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        newsRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        topStoriesRv.setAdapter(new NewsAdapter(getContext(), topStories, listener));
        newsRv.setAdapter(new NewsAdapter(getContext(), latestNews, listener));

        return view;
    }

    private List<NewsItem> getDummyNews() {
        List<NewsItem> list = new ArrayList<>();

        list.add(new NewsItem(
                "NASA Discovers Earth-Like Planet",
                "NASA's James Webb Telescope has identified a planet with Earth-like features in a nearby solar system.",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTYH9GmRCu40IN9FgAeDfYjOE8WyuIYYC7tiA&s",
                true
        ));

        list.add(new NewsItem(
                "Global Markets Rally as Inflation Eases",
                "Stock markets around the world surge after key inflation reports show a slowdown.",
                "https://images.moneycontrol.com/static-mcnews/2025/01/20250107030355_Sensex_up_higher.jpg?impolicy=website&width=1280&height=720",
                true
        ));

        list.add(new NewsItem(
                "New AI Tool Can Detect Fraud in Real-Time",
                "Researchers develop an AI model that accurately flags suspicious transactions within milliseconds.",
                "https://redresscompliance.com/wp-content/uploads/2024/06/Benefits-of-AI-in-Fraud-Detection-1024x585.webp",
                false
        ));

        list.add(new NewsItem(
                "World Cup 2026: Schedule and Host Cities Announced",
                "FIFA reveals the official schedule and host cities for the 2026 World Cup.",
                "https://mders.org/wp-content/uploads/2024/11/IMG-3-1024x675.jpeg",
                false
        ));

        list.add(new NewsItem(
                "Breakthrough in Cancer Research",
                "Scientists discover a new protein pathway that could lead to more effective cancer treatments.",
                "https://cdn.images.express.co.uk/img/dynamic/151/590x/1529449_1.jpg",
                false
        ));

        return list;
    }
}


//NewsAdapter.java


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

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<NewsItem> newsList;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(NewsItem item);
    }

    public NewsAdapter(Context context, List<NewsItem> newsList, OnItemClickListener listener) {
        this.context = context;
        this.newsList = newsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news_horizontal, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsItem item = newsList.get(position);
        holder.title.setText(item.getTitle());
        Glide.with(context).load(item.getImageUrl()).into(holder.image);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(item));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.news_title);
            image = itemView.findViewById(R.id.news_image);
        }
    }
}


//NewsDetailFragment.java




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



//NewsItem.java


package com.example.task1c;

public class NewsItem {
    private String title;
    private String description;
    private String imageUrl;
    private boolean isTopStory;

    public NewsItem(String title, String description, String imageUrl, boolean isTopStory) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.isTopStory = isTopStory;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public boolean isTopStory() { return isTopStory; }
}



//RelatedNewsAdapter.java


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












package com.example.task1c;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new HomeFragment())
                .commit();
    }
}

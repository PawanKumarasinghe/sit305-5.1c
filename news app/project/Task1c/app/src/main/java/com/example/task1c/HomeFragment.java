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

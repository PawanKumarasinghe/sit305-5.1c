package com.example.vibetube.screens;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.vibetube.R;

public class ClipPlayerFragment extends Fragment {

    private static final String ARG_VIDEO_ID = "video_id";

    public static ClipPlayerFragment newInstance(String videoId) {
        ClipPlayerFragment fragment = new ClipPlayerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_VIDEO_ID, videoId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clip_player, container, false);
        WebView webView = view.findViewById(R.id.clip_webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        String videoId = getArguments() != null ? getArguments().getString(ARG_VIDEO_ID) : null;

        if (!TextUtils.isEmpty(videoId)) {
            String embedHtml = "<html><body style='margin:0;padding:0;'>" +
                    "<iframe width='100%' height='100%' " +
                    "src='https://www.youtube.com/embed/" + videoId + "' " +
                    "frameborder='0' allow='accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture' allowfullscreen>" +
                    "</iframe></body></html>";

            webView.loadDataWithBaseURL(null, embedHtml, "text/html", "utf-8", null);
        }

        return view;
    }
}

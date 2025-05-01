package com.example.vibetube.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vibetube.MainActivity;
import com.example.vibetube.R;
import com.example.vibetube.components.VideoEntryAdapter;
import com.example.vibetube.data.ClipItem;
import com.example.vibetube.data.ClipStorageHelper;
import java.util.List;

public class ClipListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clip_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rv_clips);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ClipStorageHelper helper = new ClipStorageHelper(requireContext());
        List<ClipItem> clips = helper.fetchAll();

        recyclerView.setAdapter(new VideoEntryAdapter(getContext(), clips, clip -> {
            ClipPlayerFragment player = ClipPlayerFragment.newInstance(clip.getVideoId());
            ((MainActivity) requireActivity()).navigateTo(player);
        }));

        return view;
    }
}

package com.example.vibetube.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.vibetube.MainActivity;
import com.example.vibetube.R;
import com.example.vibetube.data.ClipItem;
import com.example.vibetube.data.ClipStorageHelper;

public class ClipFormFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clip_form, container, false);

        EditText titleField = view.findViewById(R.id.input_label);
        EditText idField = view.findViewById(R.id.input_video_id);
        Button saveBtn = view.findViewById(R.id.btn_save_clip);
        Button openList = view.findViewById(R.id.btn_go_list);

        ClipStorageHelper helper = new ClipStorageHelper(requireContext());

        saveBtn.setOnClickListener(v -> {
            String label = titleField.getText().toString().trim();
            String videoId = idField.getText().toString().trim();
            if (label.isEmpty() || videoId.isEmpty()) {
                Toast.makeText(getContext(), "Fields can't be empty", Toast.LENGTH_SHORT).show();
            } else {
                helper.insertClip(new ClipItem(label, videoId));
                Toast.makeText(getContext(), "Saved!", Toast.LENGTH_SHORT).show();
                titleField.setText("");
                idField.setText("");
            }
        });

        openList.setOnClickListener(v -> ((MainActivity) requireActivity()).navigateTo(new ClipListFragment()));

        return view;
    }
}

package com.example.vibetube.auth;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.vibetube.screens.ClipFormFragment;

public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        EditText usernameEt = view.findViewById(R.id.username);
        EditText passwordEt = view.findViewById(R.id.password);
        Button loginBtn = view.findViewById(R.id.btn_login);
        Button signupBtn = view.findViewById(R.id.btn_signup);

        AuthDatabaseHelper db = new AuthDatabaseHelper(requireContext());

        loginBtn.setOnClickListener(v -> {
            String username = usernameEt.getText().toString().trim();
            String password = passwordEt.getText().toString().trim();

            if (db.authenticate(username, password)) {
                SharedPreferences prefs = requireActivity().getSharedPreferences("vibe_session", Context.MODE_PRIVATE);
                prefs.edit().putString("logged_user", username).apply();
                ((MainActivity) requireActivity()).navigateTo(new ClipFormFragment());
            } else {
                Toast.makeText(getContext(), "Login failed", Toast.LENGTH_SHORT).show();
            }
        });

        signupBtn.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).navigateTo(new SignupFragment());
        });

        return view;
    }
}
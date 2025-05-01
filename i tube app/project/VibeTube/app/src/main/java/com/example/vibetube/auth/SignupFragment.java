package com.example.vibetube.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.vibetube.R;

public class SignupFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        EditText fullNameEt = view.findViewById(R.id.fullname);
        EditText usernameEt = view.findViewById(R.id.username);
        EditText passwordEt = view.findViewById(R.id.password);
        EditText confirmEt = view.findViewById(R.id.confirm);
        Button createBtn = view.findViewById(R.id.btn_create);

        AuthDatabaseHelper db = new AuthDatabaseHelper(requireContext());

        createBtn.setOnClickListener(v -> {
            String fullName = fullNameEt.getText().toString().trim();
            String username = usernameEt.getText().toString().trim();
            String password = passwordEt.getText().toString();
            String confirm = confirmEt.getText().toString();

            if (fullName.isEmpty() || username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(getContext(), "All fields required", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirm)) {
                Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                boolean success = db.registerUser(fullName, username, password);
                if (success) {
                    Toast.makeText(getContext(), "Account created!", Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStack();
                } else {
                    Toast.makeText(getContext(), "Username already exists", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}

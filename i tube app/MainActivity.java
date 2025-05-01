package com.example.vibetube;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.vibetube.auth.LoginFragment;
import com.example.vibetube.screens.ClipFormFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = getSharedPreferences("vibe_session", MODE_PRIVATE);
        String username = prefs.getString("logged_user", null);

        if (username != null) {
            // User is logged in → show clip form screen
            navigateTo(new ClipFormFragment());
        } else {
            // No session → show login screen
            navigateTo(new LoginFragment());
        }
    }

    public void navigateTo(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_main, fragment)
                .addToBackStack(null)
                .commit();
    }
}
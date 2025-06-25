package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityHome extends AppCompatActivity implements View.OnClickListener {

    private View profile, playWithFriend, playWithComputer, leaderBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        View rootView = findViewById(android.R.id.content);
        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.exit_button).setOnClickListener(v -> {
            Toast.makeText(this, "Exiting App", Toast.LENGTH_SHORT).show();
            finish();
        });

        profile = findViewById(R.id.log_in);
        playWithFriend = findViewById(R.id.play_friend);
        playWithComputer = findViewById(R.id.play_computer);
        leaderBoard = findViewById(R.id.Leader_Broard);

        profile.setOnClickListener(this);
        playWithComputer.setOnClickListener(this);
        playWithFriend.setOnClickListener(this);
        leaderBoard.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.log_in) {
            startActivity(new Intent(ActivityHome.this, profile.class));
        } else if (id == R.id.play_friend) {
            startActivity(new Intent(ActivityHome.this,playfriend.class));
        } else if (id == R.id.play_computer) {
            startActivity(new Intent(ActivityHome.this,MainActivity.class));
        } else if (id == R.id.Leader_Broard) {
            startActivity(new Intent(ActivityHome.this, Leader_Broard.class));
        }
    }



}

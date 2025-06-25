package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class playfriend extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, reset;
    boolean playerXTurn = true; // X always starts
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_playfriend);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        reset = findViewById(R.id.reset);
    }

    private void init() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
    }

    public void check(View view) {
        Button btnCurrent = (Button) view;

        if (!btnCurrent.getText().toString().equals("")) return;

        if (playerXTurn) {
            btnCurrent.setText("X");
        } else {
            btnCurrent.setText("O");
        }

        count++;

        if (checkWinner(playerXTurn ? "X" : "O")) {
            Toast.makeText(this, (playerXTurn ? "Player X" : "Player O") + " Wins!", Toast.LENGTH_SHORT).show();
            disableButtons();
            return;
        }

        if (count == 9) {
            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
            return;
        }

        playerXTurn = !playerXTurn; // Switch turn
    }

    private boolean checkWinner(String player) {
        String b1 = btn1.getText().toString();
        String b2 = btn2.getText().toString();
        String b3 = btn3.getText().toString();
        String b4 = btn4.getText().toString();
        String b5 = btn5.getText().toString();
        String b6 = btn6.getText().toString();
        String b7 = btn7.getText().toString();
        String b8 = btn8.getText().toString();
        String b9 = btn9.getText().toString();

        return (b1.equals(player) && b2.equals(player) && b3.equals(player)) ||
                (b4.equals(player) && b5.equals(player) && b6.equals(player)) ||
                (b7.equals(player) && b8.equals(player) && b9.equals(player)) ||
                (b1.equals(player) && b4.equals(player) && b7.equals(player)) ||
                (b2.equals(player) && b5.equals(player) && b8.equals(player)) ||
                (b3.equals(player) && b6.equals(player) && b9.equals(player)) ||
                (b1.equals(player) && b5.equals(player) && b9.equals(player)) ||
                (b3.equals(player) && b5.equals(player) && b7.equals(player));
    }

    private void disableButtons() {
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        btn5.setEnabled(false);
        btn6.setEnabled(false);
        btn7.setEnabled(false);
        btn8.setEnabled(false);
        btn9.setEnabled(false);
    }

    public void reset(View v) {
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");

        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        btn5.setEnabled(true);
        btn6.setEnabled(true);
        btn7.setEnabled(true);
        btn8.setEnabled(true);
        btn9.setEnabled(true);

        count = 0;
        playerXTurn = true;
    }
}

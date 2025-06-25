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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, reset;
    int count = 0;
    int difficultyLevel = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
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

        btnCurrent.setText("X");
        count++;

        if (checkWinner("X")) {
            Toast.makeText(this, "You Win!", Toast.LENGTH_SHORT).show();
            disableButtons();
            return;
        }

        if (count == 9) {
            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
            return;
        }

        aiMove();

        if (checkWinner("O")) {
            Toast.makeText(this, "AI Bot Wins!", Toast.LENGTH_SHORT).show();
            disableButtons();
        } else if (count == 9) {
            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        }
    }

    private void aiMove() {
        Button[][] board = {
                {btn1, btn2, btn3},
                {btn4, btn5, btn6},
                {btn7, btn8, btn9}
        };

        Button bestBtn = null;

        switch (difficultyLevel) {
            case 1:
                bestBtn = getRandomMove(board);
                break;
            case 2:
                if (Math.random() < 0.5)
                    bestBtn = getRandomMove(board);
                else
                    bestBtn = getBestMove(board, 1);
                break;
            case 3:
                bestBtn = getBestMove(board, 3);
                break;
            case 4:
                bestBtn = getBestMove(board, 5);
                break;
            case 5:
                bestBtn = getBestMove(board, 9);
                break;
        }

        if (bestBtn != null) {
            bestBtn.setText("O");
            count++;
        }
    }

    private Button getBestMove(Button[][] board, int maxDepth) {
        int bestScore = Integer.MIN_VALUE;
        Button bestMove = null;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].getText().toString().equals("")) {
                    board[i][j].setText("O");
                    int score = minimax(board, 0, false, maxDepth);
                    board[i][j].setText("");
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = board[i][j];
                    }
                }
            }
        }

        return bestMove;
    }

    private Button getRandomMove(Button[][] board) {
        List<Button> moves = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j].getText().toString().equals(""))
                    moves.add(board[i][j]);
        return moves.isEmpty() ? null : moves.get(new Random().nextInt(moves.size()));
    }

    private int minimax(Button[][] board, int depth, boolean isMaximizing, int maxDepth) {
        if (checkWinnerStatic(board, "O")) return 10 - depth;
        if (checkWinnerStatic(board, "X")) return depth - 10;
        if (isBoardFull(board) || depth == maxDepth) return 0;

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j].getText().toString().equals("")) {
                        board[i][j].setText("O");
                        int score = minimax(board, depth + 1, false, maxDepth);
                        board[i][j].setText("");
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j].getText().toString().equals("")) {
                        board[i][j].setText("X");
                        int score = minimax(board, depth + 1, true, maxDepth);
                        board[i][j].setText("");
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

    private boolean isBoardFull(Button[][] board) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j].getText().toString().equals(""))
                    return false;
        return true;
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

    private boolean checkWinnerStatic(Button[][] board, String player) {
        return (board[0][0].getText().toString().equals(player) &&
                board[0][1].getText().toString().equals(player) &&
                board[0][2].getText().toString().equals(player)) ||
                (board[1][0].getText().toString().equals(player) &&
                        board[1][1].getText().toString().equals(player) &&
                        board[1][2].getText().toString().equals(player)) ||
                (board[2][0].getText().toString().equals(player) &&
                        board[2][1].getText().toString().equals(player) &&
                        board[2][2].getText().toString().equals(player)) ||
                (board[0][0].getText().toString().equals(player) &&
                        board[1][0].getText().toString().equals(player) &&
                        board[2][0].getText().toString().equals(player)) ||
                (board[0][1].getText().toString().equals(player) &&
                        board[1][1].getText().toString().equals(player) &&
                        board[2][1].getText().toString().equals(player)) ||
                (board[0][2].getText().toString().equals(player) &&
                        board[1][2].getText().toString().equals(player) &&
                        board[2][2].getText().toString().equals(player)) ||
                (board[0][0].getText().toString().equals(player) &&
                        board[1][1].getText().toString().equals(player) &&
                        board[2][2].getText().toString().equals(player)) ||
                (board[0][2].getText().toString().equals(player) &&
                        board[1][1].getText().toString().equals(player) &&
                        board[2][0].getText().toString().equals(player));
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
    }
}

package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
public class profile extends AppCompatActivity {
    private EditText edit1,edit2;
    private Button text_button;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        reference= FirebaseDatabase.getInstance().getReference("ProfileDB");

         edit2=findViewById(R.id.edit_text2);
        edit1=findViewById(R.id.edit_text1);
        text_button=findViewById(R.id.exit_button);

        text_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoreFirebaseData();
            }
        });

        StoreFirebaseData();
    }


    private void StoreFirebaseData(){

        String first1=edit1.getText().toString();
        String first2=edit2.getText().toString();

        HashMap<String,String>userList=new HashMap<>();

        userList.put("Player1",first1);
        userList.put("Player2",first2);

        reference.child(reference.push().getKey()).setValue(userList).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Sign Up not Successful", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
package ru.pschsch.pschschapps.ask;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class EnterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mListener;
    private EditText mEmail;
    private EditText mPassword;
    private Button mNext;
    private Button mBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        mNext = findViewById(R.id.next2);
        mBack = findViewById(R.id.back2);
        mAuth = FirebaseAuth.getInstance();
        View.OnClickListener mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.next2:
                        mEmail = findViewById(R.id.editText3);
                        mPassword = findViewById(R.id.editText4);
                        signIn(mAuth, mEmail.getText().toString(), mPassword.getText().toString());
                    case R.id.back2:
                        Intent intent = new Intent(EnterActivity.this, MainActivity.class);
                        startActivity(intent);
                }
            }
        };
        mNext.setOnClickListener(mClickListener);
        mBack.setOnClickListener(mClickListener);

    }

    public void signIn(FirebaseAuth auth, String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(EnterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(EnterActivity.this, "Enter success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EnterActivity.this, UsersData.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(EnterActivity.this, "Enter failure", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

package ru.pschsch.pschschapps.ask;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mListener;
    private EditText mEmail;
    private EditText mPassword;
    private Button mNext;
    private Button mBack;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        mNext = findViewById(R.id.next);
        mBack = findViewById(R.id.back);
        mAuth = FirebaseAuth.getInstance();
        mListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mUser = firebaseAuth.getCurrentUser();
                if(mUser!= null){
                    Intent intent = new Intent(SignInActivity.this, UsersData.class);

                } else {

                }
            }
        };
        View.OnClickListener mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.next:
                        mEmail = findViewById(R.id.editText);
                        mPassword = findViewById(R.id.editText2);
                        register(mEmail.getText().toString(), mPassword.getText().toString());
                        break;
                    case R.id.back:
                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(intent);
                }
            }
        };
        mNext.setOnClickListener(mClickListener);
        mBack.setOnClickListener(mClickListener);

    }
    public void register(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignInActivity.this, "Регистрация успешна", Toast.LENGTH_LONG).show();
                        } else Toast.makeText(SignInActivity.this, "Bad", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}

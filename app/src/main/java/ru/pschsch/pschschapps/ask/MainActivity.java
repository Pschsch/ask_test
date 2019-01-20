package ru.pschsch.pschschapps.ask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MainActivityTheme);
        setContentView(R.layout.activity_main);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        if(mUser!=null){
            Intent intent = new Intent(MainActivity.this, UsersData.class);
            startActivity(intent);
        }
        Button regButton = findViewById(R.id.regbutton);
        Button enterButton = findViewById(R.id.enterbutton);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EnterActivity.class);
                startActivity(intent);
            }
        });
    }
}

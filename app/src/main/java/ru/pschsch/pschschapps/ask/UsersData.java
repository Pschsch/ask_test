package ru.pschsch.pschschapps.ask;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class UsersData extends AppCompatActivity {

    private DatabaseReference mReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_data);
        Button mNext = findViewById(R.id.endreg);
        mReference = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseUser mUser = mAuth.getCurrentUser();
        mReference.child("child").push().setValue("1");

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText mSurname = findViewById(R.id.editText5);
                EditText mName = findViewById(R.id.editText6);
                List<String> mList = new ArrayList<>();
                mList.add(mSurname.getText().toString());
                mList.add(mName.getText().toString());
                mReference.child(mUser.getUid()).push().setValue("something");
                Toast.makeText(UsersData.this, mUser.getUid(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

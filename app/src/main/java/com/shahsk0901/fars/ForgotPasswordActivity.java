package com.shahsk0901.fars;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ForgotPasswordActivity extends AppCompatActivity {

    private DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        final EditText nID = (EditText) findViewById(R.id.netID);
        final Button verifyIdentity = (Button) findViewById(R.id.verifyNetID);

        verifyIdentity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String netID = nID.toString();
                db = FirebaseDatabase.getInstance().getReference("Student/"+netID);
                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getValue() == null) {
                            Toast.makeText(getApplicationContext(),"Student not found",Toast.LENGTH_SHORT).show();
                        } else {
                            Student student = dataSnapshot.getValue(Student.class);

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent LoginActivity = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(LoginActivity);
    }
}













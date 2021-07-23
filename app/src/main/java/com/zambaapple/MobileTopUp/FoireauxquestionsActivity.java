package com.zambaapple.MobileTopUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.zambaapple.MobileTopUp.FoireAuxQuestions.FoireAuxQuestions;
import com.zambaapple.MobileTopUp.FoireAuxQuestions.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FoireauxquestionsActivity extends AppCompatActivity {
    FirebaseUser userser;
    ArrayList<User> users;
    DatabaseReference MyRefDatabase = FirebaseDatabase.getInstance("https://mobiletopup-2f33c-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
    Button sendbutton;
    EditText editText;
    RecyclerView recyclerView;
    FoireAuxQuestions adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foireauxquestions);
        users = new ArrayList<>();
        userser = FirebaseAuth.getInstance().getCurrentUser();
        adapter = new FoireAuxQuestions(users, userser.getUid());
        recyclerView = findViewById(R.id.recyclerviewfoireauxquestions);
        recyclerView.setLayoutManager(new LinearLayoutManager(FoireauxquestionsActivity.this));
        sendbutton = findViewById(R.id.sendbutton);
        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SenrequesttoServer();
            }
        });

        arret();
    }

    private void arret() {
        MyRefDatabase.child("Foireauxquestions").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        users.add(new User(
                                snapshot.child("username").getValue().toString(),
                                snapshot.child("date").child("time").getValue().toString(),
                                snapshot.child("message").getValue().toString(),
                                snapshot.child("UserID").getValue().toString(),
                                Boolean.getBoolean(snapshot.child("response").getValue().toString())));
                    }
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {
                System.out.println(databaseError.getMessage());
            }
        });
    }

    private void SenrequesttoServer() {
        editText = findViewById(R.id.questioninput);
        System.out.println((editText.getText().toString()));
        Map<String, Object> op = new HashMap<>();
        op.put("message", editText.getText().toString());
        op.put("username", userser.getUid());
        op.put("UserID", userser.getUid());
        op.put("response", false);
        op.put("date", new Date( ));

        MyRefDatabase.child("Foireauxquestions").push().setValue(op).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                arret();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.foireauxquestions:
                foireauxquestions();
                return true;
            case R.id.contacteznous:
                contacteznous();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void foireauxquestions() {

    }

    private void contacteznous() {
    }
}
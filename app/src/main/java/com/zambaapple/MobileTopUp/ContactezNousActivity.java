package com.zambaapple.MobileTopUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class ContactezNousActivity extends AppCompatActivity {
    TextView textView;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactez_nous);

         reference= FirebaseDatabase.getInstance("DATABASE URL").getReference("contactinfo");

         textView=findViewById(R.id.contactinfo);
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    textView.setText(snapshot.child("email").getValue().toString());
                    Log.i("Send email", "");
                    String[] TO = {""};
                    String[] CC = {""};
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setData(Uri.parse("mailto:"+textView.getText().toString()));
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                    emailIntent.putExtra(Intent.EXTRA_CC, CC);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

                    try {
                        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                        finish();
                        Log.i("Finished sending email...", "");
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(ContactezNousActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                System.out.println(error.getMessage());
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

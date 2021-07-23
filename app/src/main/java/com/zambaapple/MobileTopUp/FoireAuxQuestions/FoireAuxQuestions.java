package com.zambaapple.MobileTopUp.FoireAuxQuestions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.zambaapple.MobileTopUp.R;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.zip.Inflater;

public class FoireAuxQuestions extends RecyclerView.Adapter<FoireAuxQuestions.Holder> {
    ArrayList<User>users;
    Context context;
    DatabaseReference databaseReference;
FirebaseUser user;
String UID;

    public FoireAuxQuestions(ArrayList<User> users, String UID) {
        this.users = users;
        this.UID = UID;
    }

    @NonNull
    @NotNull
    @Override
    public Holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.questions,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Holder holder, int position) {
        user= FirebaseAuth.getInstance().getCurrentUser();
        if (user.getUid().equals(users.get(position).getUserID())){
            Date d=new Date((Long.parseLong(users.get(position).getDate())));
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd:hh-mm");
            holder.textView.setText("Ma question : "+users.get(position).getMessage()+"\n"+formatter.format(d));
        }else{
            holder.textView.setText(users.get(position).getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class Holder extends RecyclerView.ViewHolder{
TextView textView;
        public Holder(@NonNull @NotNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textviewquestions);
        }
    }
}

package ps.gov.notebookapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class allnotes extends AppCompatActivity {
    RecyclerView notesList_rv;
    NoteAdapter noteAdapter;
    List<mainnote> noteList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allnotes);

        initData();


        notesList_rv = findViewById(R.id.notesList_rv);
        notesList_rv.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(this ,noteList);
        notesList_rv.setAdapter(noteAdapter);
    }

    private void initData() {
        FirebaseDatabase.getInstance().getReference().child("note")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        noteList.clear();
                        for(DataSnapshot snapshot: dataSnapshot.getChildren() ){

                            mainnote note = snapshot.getValue(mainnote.class);
                            noteList.add(note);

                        }
                        noteAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }


}

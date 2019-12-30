package ps.gov.notebookapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

@SuppressLint("Registered")
class create_note extends AppCompatActivity {
    String email;
    String note_title;
    String note_details;
    String category_Note;
    int pass ;
    EditText titleEdittxt ;
    EditText detailsEdittxt ;
    EditText categoryNote;
    Button saveNote ;
    HashMap<String, Object> note = new HashMap<>();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allnotes);

        titleEdittxt = findViewById(R.id.titleNote);
        detailsEdittxt = findViewById(R.id.detailsNote);
        categoryNote=findViewById(R.id.categoryNote);
        saveNote = findViewById(R.id.addnote);
        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences("loginInfo", MODE_PRIVATE);
                email = prefs.getString("email", "No name defined");
                pass = prefs.getInt("password", 0);
                note_title = titleEdittxt.getText().toString();
                note_details = detailsEdittxt.getText().toString();
                category_Note=categoryNote.getText().toString();

                saveNote(getCurrentFocus());

            }
        });

    }
    public void saveNote(View v){
        note.put("Title" , note_title);
        note.put("details" , note_details);
        note.put("category_Name" , category_Note);
        String id = FirebaseDatabase.getInstance().getReference().child("note").push().getKey();
        note.put("note_id",id);

        db.collection("note").document("hUPI3W5tqkFQnwERsOJi").set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext() , "Success Saving" , Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext() , "Failed Saving" , Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package ps.gov.notebookapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class addcatogry extends AppCompatActivity {
    String note_category = "mIc2CvYReE2yhJSC0w8G";
    EditText catogry_name ;
    Button add_catogry , addcatogry ;
    Map<String , Object> note = new HashMap<>();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcatogry);
        catogry_name = findViewById(R.id.catogry_name);
        addcatogry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext() , addcatogry.class));
            }
        });
        add_catogry = findViewById(R.id.add_catogry);
        add_catogry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                note_category = catogry_name.getText().toString();

                saveNote(getCurrentFocus());

            }
        });

    }
    public void saveNote(View v){

        note.put("note_Category" , note_category);

        db.collection("category").document("mIc2CvYReE2yhJSC0w8G").set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
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

package ps.gov.notebookapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class signup extends AppCompatActivity {
    ImageView close_imgview;
    EditText emailEt, passwordEt;
    Button signUpBtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(signup.this, MainActivity.class);
            startActivity(intent);
        }

        emailEt = findViewById(R.id.signup_email_edittxt);
        passwordEt = findViewById(R.id.signup_password_edittxt);
        signUpBtn = findViewById(R.id.signup_btn);


        signUpBtn.setOnClickListener(v -> {
            doSignUp(emailEt.getText().toString(), passwordEt.getText().toString());
        });


    }

    private void doSignUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = mAuth.getCurrentUser();
                        String emailF = user.getEmail();
                        String uid = user.getUid();
                        Map<String, Object> data = new HashMap<>();
                        data.put("uid", uid);
                        data.put("email", emailF);
                        data.put("createdAt", new Date().getTime());


                        FirebaseDatabase.getInstance().getReference().child("User").child(uid).setValue(data)
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(signup.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                        Log.d("error", e.getLocalizedMessage());
                                    }
                                })
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Intent intent = new Intent(signup.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                });


                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(signup.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }

                });

    }

}
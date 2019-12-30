package ps.gov.notebookapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class category extends AppCompatActivity {
    RecyclerView catogryList_rv;
    CatogryAdapter catogryAdapter;
    List<maincatogry> catogryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        initData();


        catogryList_rv = findViewById(R.id.catogryList_rv);
        catogryList_rv.setLayoutManager(new LinearLayoutManager(this));
        catogryAdapter = new CatogryAdapter(this ,catogryList);
        catogryList_rv.setAdapter(catogryAdapter);
    }

    private void initData() {
        FirebaseDatabase.getInstance().getReference().child("category")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        catogryList.clear();
                        for(DataSnapshot snapshot: dataSnapshot.getChildren() ){

                            maincatogry catogry = snapshot.getValue(maincatogry.class);
                            catogryList.add(catogry);

                        }
                        catogryAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


    }
}

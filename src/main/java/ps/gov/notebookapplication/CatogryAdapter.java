package ps.gov.notebookapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CatogryAdapter extends RecyclerView.Adapter {
    Context context ;
    List<maincatogry> catogry;

    public CatogryAdapter(Context context , List<maincatogry> catogry) {
        this.context = context;
        this.catogry = catogry;
    }

    @NonNull

    public CatogryAdapter.BookVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_allnotes , parent , false);
        return new CatogryAdapter.BookVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }


    public void onBindViewHolder(@NonNull CatogryAdapter.BookVh holder, int position) {
        holder.setData(catogry.get(position));
    }

    public int getItemCount() {
        return catogry.size();
    }

    class BookVh extends RecyclerView.ViewHolder{
        TextView catogryName ;
        ImageView catogryImg;
        public BookVh(@NonNull View itemView) {
            super(itemView);
            catogryName = itemView.findViewById(R.id.catogryName);
            catogryImg = itemView.findViewById(R.id.catogryImg);
        }

        public void setData(maincatogry catogry) {
            catogryName.setText(catogry.getName_catogry());
        }
    }
}

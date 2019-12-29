
package ps.gov.notebookapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.BookVh> {

    Context context ;
    List<mainnote> notes;

    public NoteAdapter(Context context , List<mainnote> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public BookVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.activity_allnotes , parent , false);
        return new BookVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookVh holder, int position) {
        holder.setData(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class BookVh extends RecyclerView.ViewHolder{
        TextView  note_name     , note_desc , note_date;
        public BookVh(@NonNull View itemView) {
            super(itemView);
            note_name = itemView.findViewById(R.id.noteTitle);
            note_desc = itemView.findViewById(R.id.noteTitle);
            note_date = itemView.findViewById(R.id.noteDate);
        }

        public void setData(mainnote note) {
            note_name.setText(note.getTitle());
            note_date.setText(note.getdate()+"");
            note_desc.setText(note.getdetails());
        }
    }
}


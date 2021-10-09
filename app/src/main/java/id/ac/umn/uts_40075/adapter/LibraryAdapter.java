package id.ac.umn.uts_40075.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.umn.uts_40075.R;
import id.ac.umn.uts_40075.databinding.LibraryItemBinding;
import id.ac.umn.uts_40075.model.DetailLibraryActivity;
import id.ac.umn.uts_40075.model.Library;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.LibraryViewHolder> {
    private final Context context;
    private final List<Library> libraryList;
    private onItemClickListener listener;

    public LibraryAdapter(Context context, List<Library> libraryList, onItemClickListener listener) {
        this.context = context;
        this.libraryList = libraryList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LibraryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LibraryItemBinding binding = LibraryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LibraryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Library library = libraryList.get(position);
        if (!library.isPlay()) {
            holder.binding.ivSound.setImageResource(R.drawable.ic_baseline_volume_up_24);
        } else {
            holder.binding.ivSound.setImageResource(R.drawable.ic_baseline_volume_off_24);
        }
        holder.binding.tvTitleLibrary.setText(library.getTitle());
        holder.binding.textView2.setText(library.getSubTitle());
        holder.binding.ivSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPlaySound(library, position);
            }
        });
        holder.binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDeleteItem(position);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailLibraryActivity.class);
                intent.putExtra("detail_library",library );
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return libraryList.size();
    }

    public static class LibraryViewHolder extends RecyclerView.ViewHolder {
        LibraryItemBinding binding;

        public LibraryViewHolder(LibraryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }

    public interface onItemClickListener {
        void onPlaySound (Library library, int index);
        void onDeleteItem(int index);
    }
}

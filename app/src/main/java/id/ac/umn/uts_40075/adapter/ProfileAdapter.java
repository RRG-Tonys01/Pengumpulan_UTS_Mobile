package id.ac.umn.uts_40075.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.umn.uts_40075.databinding.ReferensiItemBinding;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private final Context context;
    private final List<String>  referensiList;

    public ProfileAdapter(Context context, List<String> referensiList) {
        this.context = context;
        this.referensiList = referensiList;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ReferensiItemBinding binding = ReferensiItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProfileViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        holder.binding.tvReverensi.setText(referensiList.get(position));
    }

    @Override
    public int getItemCount() {
        return referensiList.size();
    }

    public static class ProfileViewHolder extends RecyclerView.ViewHolder {
        ReferensiItemBinding binding;

        public ProfileViewHolder(ReferensiItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
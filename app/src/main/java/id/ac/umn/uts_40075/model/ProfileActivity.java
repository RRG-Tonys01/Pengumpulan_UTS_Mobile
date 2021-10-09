package id.ac.umn.uts_40075.model;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import id.ac.umn.uts_40075.adapter.ProfileAdapter;
import id.ac.umn.uts_40075.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    private ProfileAdapter adapter;
    private ActivityProfileBinding binding;
    private List<String> referensiList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initToolbar();
        initAdapter(binding);
        loadData();
    }

    private void initToolbar() {
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initAdapter(ActivityProfileBinding binding) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvReferensi.setLayoutManager(layoutManager);
        adapter = new ProfileAdapter(this, referensiList);
        binding.rvReferensi.setAdapter(adapter);
    }

    private void loadData() {
        referensiList.add("https://www.zedge.net/ringtone");
        referensiList.add("https://freesfx.co.uk/");
        referensiList.add("https://github.com/hdodenhof/CircleImageView");
        referensiList.add("https://developer.android.com/guide/topics/media/mediaplayer");
        referensiList.add("https://stackoverflow.com/");
        referensiList.add("https://www.javatpoint.com/");
        referensiList.add("https://developer.android.com");
        referensiList.add("https://github.com/");
        adapter.notifyDataSetChanged();
    }
}
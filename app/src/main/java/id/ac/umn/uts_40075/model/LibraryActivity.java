package id.ac.umn.uts_40075.model;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import id.ac.umn.uts_40075.R;
import id.ac.umn.uts_40075.adapter.LibraryAdapter;
import id.ac.umn.uts_40075.databinding.ActivityLibraryBinding;

public class LibraryActivity extends AppCompatActivity {
    private final List<Library> libraryList = new ArrayList<>();
    private ActivityLibraryBinding binding;
    private LibraryAdapter adapter;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLibraryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent() != null) {
            String name = getIntent().getStringExtra("login_name");
            initToolbar(name);
            Toast.makeText(this, "Selamat datang, " + name + " LAB", Toast.LENGTH_SHORT).show();

        } else {
            Log.d("bundle ", "kosong");
        }

        initAdapter(binding);
        loadData();
    }

    private void initToolbar(String name) {
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            case R.id.action_exit:
                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer!=null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer!=null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    private void initAdapter(ActivityLibraryBinding binding) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvLibrary.setLayoutManager(layoutManager);
        adapter = new LibraryAdapter(this, libraryList, new LibraryAdapter.onItemClickListener() {
            @Override
            public void onPlaySound(Library library, int index) {
                mediaPlayer = library.getMediaPlayer();
                if(library.isPlay()) {
                    library.setPlay(true);
                    libraryList.set(index, library);
                    mediaPlayer.start();
                } else {
                    library.setPlay(false);
                    libraryList.set(index, library);
                    mediaPlayer.pause();
                }
                adapter.notifyDataSetChanged();
            }



            @Override
            public void onDeleteItem(int index) {
                libraryList.remove(index);
                adapter.notifyDataSetChanged();
            }
        });
        binding.rvLibrary.setAdapter(adapter);
    }

    private void loadData() {
        libraryList.clear();
        Library library = new Library();
        library.setId("1");
        library.setTitle("Adventures In Paradise");
        library.setSubTitle("Lagu");
        library.setSound(R.raw.adventuresinparadise);
        library.setMediaPlayer(MediaPlayer.create(this, R.raw.adventuresinparadise));
        library.setPlay(false);
        libraryList.add(library);

        library = new Library();
        library.setId("2");
        library.setTitle("Black Magic");
        library.setSubTitle("SFX");
        library.setSound(R.raw.blackmagic);
        library.setMediaPlayer(MediaPlayer.create(this, R.raw.blackmagic));
        library.setPlay(false);
        libraryList.add(library);

        library = new Library();
        library.setId("3");
        library.setTitle("Blue Jeans");
        library.setSubTitle("SFX");
        library.setSound(R.raw.bluejeans);
        library.setMediaPlayer(MediaPlayer.create(this, R.raw.bluejeans));
        library.setPlay(false);
        libraryList.add(library);

        library = new Library();
        library.setId("4");
        library.setTitle("Club Dance");
        library.setSubTitle("SFX");
        library.setSound(R.raw.clubdance);
        library.setMediaPlayer(MediaPlayer.create(this, R.raw.clubdance));
        library.setPlay(false);
        libraryList.add(library);

        library = new Library();
        library.setId("5");
        library.setTitle("Guitar");
        library.setSubTitle("SFX");
        library.setSound(R.raw.guitar);
        library.setMediaPlayer(MediaPlayer.create(this, R.raw.guitar));
        library.setPlay(false);
        libraryList.add(library);


        adapter.notifyDataSetChanged();
    }
}
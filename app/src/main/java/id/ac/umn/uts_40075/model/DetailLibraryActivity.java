package id.ac.umn.uts_40075.model;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.Objects;

import id.ac.umn.uts_40075.databinding.ActivityDetailLibraryBinding;

public class DetailLibraryActivity extends AppCompatActivity {
    private ActivityDetailLibraryBinding binding;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailLibraryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(getIntent() != null) {
            Library library = getIntent().getParcelableExtra("detail_library");
            initToolbar(library.getTitle());

            binding.tvTitle.setText(library.getTitle());
            binding.tvSubtitle.setText(library.getSubTitle());
            mediaPlayer = MediaPlayer.create(DetailLibraryActivity.this, library.getSound());

            binding.btnSfx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!mediaPlayer.isPlaying()) {
                        binding.btnSfx.setText("Stop Sfx");
                        mediaPlayer.start();
                    } else {
                        binding.btnSfx.setText("Play Sfx");
                        mediaPlayer.pause();
                    }
                }
            });
        } else {
            Log.d("Data Intent", "null");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!= null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

    private void initToolbar(String title) {
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
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
}
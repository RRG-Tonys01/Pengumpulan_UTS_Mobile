package id.ac.umn.uts_40075.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import id.ac.umn.uts_40075.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(view -> {
            if(binding.etLogin.getText().toString().length() == 0) {
                binding.etLogin.setError("Harap diisi");
            } else {
                Intent intent = new Intent(LoginActivity.this, LibraryActivity.class);
                intent.putExtra("login_name", binding.etLogin.getText().toString());
                startActivity(intent);

            }
        });
    }
}
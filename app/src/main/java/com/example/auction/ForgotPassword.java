package com.example.auction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        EdgeToEdge.enable(this);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        EditText ForgotPasswordemail = findViewById(R.id.editTextText1);
        Button ForgotPasswordbutton = findViewById(R.id.button1);
        TextView ForgotPasswordlogin = findViewById(R.id.textView3);

        ForgotPasswordbutton.setOnClickListener(view -> {
            String email = ForgotPasswordemail.getText().toString();

            if (email.isEmpty()) {
                ForgotPasswordemail.setError("Email cannot be empty");
            } else {
                sendPasswordResetEmail(email);
            }
        });

        ForgotPasswordlogin.setOnClickListener(view -> {
            Intent intent = new Intent(ForgotPassword.this, MainActivity.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
    }

    private void sendPasswordResetEmail(String email) {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(ForgotPassword.this, "Reset link sent to your email.", Toast.LENGTH_SHORT).show();
                        Toast.makeText(ForgotPassword.this, "Please check your email to reset your password.", Toast.LENGTH_LONG).show();

                        // Redirect to login page
                        Intent intent = new Intent(ForgotPassword.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Optional: Finish the forgot password activity
                    } else {
                        // If sending email fails, display a message to the user.
                        Log.w("ForgotPasswordwordActivity", "Failed to send reset email.", task.getException());
                        Toast.makeText(ForgotPassword.this, "Failed to send reset email. Please check your email and try again.", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
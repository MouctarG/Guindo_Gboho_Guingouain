package com.example.projetandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetandroid.model.User;
import com.example.projetandroid.model.utils.DatabaseHandler;

public class LoginActivity extends AppCompatActivity {

    private EditText edit_log_psd;
    private EditText edit_log_mpd;
    DatabaseHandler databaseHandler;
    public static String LOGIN_USER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edit_log_psd = findViewById(R.id.edit_log_psd);
        edit_log_mpd = findViewById(R.id.edit_log_mdp);
        databaseHandler = new DatabaseHandler(this, DatabaseHandler.DATABASE_NAME, null, DatabaseHandler.DATABASE_VERSION);

    }

    public void seConnecter(View view) {
        String str_psd = edit_log_psd.getText().toString();
        String str_mdp = edit_log_mpd.getText().toString();
        int duration = Toast.LENGTH_SHORT;
        User user;

        Toast toast = Toast.makeText(this, getText(R.string.msgChamp_req_ins), duration);

        if (str_psd.trim().length() == 0 || str_mdp.trim().length() == 0)
            toast.show();
        else {
            user = databaseHandler.checkUser(str_psd, str_mdp);
            if (user == null) {
                toast.setText(R.string.log_mdp_Incorrect);
                toast.show();
            } else {
                LOGIN_USER = str_psd;
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                databaseHandler.close();
                finish();
            }

        }
    }

    public void motDPasseOublier(View view) {
        Intent intent = new Intent(LoginActivity.this, PasswordOublierActivity.class);
        startActivity(intent);
    }

}

package com.example.projetandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetandroid.model.User;
import com.example.projetandroid.utils.DatabaseHandler;

public class PasswordOublierActivity extends AppCompatActivity {
    EditText edit_psd;
    EditText edit_mdp;
    EditText edit_mdp_confirm;
    EditText edit_date;
    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_oublier);
        edit_psd = findViewById(R.id.edit_pass_psd);
        edit_mdp = findViewById(R.id.edit_passw_mdp);
        edit_mdp_confirm = findViewById(R.id.edit_pass_mdp_confirm);
        edit_date = findViewById(R.id.edit_pass_date);
    }

    public void updatePassword(View view) {
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this, getText(R.string.msgChamp_req_ins), duration);

        String str_psd = edit_psd.getText().toString();
        String str_mdp_corfirm = edit_mdp_confirm.getText().toString();
        String str_mdp = edit_mdp.getText().toString();
        String str_date = edit_date.getText().toString();

        if (str_psd.trim().length() == 0 || str_mdp.trim().length() == 0 || str_mdp_corfirm.trim().length() == 0)
            toast.show();
        else if (!str_mdp.equals(str_mdp_corfirm)) {
            toast.setText(getText(R.string.txt_mdp_diff_ins));
            toast.show();

        } else {
            User user = databaseHandler.checkUser(str_psd);
            if (user != null) {

                databaseHandler.updatePassword(str_psd, str_mdp);
                toast.setText(str_psd);
                toast.setText(getText(R.string.msg_new_password));
                toast.show();
                Intent intent = new Intent(PasswordOublierActivity.this, MainActivity.class);
                startActivity(intent);
                databaseHandler.close();
                finish();
            } else {
                toast.setText(R.string.cmpt_introuvable);
            }
        }

    }
}
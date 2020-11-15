package com.example.projetandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetandroid.model.User;
import com.example.projetandroid.model.utils.DatabaseHandler;


public class InscriptionActivity extends AppCompatActivity {
    EditText edit_ins_psd;
    EditText edit_ins_mdp;
    EditText edit_ins_mdp_confirm;
    EditText edit_ins_date;
    DatabaseHandler databaseHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        edit_ins_psd = findViewById(R.id.edit_ins_psd);
        edit_ins_mdp = findViewById(R.id.edit_ins_mdp);
        edit_ins_mdp_confirm = (EditText) findViewById(R.id.edit_ins_mdp_confirm);
        edit_ins_date = findViewById(R.id.edit_ins_date);
        databaseHandler = new DatabaseHandler(this, DatabaseHandler.DATABASE_NAME, null, DatabaseHandler.DATABASE_VERSION);

        // Toast toast = Toast.makeText(this, databaseHandler.showDatabase(), duration);
        // toast.show();
    }

    public void creerCompte(View view) {
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this, getText(R.string.msgChamp_req_ins), duration);

        String str_psd = edit_ins_psd.getText().toString();
        String str_mdp_corfirm = edit_ins_mdp_confirm.getText().toString();
        String str_mdp = edit_ins_mdp.getText().toString();
        String str_date = edit_ins_date.getText().toString();

        if (str_psd.trim().length() == 0 || str_mdp.trim().length() == 0 || str_mdp_corfirm.trim().length() == 0)
            toast.show();
        else if (!str_mdp.equals(str_mdp_corfirm)) {
            toast.setText(getText(R.string.txt_mdp_diff_ins));
            toast.show();

        } else {
            User user = new User(str_psd, str_mdp);
            databaseHandler.addUser(user);

            toast.setText(str_psd);
            toast.setText(getText(R.string.cmpt_creer));
            toast.show();
            Intent intent = new Intent(InscriptionActivity.this, MainActivity.class);
            startActivity(intent);
            databaseHandler.close();
            finish();
        }
    }
}
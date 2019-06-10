package com.example.jpr_dealear.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jpr_dealear.Database.DatabaseHalper;
import com.example.jpr_dealear.R;

public class RegistrationActivity extends AppCompatActivity {

    private EditText Enteruserid, EnterPassword, ConfrimPassword;
    private Button submit;
    private DatabaseHalper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = new DatabaseHalper(this);
        Enteruserid = findViewById(R.id.reg_id);
        EnterPassword = findViewById(R.id.reg_pass1);
        ConfrimPassword = findViewById(R.id.reg_pass2);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_name = Enteruserid.getText().toString();
                String pass = EnterPassword.getText().toString();
                String pass2 = ConfrimPassword.getText().toString();

                if (user_name.equals("") || pass.equals("") || pass2.equals("")) {
                    Toast.makeText(RegistrationActivity.this, "Fildes are Empaty", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals(pass2)) {
                        boolean chkuserid = db.chkuserid(user_name);
                        if (!chkuserid) {
                            Boolean insertuser = db.insertuser(user_name, pass);
                            if (insertuser) {
                                Toast.makeText(RegistrationActivity.this, "Registration Successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(RegistrationActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(RegistrationActivity.this, "user AllReady Exsist", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

            }
        });
    }
}

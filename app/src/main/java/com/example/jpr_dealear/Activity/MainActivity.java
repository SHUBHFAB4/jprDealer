package com.example.jpr_dealear.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jpr_dealear.Constants;
import com.example.jpr_dealear.Database.DatabaseHalper;
import com.example.jpr_dealear.R;
import com.example.jpr_dealear.User;

public class MainActivity extends AppCompatActivity {

    DatabaseHalper db;
    ImageView image1;
    EditText useridEdt, passwordEdt;
    Button logginBtn, registerBtn;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHalper(this);
        image1 = (ImageView) findViewById(R.id.image1);
        useridEdt = findViewById(R.id.userid);
        passwordEdt = findViewById(R.id.pass);
        logginBtn = findViewById(R.id.login);
        registerBtn = findViewById(R.id.reg);




        logginBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                String user_name = useridEdt.getText().toString();
                String password = passwordEdt.getText().toString();
                if (TextUtils.isEmpty(user_name)) {
                    Toast.makeText(MainActivity.this, "Enter userId", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "Enter password", Toast.LENGTH_LONG).show();
                    return;
                }


                User user = db.loginuser(user_name,password);
                if (user!=null) {
                 Intent intent = new Intent(MainActivity.this, Dealears_listActivity.class);
                 intent.putExtra(Constants.USER_ID,user.getUser_id());
                 startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "user not valid/ incorrect detail", Toast.LENGTH_LONG).show();

                }


            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * Creation 1 login credential to login into the system
     * it will check if userId already exist then it will not save again
     * if not exist then it will save the userid and password
     */

}

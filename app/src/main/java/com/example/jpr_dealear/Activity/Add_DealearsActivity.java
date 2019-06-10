package com.example.jpr_dealear.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jpr_dealear.Constants;
import com.example.jpr_dealear.Database.DatabaseHalper;
import com.example.jpr_dealear.DealearsAdapter;
import com.example.jpr_dealear.DealerModel;
import com.example.jpr_dealear.R;

import java.util.List;

public class Add_DealearsActivity extends AppCompatActivity {

    DatabaseHalper db;
    Button btnsub;
    EditText dealearsname, dealearsephone;
    private int userid;
    private String phone;
    private int d_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__dealears);


        db = new DatabaseHalper(this);
        btnsub = findViewById(R.id.submit);
        dealearsname = findViewById(R.id.dname);
        dealearsephone = findViewById(R.id.dphone);


        Intent intent = getIntent();
        if (intent.hasExtra("name")) {
            String name = intent.getStringExtra("name");
            dealearsname.setText(name);
            d_id = intent.getIntExtra("d_id",0);

            phone = intent.getStringExtra("phone");
            dealearsephone.setText(phone);

            dealearsephone.setEnabled(false);
            btnsub.setText("update");


        } else {
            if (intent.hasExtra(Constants.USER_ID)) {
                userid = intent.getIntExtra(Constants.USER_ID, 0);
            }


        }



        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dealearsnames = dealearsname.getText().toString();
                String phonenumbers = dealearsephone.getText().toString();
                if (TextUtils.isEmpty(dealearsnames)) {
                    Toast.makeText(Add_DealearsActivity.this, "Enter dealears name", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(phonenumbers)) {
                    Toast.makeText(Add_DealearsActivity.this, "Enter phonenumber", Toast.LENGTH_LONG).show();
                    return;
                }


                // if you clicked from recycler item the we have phone and if u have phone that means going to update
                // sir aa k aap btaana samkjh me ek dum nhi aa rha kl tk sb shi tha ptaaa nhi hr roj kya ho jaata  ok sending you something new to look into OK

                if (userid > 0) {
                    Boolean isValid = db.chkdealears(dealearsnames, phonenumbers);

                    if (!isValid) {
                        DealerModel dealerModel = new DealerModel();
                        dealerModel.setDealearsname(dealearsnames);
                        dealerModel.setPhonenumber(phonenumbers);
                        dealerModel.setUserid(userid);

                        // if you clicked from menu the we have userid
                        boolean isSaved = (boolean) db.insertdealears(dealerModel);

                        if (isSaved) {
                            Toast.makeText(Add_DealearsActivity.this, "Data save successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(Add_DealearsActivity.this, "Invaild data", Toast.LENGTH_SHORT).show();

                        }
                    }
                } else {
                    DealerModel dealerModel = new DealerModel();
                    dealerModel.setDealearsname(dealearsnames);
                    dealerModel.setPhonenumber(phone);
                    dealerModel.setD_id(d_id);
                    int updated = db.update_dealears(dealerModel);

                    if (updated > 0) {
                        Toast.makeText(Add_DealearsActivity.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {

                        Toast.makeText(Add_DealearsActivity.this, "Unable to update", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });

//

    }


}

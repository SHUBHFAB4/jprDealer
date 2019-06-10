package com.example.jpr_dealear.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jpr_dealear.Constants;
import com.example.jpr_dealear.Database.DatabaseHalper;
import com.example.jpr_dealear.DealerModel;
import com.example.jpr_dealear.R;
import com.example.jpr_dealear.DealearsAdapter;

import java.util.List;

import static com.example.jpr_dealear.Constants.USER_ID;

public class Dealears_listActivity extends AppCompatActivity implements DealearsAdapter.OnLongClickAction{

    ImageView imageView;
    TextView dealearsname,dealearsphone;
    CardView cardView;
    private int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

         dealearsname=findViewById(R.id.name1);
        dealearsphone=findViewById(R.id.number1);
        imageView = findViewById(R.id.callicon);
        cardView =findViewById(R.id.cardview);


      Intent i=getIntent();
      userid=i.getIntExtra(Constants.USER_ID,0);

    }


    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView dealearslist=findViewById(R.id.dealearslist);

        dealearslist.setLayoutManager(new LinearLayoutManager(this));

        DatabaseHalper databaseHalper = new DatabaseHalper(this);

        List<DealerModel> dealers = databaseHalper.getAllDealears(userid);
        DealearsAdapter adapter = new DealearsAdapter(this,dealers);

        adapter.setOnLongClick(this);

        dealearslist.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add_dealears:
                Intent i = new Intent(Dealears_listActivity.this, Add_DealearsActivity.class);
                i.putExtra(USER_ID,userid);
                startActivity(i);
                return true;
            case R.id.mark_attendence:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onLongClick(int position) {
        Toast.makeText(this,"Hi",Toast.LENGTH_LONG).show();
    }
}

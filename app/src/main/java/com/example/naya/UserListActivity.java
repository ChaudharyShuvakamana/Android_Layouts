package com.example.naya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.naya.model.User;

import java.util.List;

public class UserListActivity extends AppCompatActivity {
    ListView listView;
    String[] test={"a","b","c"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        listView=findViewById(R.id.lvUsers);

        Intent intent =getIntent();
        List<User> userList=(List<User>)intent.getSerializableExtra("allusers");
        String[] userName=new String[userList.size()];
        int i=0;
        for(User user:userList){
            userName[i]=user.getName();
            i++;
        }

        ArrayAdapter adapter=new ArrayAdapter(this,R.layout.spinners_values,test);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(UserListActivity.this,
                        ""+i,Toast.LENGTH_SHORT).show();

            }
        });

    }
}

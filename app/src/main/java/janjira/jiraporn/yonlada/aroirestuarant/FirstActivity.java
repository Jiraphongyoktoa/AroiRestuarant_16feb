package janjira.jiraporn.yonlada.aroirestuarant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import janjira.jiraporn.yonlada.aroirestuarant.fragment.FirstFrgment;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first2);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentfragmentment, new FirstFrgment()).commit();
        }
    }  // Main Method
}   //Main Class

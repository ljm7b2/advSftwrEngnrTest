package com.example.ljm7b.loginandweatherapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.ljm7b.loginandweatherapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void attemptLogin(View v){

        EditText loginText = (EditText)findViewById(R.id.editText);
        EditText passwdText = (EditText)findViewById(R.id.editText2);

        if(loginText.getText().toString().equals("admin")
                && passwdText.getText().toString().equals("admin")){

            Context context = getApplicationContext();
            CharSequence text = "Login Success!";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Intent intent = new Intent(this, Weather_Current_Location.class);
            String message = loginText.getText().toString();
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);

        }
        else{
            TextView failedLogin = (TextView)findViewById(R.id.textView3);
            failedLogin.setVisibility(v.VISIBLE);
        }


    }
}

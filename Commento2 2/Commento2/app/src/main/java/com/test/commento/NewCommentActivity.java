package com.test.commento;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import commentsender.GMailSender;


public class NewCommentActivity extends AppCompatActivity {
    EditText commentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_comment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        commentText = (EditText)findViewById(R.id.input_comment);

        commentText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        Button button = (Button) findViewById(R.id.btnSendComment);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String commentBody = createRequestToPassComment(commentText.getText().toString());

                commentText.setText("");

                hideKeyboard(v);

                Toast.makeText(NewCommentActivity.this,"Comment sent to the server",Toast.LENGTH_LONG).show();

                SendCoomentTask job = new SendCoomentTask();
                job.execute(commentBody);

            }
        });
    }


    private String createRequestToPassComment(String userComment) {


        StringBuilder body = new StringBuilder();
        body
                .append("Comment : \n" + userComment);


        return body.toString();
    }

    private class SendCoomentTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String[] params) {
            // do above Server call here
            GMailSender gMailSender = new GMailSender("somnathdev477@gmail.com", "Anmol1234");

            try {

                gMailSender.sendMail("New Comment Received", params[0], "somnathdev477@gmail.com", "somnath.n005@gmail.com");



            } catch (Exception e) {
                Log.d("Error Sending es", e.toString());
            }


            return "OK";
        }

        @Override
        protected void onPostExecute(String message) {

        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}

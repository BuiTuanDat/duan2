package com.ph05743.lenovo.androidnetworking;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Bai4Activity extends AppCompatActivity implements View.OnClickListener {
    private TextView text4;
    private EditText edt4;
    private Button btn4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai4);
        text4 = (TextView) findViewById(R.id.text4);
        edt4 = (EditText) findViewById(R.id.edt4);
        btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn4:
                AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner(this, text4, edt4);
                String sleepTime = edt4.getText().toString();
                asyncTaskRunner.execute(sleepTime);
                break;
        }

    }

    public class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog dialog;
        TextView text4;
        EditText edt4;
        Context context;

        public AsyncTaskRunner(Context context, TextView text4, EditText edt4) {
            this.text4 = text4;
            this.context = context;
            this.edt4 = edt4;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            if (dialog.isShowing()) {
//                dialog.dismiss();
//            }
//            text4.setText(s);
            dialog = ProgressDialog.show(context, "ProgressDialog", " Wait for " +
                    edt4.getText().toString() + "seconds");
        }

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping...");

            try {
                int edt4 = Integer.parseInt(params[0]) * 1000;
                Thread.sleep(edt4);
                resp = "Sleep for" + params[0] + "seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;

        }

    }
}

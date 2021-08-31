package com.example.thread;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    private ImageView mImgBitmap;
    private Button mBtnDownload;
    private EditText mEditLink;
    private EditText mEdtFileName;
    private ProgressDialog mPdDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListeners();
    }

    private void initView() {
        mImgBitmap = findViewById(R.id.imgBitmap);
        mBtnDownload = findViewById(R.id.btnDownload);
        mEditLink = findViewById(R.id.edtLink);
        mEdtFileName = findViewById(R.id.editFileName);
        setUpDialog();
    }

    private void initListeners() {

        mBtnDownload.setOnClickListener(v -> {
            String link = mEditLink.getText().toString();
            String fileName = mEdtFileName.getText().toString();
            if (link.length() != 0 && fileName.length() != 0) {
                new LoadImage().execute(link, fileName);
            } else {
                Toast.makeText(this, R.string.incorrect, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpDialog() {
        mPdDownload = new ProgressDialog(this);
        mPdDownload.setTitle(R.string.download);
        mPdDownload.setIndeterminate(false);
        mPdDownload.setMax(100);
        mPdDownload.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mPdDownload.setCancelable(true);
    }

    private class LoadImage extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mPdDownload.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            int count;
            String fileName = null;
            try {
                //connect
                URL url = new URL(strings[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                int length = connection.getContentLength();
                //create file
                InputStream input = new BufferedInputStream(url.openStream(), 8192);
                String storageDir = "sdcard/Music/";
                fileName = storageDir + strings[1] + ".jpeg";
                File file = new File(fileName);
                OutputStream output = new FileOutputStream(file);
                byte[] data = new byte[1024];
                long total = 0;
                //write to file
                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress((int) ((total * 100) / length));
                    output.write(data, 0, count);
                }
                //close stream
                output.close();
                input.close();
            } catch (Exception ignored) {
            }
            return fileName;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mPdDownload.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String string) {
            super.onPostExecute(string);
            mPdDownload.dismiss();
            Toast.makeText(MainActivity.this, R.string.download_complete, Toast.LENGTH_SHORT).show();
            mImgBitmap.setImageDrawable(Drawable.createFromPath(string));
        }
    }
}

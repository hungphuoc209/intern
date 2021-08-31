package com.example.intend2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button mBtnNumber1;
    private Button mBtnNumber2;
    private Button mBtnNumber3;
    private Button mBtnNumber4;
    private Button mBtnNumber5;
    private Button mBtnNumber6;
    private Button mBtnNumber7;
    private Button mBtnNumber8;
    private Button mBtnNumber9;
    private Button mBtnNumber0;
    private Button mBtnHashTag;
    private Button mBtnAsterisk;
    private ImageButton mImgBtnBackspace;
    private ImageButton mImgBtnCall;
    private EditText mEdtNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();

    }

    private void initViews(){
        mBtnNumber1 = findViewById(R.id.btnNumber1);
        mBtnNumber2 = findViewById(R.id.btnNumber2);
        mBtnNumber3 = findViewById(R.id.btnNumber3);
        mBtnNumber4 = findViewById(R.id.btnNumber4);
        mBtnNumber5 = findViewById(R.id.btnNumber5);
        mBtnNumber6 = findViewById(R.id.btnNumber6);
        mBtnNumber7 = findViewById(R.id.btnNumber7);
        mBtnNumber8 = findViewById(R.id.btnNumber8);
        mBtnNumber9 = findViewById(R.id.btnNumber9);
        mBtnNumber0 = findViewById(R.id.btnNumber0);
        mBtnAsterisk = findViewById(R.id.btnAsterisk);
        mBtnHashTag = findViewById(R.id.btnHashTag);
        mImgBtnBackspace = findViewById(R.id.btnBackspace);
        mImgBtnCall = findViewById(R.id.btnCall);
        mEdtNumber = findViewById(R.id.edtNumber);
        mEdtNumber.setShowSoftInputOnFocus(false);
    }
    private void initListeners(){
        mBtnNumber1.setOnClickListener(view -> addNumberAt("1",mEdtNumber,mEdtNumber.getSelectionStart()));
        mBtnNumber2.setOnClickListener(view -> addNumberAt("2",mEdtNumber,mEdtNumber.getSelectionStart()));
        mBtnNumber3.setOnClickListener(view -> addNumberAt("3",mEdtNumber,mEdtNumber.getSelectionStart()));
        mBtnNumber4.setOnClickListener(view -> addNumberAt("4",mEdtNumber,mEdtNumber.getSelectionStart()));
        mBtnNumber5.setOnClickListener(view -> addNumberAt("5",mEdtNumber,mEdtNumber.getSelectionStart()));
        mBtnNumber6.setOnClickListener(view -> addNumberAt("6",mEdtNumber,mEdtNumber.getSelectionStart()));
        mBtnNumber7.setOnClickListener(view -> addNumberAt("7",mEdtNumber,mEdtNumber.getSelectionStart()));
        mBtnNumber8.setOnClickListener(view -> addNumberAt("8",mEdtNumber,mEdtNumber.getSelectionStart()));
        mBtnNumber9.setOnClickListener(view -> addNumberAt("9",mEdtNumber,mEdtNumber.getSelectionStart()));
        mBtnNumber0.setOnClickListener(view -> addNumberAt("0",mEdtNumber,mEdtNumber.getSelectionStart()));
        mBtnHashTag.setOnClickListener(view -> addNumberAt("#",mEdtNumber,mEdtNumber.getSelectionStart()));
        mBtnAsterisk.setOnClickListener(view -> addNumberAt("*",mEdtNumber,mEdtNumber.getSelectionStart()));

        mImgBtnBackspace.setOnClickListener(view ->{
            int mStart = mEdtNumber.getSelectionStart();
            int mEnd = mEdtNumber.getSelectionEnd();
            if(mStart == mEnd) deleteNumberAt(mEdtNumber, mEdtNumber.getSelectionStart());
            else deleteNumberFromTo(mEdtNumber, mStart, mEnd);
        });

        mImgBtnBackspace.setOnLongClickListener(v -> {
            deleteAllAt(mEdtNumber, mEdtNumber.getSelectionStart());
            return false;
        });
        mEdtNumber.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                InputMethodManager mImm = (InputMethodManager) getSystemService(MainActivity.INPUT_METHOD_SERVICE);
                mImm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                mEdtNumber.setSelection(mEdtNumber.length());
            }
        });
        mImgBtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhoneNumber(mEdtNumber.getText().toString());
            }
        });
    }
    private void addNumberAt(String mNumber,EditText mEdt,int mPosition){
        String mNumberEdt = mEdt.getText().toString();
        String mNumberBefore = mNumberEdt.substring(0, mPosition);
        String mNumberAfter = mNumberEdt.substring(mPosition);
        mNumberEdt = mNumberBefore + mNumber + mNumberAfter;
        mEdt.setText(mNumberEdt);
        mEdt.setSelection(mPosition+1);
    }
    private void deleteNumberAt(EditText mEdt,int mPosition){
        if(mEdt.getText().toString().length() != 0 && mPosition !=0) {
            String mNumberEdt = mEdt.getText().toString();
            String mNumberBefore = mNumberEdt.substring(0, mPosition - 1);
            String mNumberAfter = mNumberEdt.substring(mPosition);
            mNumberEdt = mNumberBefore + mNumberAfter;
            mEdt.setText(mNumberEdt);
            mEdt.setSelection(mPosition - 1);

        }
    }
    private void callPhoneNumber(String mNumber){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+ Uri.encode(mNumber)));
        Toast.makeText(this, "Calling "+mNumber, Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
    private void deleteAllAt(EditText mEdt,int mPosition){
        if(mEdtNumber.getText().toString().length()!=0 && mPosition !=0){
            String mNumber = mEdt.getText().toString();
            mNumber = mNumber.substring(mPosition);
            mEdt.setText(mNumber);
            mEdt.setSelection(0);
        }
    }
    private void deleteNumberFromTo(EditText mEdt,int start,int end){

        String mNumber = mEdt.getText().toString();
        start = mEdt.getSelectionStart();
        end = mEdt.getSelectionEnd();
        String mNumberEdtBefore = mNumber.substring(0, start);
        String mNumberEdtAfter = mNumber.substring(end, mEdt.length());
        mNumber = mNumberEdtBefore + mNumberEdtAfter;
        mEdt.setText(mNumber);
        mEdt.setSelection(start);
    }
}
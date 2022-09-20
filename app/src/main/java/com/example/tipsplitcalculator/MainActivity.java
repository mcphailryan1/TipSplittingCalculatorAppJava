package com.example.tipsplitcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    private EditText totalBillValue;
    private EditText numberPeopleValue;
    private EditText tipAmount;
    private EditText totalWithTip;
    private TextView answerText;
    private RadioButton twelvePercent;
    private RadioButton fifteenPercent;
    private RadioButton eighteenPercent;
    private RadioButton twentyPercent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //variables for the views
        totalBillValue = findViewById(R.id.billTotalID);
        numberPeopleValue = findViewById(R.id.numPeopleID);
        answerText = findViewById(R.id.endTotalID);
        tipAmount = findViewById(R.id.tipAmountID);
        totalWithTip = findViewById(R.id.totalIncludeID);
        //variables for the radio buttons
        twelvePercent = findViewById(R.id.twelveRadio);
        fifteenPercent = findViewById(R.id.fifteenRadio);
        eighteenPercent = findViewById(R.id.eighteenRadio);
        twentyPercent = findViewById(R.id.twentyRadio);

    }

    public void setTipPercentage(View v){

        String totalBillString = totalBillValue.getText().toString();
        //following is error handling, keeps from crashing on empty inputs - from lecture
        if (totalBillString.isEmpty())
            return;
        double billStringValue = Double.parseDouble(totalBillString);

        if (v.getId() == R.id.twelveRadio) {
            //the following is for calculating the tip
            double calculatedTipAmount = billStringValue * (0.12);
            tipAmount.setText("$" + String.format("%5.2f%n", calculatedTipAmount));
            //the following is for calculating the total with the tip
            double calculatedTotalWithTip = billStringValue + calculatedTipAmount;
            totalWithTip.setText("$" + String.format("%5.2f%n", calculatedTotalWithTip));

        } else if (v.getId() == R.id.fifteenRadio) {
            //the following is for calculating the tip
            double calculatedTipAmount = (double) billStringValue * (0.15);
            tipAmount.setText("$" + String.format("%5.2f%n", calculatedTipAmount));
            //the following is for calculating the total with the tip
            double calculatedTotalWithTip = billStringValue + calculatedTipAmount;
            totalWithTip.setText("$" + String.format("%5.2f%n", calculatedTotalWithTip));

        } else if (v.getId() == R.id.eighteenRadio) {
            //the following is for calculating the tip
            double calculatedTipAmount = (double) billStringValue * (0.18);
            tipAmount.setText("$" + String.format("%5.2f%n", calculatedTipAmount));
            //the following is for calculating the total with the tip
            double calculatedTotalWithTip = billStringValue + calculatedTipAmount;
            totalWithTip.setText("$" + String.format("%5.2f%n", calculatedTotalWithTip));

        } else if (v.getId() == R.id.twentyRadio) {
            //the following is for calculating the tip
            double calculatedTipAmount = (double) billStringValue * (0.20);
            tipAmount.setText("$" + String.format("%5.2f%n", calculatedTipAmount));
            //the following is for calculating the total with the tip
            double calculatedTotalWithTip = billStringValue + calculatedTipAmount;
            totalWithTip.setText("$" + String.format("%5.2f%n", calculatedTotalWithTip));

        } else {
            Log.d(TAG, "setTipPercentage: Unkown Tip Percentage" + ((RadioButton) v).getText());
        }

    }

    public void calculateTip(View v) {

        Log.d(TAG, "calculateTip");
        String totalPeopleString = numberPeopleValue.getText().toString();
        //following is error handling, keeps from crashing on empty inputs - from lecture
        if (totalPeopleString.isEmpty())
            return;
        int peopleStringValue = Integer.parseInt(totalPeopleString);

        //taking the text with $, removing the $ so that the math can happen
        String string1 = totalWithTip.getText().toString();
        String string2 = string1.replace("$","");

        //doing the math for the final answer
        double tempAnswer = Double.parseDouble(string2) / peopleStringValue;
        answerText.setText("$" + String.format("%5.2f", tempAnswer));
    }

    public void clearViews(View v) {

        Log.d(TAG, "clearing fields");
        //manually setting all text fields to blank
        totalBillValue.setText("");
        //setting the radio buttons to not checked
        twelvePercent.setChecked(false);
        fifteenPercent.setChecked(false);
        eighteenPercent.setChecked(false);
        twentyPercent.setChecked(false);
        //back to manually clearing the fields
        tipAmount.setText("");
        totalWithTip.setText("");
        numberPeopleValue.setText("");
        answerText.setText("Total $$$");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //saving the value in the answer field for when the screen rotates
        outState.putString("ANSWER", answerText.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        //putting the SAVED value from the answer field back into the text field when the screen rotates
        super.onRestoreInstanceState(savedInstanceState);
        answerText.setText(savedInstanceState.getString("ANSWER"));
    }

}
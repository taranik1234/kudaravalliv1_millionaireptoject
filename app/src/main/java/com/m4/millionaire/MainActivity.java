package com.m4.millionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Class members
    Handler handler;
    RadioGroup options;
    Button QuitButton, SubmitButton;
    TextView QuestionView, CashView;
    RadioButton radio1, radio2, radio3, radio4;

    static int CurrentQues = 0;
    final int Question15 = 15, TimeDelay = 3000;
    String[] cash = {"100", "200", "400", "800", "1,600", "3,200", "6,400",
                    "12,500", "25,000", "50,000", "100,000", "200,000", "300,000",
                    "500,000", "1,000,000"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        SubmitButton = (Button) findViewById(R.id.SubmitButton);
        QuitButton = (Button) findViewById(R.id.QuitButton);
        QuestionView = (TextView) findViewById(R.id.Question);
        options = (RadioGroup) findViewById(R.id.options);
        radio1 = (RadioButton) findViewById(R.id.Option1);
        radio2 = (RadioButton) findViewById(R.id.Option2);
        radio3 = (RadioButton) findViewById(R.id.Option3);
        radio4 = (RadioButton) findViewById(R.id.Option4);
        CashView = (TextView) findViewById(R.id.amount);

        CashView.setVisibility(View.INVISIBLE);

        CurrentQues = 1;

        // Quit option ONLY  at questions 5 & 10
        QuitButton.setVisibility(View.INVISIBLE);
    }

    private String getStringResourceByName(String aString) {
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return getString(resId);
    }

    private void NextQuestion(){

        StringBuilder strQuesID = new StringBuilder();
        strQuesID.append("question_");
        strQuesID.append(++CurrentQues);
        QuestionView.setText(getStringResourceByName(strQuesID.toString()));

        StringBuilder strRBOption = new StringBuilder();
        strRBOption.append("option_");
        strRBOption.append(CurrentQues);
        String strTemp = strRBOption.toString().concat("_a");

        radio1.setText(getStringResourceByName(strTemp));
        radio1.setChecked(false);

        strTemp = strRBOption.toString().concat("_b");
        radio2.setText(getStringResourceByName(strTemp));
        radio2.setChecked(false);

        strTemp = strRBOption.toString().concat("_c");
        radio3.setText(getStringResourceByName(strTemp));
        radio3.setChecked(false);

        strTemp = strRBOption.toString().concat("_d");
        radio4.setText(getStringResourceByName(strTemp));
        radio4.setChecked(false);

        if(CurrentQues == 5 || CurrentQues == 10){
            QuitButton.setVisibility(View.VISIBLE);
        }
        else
        {
            QuitButton.setVisibility(View.INVISIBLE);
        }
        SubmitButton.setEnabled(true);
    }

    public void Quit(View view){
        Intent i =  new Intent(this, WinningPage.class);
        Bundle bundle = new Bundle();
        bundle.putString("prize_amt", cash[CurrentQues -1]);
        i.putExtras(bundle);
        startActivity(i);
    }

    private RadioButton OptionChosen() {

        if(radio1.isChecked())
            return radio1;
        else if(radio2.isChecked())
            return radio2;
        else if(radio3.isChecked())
            return radio3;
        else if(radio4.isChecked())
            return radio4;
        else
        {
            // No options selected
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.emptyinput), Toast.LENGTH_SHORT);
            toast.show();
            return null;
        }
    }

    public void Submit(View view){

        Bundle bundle = new Bundle();
        StringBuilder answer = new StringBuilder();
        String answer_id, answer_text;
        answer.append("answer_");
        answer.append(CurrentQues);
        answer_id = answer.toString();
        answer_text  = getStringResourceByName(answer_id);
        RadioButton radioButtonChosen = OptionChosen();

        if(radioButtonChosen != null){
            if(radioButtonChosen.getText().equals(answer_text)) {

                // Correct answer
                Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.correct_answer) + cash[CurrentQues - 1], Toast.LENGTH_SHORT);
                toast.show();

                if (CurrentQues != Question15) {
                    StringBuilder strAmount = new StringBuilder();
                    strAmount.append(getString(R.string.win));
                    strAmount.append("$");
                    strAmount.append(cash[CurrentQues - 1]);

                    CashView.setText(strAmount.toString());
                    CashView.setVisibility(View.VISIBLE);

                    SubmitButton.setEnabled(false);

                    handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            NextQuestion();
                        }
                    }, TimeDelay);

                }
                else {

                    handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(getApplicationContext(), WinningPage.class);
                            Bundle nextbundle = new Bundle();
                            nextbundle.putString("prize_amt", cash[14]);
                            intent.putExtras(nextbundle);
                            startActivity(intent);
                        }

                    }, TimeDelay);

                }

            }

            else{

                //Incorrect answer
                Toast toast = Toast.makeText( getApplicationContext(),getString(R.string.wrong_answer), Toast.LENGTH_SHORT);
                toast.show();

                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent i =  new Intent(getApplicationContext(), GameLost.class);
                        startActivity(i);
                    }
                }, TimeDelay);

            }

        }

    }
}


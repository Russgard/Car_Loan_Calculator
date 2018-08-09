package android.russgar.com.carloan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText price;
    EditText rate;
    EditText taxes;
    EditText down;
    EditText months;
    TextView monthlyValue;
    TextView biweeklyValue;
    TextView monthlyInterest;
    TextView biweeklyInterest;
    TextView monthlyTotal;
    TextView biweeklyTotal;
    double principal;
    double interestrate;
    double taxRate;
    double downpayment;
    int cicles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        price = (EditText) findViewById(R.id.priceValue);
        rate = (EditText) findViewById(R.id.rateValue);
        taxes = (EditText) findViewById(R.id.taxesValue);
        down = (EditText) findViewById(R.id.downValue);
        months = (EditText) findViewById(R.id.monthsValue);
        monthlyValue = (TextView) findViewById(R.id.monthlyValue);
        biweeklyValue =(TextView) findViewById(R.id.biweeklyValue);
        monthlyInterest = (TextView) findViewById(R.id.monthlyInterest);
        biweeklyInterest = (TextView) findViewById(R.id.biweeklyInterest);
        monthlyTotal = (TextView) findViewById(R.id.monthlyTotal);
        biweeklyTotal = (TextView) findViewById(R.id.biweeklyTotal);
        rate.setText("4.59");
        taxes.setText("13");
        months.setText("84");


        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Calculate();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        rate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Calculate();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        taxes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Calculate();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        down.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Calculate();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        months.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Calculate();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });





    }
    public void Calculate(){
        try {
            principal = Double.parseDouble(price.getText().toString()) + 0.005;
            int i = (int) (principal * 100);
            principal = i/100;
        } catch (NumberFormatException e) {
            principal = 0;
        }
        try {
            interestrate = Double.parseDouble(rate.getText().toString()) / 100;
        } catch (NumberFormatException e) {
            interestrate = 0;
        }
        try {
            taxRate = Double.parseDouble(taxes.getText().toString()) / 100;
        } catch (NumberFormatException e) {
            taxRate = 0;
        }
        try {
            downpayment = Double.parseDouble(down.getText().toString());
            int y = (int) (downpayment * 100);
            downpayment = y / 100;
        } catch (NumberFormatException e) {
            downpayment = 0;
        }
        try {
            cicles = Integer.parseInt(months.getText().toString());
        } catch (NumberFormatException e) {
            cicles = 0;
        }

        if (principal != 0 && cicles > 0 && cicles <85){
            double mRate = interestrate / 12;
            double biRate = interestrate / 26;

            int biweeks;
            double some = (double)cicles * 13/6;
            biweeks = some > (int)cicles*13/6 ? (int)(cicles * 13 / 6 ) + 1: (int)(cicles * 13 / 6 );


            double mstepen = 1.00;
            double bistepen = 1.00;
            for (int i = 0; i < cicles; ++i){
                mstepen *= 1 + mRate;
            }
            for (int y = 0; y < biweeks; ++y){
                bistepen *= 1 + biRate;

            }
            principal += principal * taxRate - downpayment;
            double mnth = mRate * principal * mstepen / (mstepen - 1);
            int mnthInt = (int) ((mnth + 0.005) * 100);
            mnth = (double) mnthInt / 100;
            double bw = ((biRate * principal * bistepen / (bistepen -1)) + 0.005) * 100;
            int bwInt = (int) bw;
            bw = (double) bwInt / 100;
            monthlyValue.setText(""+mnth);
            biweeklyValue.setText("" + bw);
            double mInt = (mnth * cicles - principal + 0.005) * 100;
            int tempmInt = (int) mInt;
            mInt = (double) tempmInt/100;
            double wInt = (bw * biweeks -principal + 0.005) * 100;
            int tempbInt = (int) wInt;
            wInt = (double) tempbInt/100;
            monthlyInterest.setText("" + mInt);
            biweeklyInterest.setText("" + wInt);


            double tempMT = (principal+downpayment+mInt+0.005)*100;
            int iTempMT = (int) tempMT;
            mInt = (double) iTempMT/100;
            monthlyTotal.setText(""+mInt);
            double tempBT = (principal+downpayment+wInt+0.005)*100;
            int aInt = (int) tempBT;
            wInt = (double) aInt/100;
            biweeklyTotal.setText(""+wInt);


        }

    }
}

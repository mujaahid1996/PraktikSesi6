package com.laptop.asus.praktiksesi6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    String satuanAwalVal, satuanHasilVal;
    Double hasilVal;
    RadioButton celciusRb, fahrenheitRb, kelvinRb;
    RadioGroup rbGrup;
    EditText inputEt;
    TextView hasilTv;
    Button calculateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.suhuSpinner);
        celciusRb = (RadioButton) findViewById(R.id.celciusRb);
        fahrenheitRb = (RadioButton) findViewById(R.id.fahrenheitRb);
        kelvinRb = (RadioButton) findViewById(R.id.kelvinRb);
        inputEt = (EditText) findViewById(R.id.inputEt);
        hasilTv = (TextView) findViewById(R.id.hasilTv);
        calculateBtn = (Button) findViewById(R.id.calculateBtn);
        rbGrup = (RadioGroup) findViewById(R.id.rbGrup);

        //set radio button default
        rbGrup.check( R.id.celciusRb );
        satuanHasilVal = getString(R.string.celcius);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                satuanAwalVal = item.toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        kelvinRb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton radioButton = (RadioButton) v;
                satuanHasilVal = radioButton.getText().toString();
//                Toast.makeText(getApplicationContext(), satuanHasilVal+" dipilih", Toast.LENGTH_SHORT).show();
            }
        });
        celciusRb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihRadioButton(v);
            }
        });
        fahrenheitRb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihRadioButton(v);
            }
        });


        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean awalCelcius = satuanAwalVal.equals(getText(R.string.celcius));
                boolean awalFahrenheit = satuanAwalVal.equals(getText(R.string.fahrenheit));
                boolean awalKelvin = satuanAwalVal.equals(getText(R.string.kelvin));

                boolean hasilCelcius = satuanHasilVal.equals(getString(R.string.celcius));
                boolean hasilFahrenheit = satuanHasilVal.equals(getString(R.string.fahrenheit));
                boolean hasilKelvin = satuanHasilVal.equals(getString(R.string.kelvin));

//                Toast.makeText(MainActivity.this, ""+satuanAwalVal, Toast.LENGTH_SHORT).show();

                if (awalCelcius == true) {
                    if (hasilCelcius == true) {
                        hasilVal = hasilInputSuhu();
                        outputSuhu();
                    } else if (hasilKelvin == true) {
                        hasilVal = hasilInputSuhu() + 273.15;
                        outputSuhu();
                    } else if (hasilFahrenheit == true) {
                        hasilVal = (hasilInputSuhu() * 1.8) + 32;
                        outputSuhu();
                    }
                }
                else if (awalFahrenheit == true) {
                    if (hasilCelcius == true) {
                        hasilVal =  (hasilInputSuhu() - 32 ) / 1.8;
                        outputSuhu();
                    } else if (hasilKelvin == true) {
                        hasilVal = (hasilInputSuhu() + 459.67 ) / 1.8;
                        outputSuhu();
                    } else if (hasilFahrenheit == true) {
                        hasilVal = hasilInputSuhu();
                        outputSuhu();
                    }
                }
                else if (awalKelvin == true) {
                    if (hasilCelcius == true) {
                        hasilVal =  hasilInputSuhu() - 273.15;
                        outputSuhu();
                    } else if (hasilKelvin == true) {
                        hasilVal = hasilInputSuhu();
                        outputSuhu();
                    } else if (hasilFahrenheit == true) {
                        hasilVal = hasilInputSuhu() * 1.8 - 459.67;
                        outputSuhu();
                    }
                }

            }
        });


    }

    private Double hasilInputSuhu() {
        hasilVal = Double.parseDouble(inputEt.getText().toString());
        return hasilVal;
    }

    //tampilkan suhu
    private void outputSuhu() {
        hasilTv.setText(hasilVal.toString());
    }

    private void pilihRadioButton(View v) {
        RadioButton radioButton = (RadioButton) v;
        satuanHasilVal = radioButton.getText().toString();
    }
}

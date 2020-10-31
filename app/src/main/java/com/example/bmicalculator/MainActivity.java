/* #############################
 *
 * Author: Johnathon Mc Grory
 * Date : 10 October 2020
 * Description : Lab Two MainActivity page Java Code
 *
 * ############################# */
package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //initialise local variables
    EditText weight, height;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set background colour
        view = this.getWindow().getDecorView();
        view.setBackgroundResource(R.color.Blue);

        // link widgets to xml code
        weight = findViewById(R.id.etWeight);
        height = findViewById(R.id.etTime);
    }

    public void doCalculation(View view) {

        //initialize and store variables from the EditTexts (store inputs)
        int inputWeight = Integer.valueOf(weight.getText().toString());
        int inputHeight = Integer.valueOf(height.getText().toString());

        //check that values have been inputted (data validation)
        if (weight != null && height != null)
        {
            //check to make sure that the input is an actual human weight (data validation)
            if (inputWeight > 20 && inputWeight < 200 && inputHeight > 80 && inputHeight < 300)
            {
                //create a new intent
                Intent bmi = new Intent(view.getContext(), ResultsActivity.class);
                //pass the values
                bmi.putExtra("inputWeight", inputWeight);
                bmi.putExtra("inputHeight", inputHeight);
                //starts the new page
                startActivity(bmi);
            }
            else
            {
                //throw an error to the user and clear the input fields
                Toast.makeText(this, "INVALID INPUT!", Toast.LENGTH_LONG).show();
                doReset(view);
            }
        }
        else
        {
            //tell the user that they didn't enter any values
            Toast.makeText(this, "You didn't enter any values!", Toast.LENGTH_LONG).show();
        }

    }

    public void doReset(View view) {
        //Reset the input values to zero
        height.setText("");
        weight.setText("");
    }
}

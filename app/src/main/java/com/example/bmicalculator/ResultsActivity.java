/* #############################
 *
 * Author: Johnathon Mc Grory
 * Date : 10 October 2020
 * Description : Lab Two ResultsActivity page Java Code
 *
 * ############################# */

package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ResultsActivity extends AppCompatActivity {

    //Initializing local variables
    int weight, height  = 0;
    TextView Weight, Height, Result, Range;
    String category;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //define and store text views for use in methods
        Weight = findViewById(R.id.tvWeight);
        Height = findViewById(R.id.tvHeight);
        Result = findViewById(R.id.tvResult);
        Range = findViewById(R.id.tvRange);

        //just woke up
        //look into the pipe
        //gets the values passed from the main page
        weight = getIntent().getIntExtra("inputWeight", -1);
        height = getIntent().getIntExtra("inputHeight", -1);

        //sets values to be outputted to the text views
        Weight.setText("Your Weight: " + String.valueOf(weight) + " kg ");
        Height.setText("Your Height: " + String.valueOf(height) + " cm ");

        //BMI calculation - divide weight (kg) by height (cm) squared, multiply by 10,000 and round to one decimal place.
        double BMIResult = (weight / (Math.pow(height, 2))) * 10000;
        //round to two decimal places
        BMIResult = Math.round(BMIResult * 100.0) / 100.0;

        Result.setText("Your BMI: " + String.valueOf(BMIResult));

        //18.5 and below is considered underweight
        if (BMIResult <= 18.5)
        {
            category = "Under Weight";
            //Set background to red
            //set background colour
            view = this.getWindow().getDecorView();
            view.setBackgroundResource(R.color.Red);

            if ( BMIResult < 18.5 && BMIResult >= 16)
            {
                //bordering on healthy weight
                Toast.makeText(this, "You are almost a healthy weight", Toast.LENGTH_LONG).show();
                view = this.getWindow().getDecorView();
                view.setBackgroundResource(R.color.Yellow);
            }
            else if( BMIResult < 16)
            {
                //Extremely Underweight
                Toast.makeText(this, "You are  Extremely Underweight", Toast.LENGTH_LONG).show();
            }
            //very underweight
        }
        else if (BMIResult > 18.5 && BMIResult <=  24.9)
        {
            //healthy weight
            category = "Healthy Weight";
            view = this.getWindow().getDecorView();
            view.setBackgroundResource(R.color.Green);

            // middle of 18.5 and 24.9 is 21.7
            // difference between underweight range (18.5) and the middle (21.7) is 3.2
            // half that (1.6) to find centre point (18.5 + 1.6) = 20.1
            //anything less than the centre point is bordering on underweight so color it yellow

            if (BMIResult <= 20.1)
            {
                //almost underweight
                Toast.makeText(this, "You are Almost Underweight ", Toast.LENGTH_LONG).show();
                view = this.getWindow().getDecorView();
                view.setBackgroundResource(R.color.Yellow);
            }
            // middle of 18.5 and 24.9 is 21.7
            // difference between overweight range (24.9) and the middle (21.7) is 3.2
            // half that (1.6) to find centre point (24.9 - 1.6) = 23.3
            //anything more than the centre point is bordering on overweight so color it yellow
            else if( BMIResult >= 23.3)
            {
                //Almost overweight
                Toast.makeText(this, "You are Almost Overweight", Toast.LENGTH_LONG).show();
                view = this.getWindow().getDecorView();
                view.setBackgroundResource(R.color.Yellow);
            }
        }
        else
        {
            //if the BMIResult fits into none of these it must be overweight
            category = "Overweight";
            //Set background to red
            view = this.getWindow().getDecorView();
            view.setBackgroundResource(R.color.Red);

            if (BMIResult < 27.5)
            {
                //Slightly Overweight
                Toast.makeText(this, "You are Slightly Overweight", Toast.LENGTH_LONG).show();
                view = this.getWindow().getDecorView();
                view.setBackgroundResource(R.color.Yellow);
            }
            else if (BMIResult > 30)
            {
                Toast.makeText(this, "You are Obese", Toast.LENGTH_LONG).show();
                view = this.getWindow().getDecorView();
            }
        }

        //Attempted to use Spans to make the weight category string variable bold
//        final SpannableStringBuilder sb = new SpannableStringBuilder(category);
//        final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD); // Span to make text bold
//        sb.setSpan(bss, 0, 10, Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make first 4 characters Bold
//        Range.setText("You are in the " + category + " weight category");


        //Attempted to use html tags to make the weight category string variable bold
//        String htmlStart="You are in the <b>";
//        String htmlEnd="</b> weight category";
//        Result.setText(String.valueOf(" Your BMI is " + BMIResult));
//        Range.setText(Html.fromHtml(htmlStart) + category + Html.fromHtml(htmlEnd) );

        Range.setText("You are in the :      '" + category + "' weight category");
    }

    public void DoExit(View view) {
        //go back to the main screen
        finish();
    }
}

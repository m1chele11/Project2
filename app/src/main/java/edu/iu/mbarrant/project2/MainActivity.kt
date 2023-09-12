package edu.iu.mbarrant.project2

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlin.math.sin

class MainActivity : AppCompatActivity() {

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main)
            // Handle landscape-specific UI changes here if needed
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main)
            // Handle portrait-specific UI changes here if needed
        }
    }

//________________________
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initializing the current number as an empty string until one is clicked
        var currentNumber = ""
        //initializing the current operator as an empty string until one is clicked
        var currentOperator = ""
        //the current result is 0.0 until a number is clicked and operator is executed
        var result = 0.0
        // this is used for the clear Button, since a clear will always display 0
        var empty0 = "0"


        //this is the text view
        val resultTV = findViewById<TextView>(R.id.resultTV)


        val buttonIDsLand = arrayOf(
            R.id.buttonSin, R.id.buttonCos,
            R.id.buttonTan, R.id.buttonLog,
            R.id.buttonLn)

        //an array of button ID's
        val buttonIds = arrayOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
            R.id.buttonPlus, R.id.buttonMinus, R.id.buttonNegate, R.id.buttonDivide, R.id.buttonDecimal,
            R.id.buttonEquals, R.id.buttonClear, R.id.buttonPercent, R.id.buttonEquals, R.id.buttonTimes
        )

    // For loop to loop through the array, that way we don't need multiple click Listeners
    for (buttonId in buttonIds) {
        /*
        here the button selected becomes the button val, we check the button label
        first, that way we know what math to apply to the numbers clicked.
        since it is a string and we have the possibility of having a double we use .todouble()
        once an operator is selected the current number becomes empty again so that the user can select a new number to execut the operation
        we assign the result number to be displayed in the text view
         */
        val button: Button = findViewById(buttonId)
        button.setOnClickListener {
            when (val buttonLabel = button.text) {
                "+/-" -> {
                    currentNumber = (currentNumber.toDouble() * -1).toString()
                    resultTV.text = currentNumber
                    Log.d("CalculatorActivity", "Button Clicked: neglect")
                }
                "sin" -> {
                    try {
                        // Calculate the sine value (in radians) and display it
                        val radians = currentNumber.toDouble()
                        val result = sin(radians)
                        currentNumber = result.toString()
                        resultTV.text = currentNumber
                        currentOperator = ""

                        // Logging for the "Sin" button click
                        Log.d("CalculatorActivity", "Button Clicked: Sin")
                    } catch (e: NumberFormatException) {
                        // Handle invalid input (non-numeric input)
                        currentNumber = ""
                        resultTV.text = "Error"
                        Log.e("CalculatorActivity", "Error: Invalid input for Sin")
                    }
                }

                "+", "-", "X", "/" -> {
                    currentOperator = buttonLabel.toString()
                    result = currentNumber.toDouble()
                    currentNumber = ""
                    //Log.d("CalculatorActivity", "Button Clicked: operator")
                    if (setOf("+", "-", "X", "/").contains(buttonLabel)) {
                        // Log the clicked operator
                        Log.d("CalculatorActivity", "Button Clicked: Operator $buttonLabel")
                    }

                }
                "%" -> {
                    if (currentNumber.isNotEmpty()) {
                        // Calculate the percentage
                        result = currentNumber.toDouble() / 100
                        currentNumber = result.toString()
                        resultTV.text = currentNumber
                        currentOperator = ""

                        // Logging for the "%" button click
                        Log.d("CalculatorActivity", "Button Clicked: Percent")
                    }
                }
                //check what math to apply by button label
                "=" -> {
                    if (currentOperator.isNotEmpty() && currentNumber.isNotEmpty()) {
                        when (currentOperator) {
                            "+" -> result += currentNumber.toDouble()
                            "-" -> result -= currentNumber.toDouble()
                            "X" -> result *= currentNumber.toDouble()
                            "/" -> result /= currentNumber.toDouble()
                            "%" -> result = currentNumber.toDouble() / 100
                        }
                        currentNumber = result.toString()
                        resultTV.text = currentNumber
                        currentOperator = ""
                        //currentNumber = ""

                        // Logging for the "=" button click
                        Log.d("CalculatorActivity", "Button Clicked: Equals")
                    }
                }
                //clear
                "C" -> {
                    currentNumber = empty0
                    resultTV.text = currentNumber
                    currentNumber =""
                    Log.d("CalculatorActivity", "Button Clicked: Clear")

                }
                //assign to TV
                else -> {
                    // if (currentOperator.isEmpty()) {
                    // If no operator has been selected, reset currentNumber
                    //currentNumber = ""
                    //}

                    if (buttonLabel == ".") {
                        // Check if the button label is a decimal point
                        // Ensure there's only one decimal point in the currentNumber
                        if (!currentNumber.contains(".")) {
                            currentNumber += buttonLabel
                        }
                    } else {
                        currentNumber += buttonLabel
                    }

                    resultTV.text = currentNumber
                    // Log the clicked number
                    Log.d("CalculatorActivity", "Button Clicked: Number $buttonLabel")
                }
            }
        }

    }


    }
}

/*
/*
//function to handle buttons
fun handleButtonClick(buttonLabel: String) {

here the button selected becomes the button val, we check the button label
first, that way we know what math to apply to the numbers clicked.
since it is a string and we have the possibility of having a double we use .todouble()
once an operator is selected the current number becomes empty again so that the user can select a new number to execut the operation
we assign the result number to be displayed in the text view
*/
    val resultTV = findViewById<TextView>(R.id.resultTV)

    when (buttonLabel) {
        "+/-" -> {
            currentNumber = (currentNumber.toDouble() * -1).toString()
            resultTV.text = currentNumber
            Log.d("CalculatorActivity", "Button Clicked: negate")
        }
        "sin" -> {
            try {
                // Calculate the sine value (in radians) and display it
                val radians = currentNumber.toDouble()
                val result = sin(radians)
                currentNumber = result.toString()
                resultTV.text = currentNumber
                currentOperator = ""

                // Logging for the "Sin" button click
                Log.d("CalculatorActivity", "Button Clicked: Sin")
            } catch (e: NumberFormatException) {
                // Handle invalid input (non-numeric input)
                currentNumber = ""
                resultTV.text = "Error"
                Log.e("CalculatorActivity", "Error: Invalid input for Sin")
            }
        }

        "+", "-", "X", "/" -> {
            currentOperator = buttonLabel
            result = currentNumber.toDouble()
            currentNumber = ""
            if (setOf("+", "-", "X", "/").contains(buttonLabel)) {
                Log.d("CalculatorActivity", "Button Clicked: Operator $buttonLabel")
            }
        }
        "%" -> {
            if (currentNumber.isNotEmpty()) {
                // Calculate the percentage
                result = currentNumber.toDouble() / 100
                currentNumber = result.toString()
                resultTV.text = currentNumber
                currentOperator = ""
                Log.d("CalculatorActivity", "Button Clicked: Percent")
            }
        }
        "=" -> {
            if (currentOperator.isNotEmpty() && currentNumber.isNotEmpty()) {
                when (currentOperator) {
                    "+" -> result += currentNumber.toDouble()
                    "-" -> result -= currentNumber.toDouble()
                    "X" -> result *= currentNumber.toDouble()
                    "/" -> result /= currentNumber.toDouble()
                    "%" -> result = currentNumber.toDouble() / 100
                }
                currentNumber = result.toString()
                resultTV.text = currentNumber
                currentOperator = ""
                Log.d("CalculatorActivity", "Button Clicked: Equals")
            }
        }
        "C" -> {
            currentNumber = empty0
            resultTV.text = currentNumber
            currentNumber = ""
            Log.d("CalculatorActivity", "Button Clicked: Clear")
        }
        else -> {
            if (buttonLabel == ".") {
                if (!currentNumber.contains(".")) {
                    currentNumber += buttonLabel
                }
            } else {
                currentNumber += buttonLabel
            }
            resultTV.text = currentNumber
            Log.d("CalculatorActivity", "Button Clicked: Number $buttonLabel")
        }
    }
}

for (buttonId in buttonIds) {
    val button: Button = findViewById(buttonId)
    button.setOnClickListener {
        handleButtonClick(button.text.toString())
    }
}
// Set click listeners for landscape buttons
for (buttonId in buttonIDsLand) {
    val button: Button = findViewById(buttonId)
    button.setOnClickListener {
        handleButtonClick(button.text.toString())
    }
}
    */
package com.shencare.shencaremobile;

/**
 * Created by Administrator on 2016/1/15.
 */
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.regex.Pattern;

public class Validation {
    //Regular Expression
    //The expression should have validation details for the email, phone, username, password, general name (including name and surname)

    //This expression matches email addresses, and checks that they are of the proper form. It checks to ensure the top level domain is between 2 and 4 characters long, but does not check the specific domain against a list (especially since there are so many of them now).
    //Matches  joe@aol.com | joe@wrox.co.uk | joe@domain.info
    //Non-Matches a@b | notanemail | joe@@.
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    //Singapore Phone number
    private static final String PHONE_REGEX = "^[689]\\d{7}$";
    //Password matching expression. Match all alphanumeric character and predefined wild characters. Password must consists of at least 6 characters and not more than 12 characters.
    //Matches  @12X*567 | 1#Zv96g@*Yfasd4 | #67jhgt@erd
    //Non-Matches $12X*567 | 1#Zv_96 | +678jhgt@erd
    private static final String PASSWORD_REGEX ="^([a-zA-Z0-9@*#]{6,12})$";
    //username should be 4 - 10 characters long, it cannot contain any white space.
    private static final String USERNAME_REGEX = "^(?=.{4,10}$)(?![_.])[a-zA-Z0-9._]+(?<![.])$";
    //Name and surname can only contain letters
    private static final String GENERAL_NAME_REGEX = "^[a-zA-Z-_ ]+$";

    //Error Message
    private static final String REQUIRED_MSG = "required field.";
    private static final String EMAIL_MSG = "invalid email.";
    private static final String PASSWORD_MSG = "invalid password: password should be 6 - 12 characters and should not contain white space.";
    private static final String PHONE_MSG = "invalid phone: phone number should start with 6,8,9 and should have 8 digits.";
    private static final String USERNAME_MSG = "invalid username: username should be 4 - 10 characters and should not contain white space.";
    private static final String GENERAL_NAME_MSG = "Please key in a valid name.";
    private static final String RADIOBUTTON_MSG = "Please select one.";
    private static final String COMFIRMED_PW_MSG ="Password do not match.";

    //call this field when you want to check email validation
    public static boolean isEmailAddress(EditText editText, boolean required){
        return isValid(editText,EMAIL_REGEX,EMAIL_MSG,required);
    }

    //call this field when you want to check phone validation
    public static boolean isPhone(EditText editText,boolean required){
        return isValid(editText,PHONE_REGEX,PHONE_MSG,required);
    }

    //call this field when you want to check password validation
    public static boolean isPassword(EditText editText, boolean required){
        return isValid(editText,PASSWORD_REGEX,PASSWORD_MSG,required);
    }

    //call this field when you want to check user name validation
    public static boolean isUsername(EditText editText, boolean required){
        return isValid(editText,USERNAME_REGEX,USERNAME_MSG,required);
    }

    //call this field when you want to check for name and surname validation
    public static boolean isGeneralName(EditText editText, boolean required){
        return isValid(editText,GENERAL_NAME_REGEX,GENERAL_NAME_MSG,required);
    }

    // check if the mandatory radioGroup field has been selected
    public static boolean isRadioButtonChecked(RadioGroup radioGroup,TextView textView, boolean required){
        textView.setError(null);
        //no radio buttons are checked
        if (radioGroup.getCheckedRadioButtonId() == -1){
            textView.setError(RADIOBUTTON_MSG);
            textView.requestFocus();
            return false;}
        return true;
    }

    //To validate the [confirmed password] field
    public static boolean isValidConfirmedPassword(EditText editText, String password, boolean required){
        String text = editText.getText().toString().trim();
        //clearing the error, if it was previously set by some other values
        editText.setError(null);
        //text required and editText is blank, so return false
        if(required && !hasText(editText)) return false;

        if(text !=null && !text.equals(password)){
            editText.setError(COMFIRMED_PW_MSG);
            return false;
        }
        return true;
    }


    //return true if the input field is valid based on the parameter passed in
    public static boolean isValid(EditText editText,String regex, String errMsg, boolean required){

        String text = editText.getText().toString().trim();
        //clearing the error, if it was previously set by some other values
        editText.setError(null);

        //text required and editText is blank, so return false
        if(required && !hasText(editText)) return false;

        //pattern doesn't match so returning false
        if(required && !Pattern.matches(regex, text)){
            editText.setError(errMsg);
            return false;
        }
        return true;
    }

    //Check whether the input field has text
    //return true if it contains text otherwise false
    public static boolean hasText(EditText editText){

        String text = editText.getText().toString().trim();
        //clearing the error, if it was previously set by some other values
        editText.setError(null);
        //length 0 means there is no text
        if(text.length()==0){
            editText.setError(REQUIRED_MSG);
            return false;
        }
        return true;
    }

    public static boolean validateDropDown(Spinner spinner, String selected, TextView textview){
        textview.setError(null);
        String st = spinner.getSelectedItem().toString();
        int pos = spinner.getSelectedItemPosition();
        if(pos == 0 || st == null || st.equals("--Please select one--")){
            textview.setError("Please select one.");
            textview.requestFocus();
            return false;
        }
        selected = spinner.getSelectedItem().toString();
        return true;
    }

}

package agency.five.codebase.android.ordermanager

const val WEIGHT_1 = 1f
const val WEIGHT_2 = 2f
const val WEIGHT_3 = 3f
const val WEIGHT_4 = 4f
const val WEIGHT_5 = 5f

const val ROUNDED_CORNER_PERCENT_13 = 13
const val ROUNDED_CORNER_PERCENT_30 = 30

const val GRID_COUNT: Int = 2

const val PASSWORD_REGEX: String =
        "^" +
            "(?=.*[0-9])" +         //at least 1 digit
            "(?=.*[a-z])" +         //at least 1 lower case letter
            "(?=.*[A-Z])" +         //at least 1 upper case letter
            "(?=.*[a-zA-Z])" +      //any letter
            "(?=.*[@#$%^&+=])" +    //at least 1 special character
            "(?=\\S+$)" +           //no white spaces
            ".{8,}" +               //at least 8 characters
        "$"

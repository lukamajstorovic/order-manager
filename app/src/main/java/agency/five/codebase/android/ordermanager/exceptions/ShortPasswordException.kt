package agency.five.codebase.android.ordermanager.exceptions

import agency.five.codebase.android.ordermanager.ui.registerstaff.MIN_PASSWORD_LENGTH

class ShortPasswordException :
    Exception("Password is too short. Minimum length is $MIN_PASSWORD_LENGTH")

package agency.five.codebase.android.ordermanager.data.currentuser

import agency.five.codebase.android.ordermanager.enums.StaffRoles
import agency.five.codebase.android.ordermanager.model.Staff
import agency.five.codebase.android.ordermanager.ui.main.dataStore
import android.app.Application
import androidx.datastore.core.DataStore
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class UserDataViewModel(application: Application) : AndroidViewModel(application) {

    private val appContext = application.applicationContext
    private val userDataStore: DataStore<UserData> = appContext.dataStore

    val userDataFlow: Flow<UserData> = userDataStore.data

    suspend fun setUserData(staff: Staff) {
        userDataStore.updateData { currentUserData ->
            currentUserData.copy(
                username = staff.username,
                name = staff.name,
                role = staff.role
            )
        }
    }

    suspend fun clearUserData() {
        userDataStore.updateData { currentUserData ->
            currentUserData.copy(
                username = "",
                name = "",
                role = StaffRoles.NONE
            )
        }
    }
}

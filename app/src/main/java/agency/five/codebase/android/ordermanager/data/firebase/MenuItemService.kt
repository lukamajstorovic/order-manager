package agency.five.codebase.android.ordermanager.data.firebase

import agency.five.codebase.android.ordermanager.data.firebase.model.DbMenuItem
import kotlinx.coroutines.flow.Flow

interface MenuItemService {
    fun getMenuItems(establishmentId: String): Flow<List<DbMenuItem>>
    suspend fun addMenuItem(menuItem: DbMenuItem): Result<Unit>
    suspend fun updateMenuItem(menuItem: DbMenuItem): Result<Unit>
    suspend fun removeMenuItem(id: String): Result<Unit>
}

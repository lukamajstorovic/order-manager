package agency.five.codebase.android.ordermanager.data.firebase.repository.menuitem

import agency.five.codebase.android.ordermanager.data.firebase.model.DbMenuItem
import kotlinx.coroutines.flow.Flow

interface MenuItemRepository {
    fun getMenuItems(establishmentId: String): Flow<List<DbMenuItem>>
    suspend fun addMenuItem(menuItem: DbMenuItem): Result<Unit>
    suspend fun removeMenuItem(id: String): Result<Unit>
}

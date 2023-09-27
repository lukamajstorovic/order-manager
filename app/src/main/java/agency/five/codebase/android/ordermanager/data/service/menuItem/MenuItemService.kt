package agency.five.codebase.android.ordermanager.data.service.menuItem

import agency.five.codebase.android.ordermanager.model.MenuItem
import kotlinx.coroutines.flow.Flow

interface MenuItemService {
    fun getMenuItems(establishmentId: String): Flow<List<MenuItem>>
    suspend fun addMenuItem(menuItem: MenuItem): Result<Unit>
    suspend fun removeMenuItem(id: String): Result<Unit>
}

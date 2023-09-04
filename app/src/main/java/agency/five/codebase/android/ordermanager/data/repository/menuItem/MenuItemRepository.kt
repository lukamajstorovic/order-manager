package agency.five.codebase.android.ordermanager.data.repository.menuItem

import agency.five.codebase.android.ordermanager.model.MenuItem
import kotlinx.coroutines.flow.Flow

interface MenuItemRepository {
    fun getMenuItems(establishmentId: String): Flow<List<MenuItem>>
    suspend fun addMenuItem(menuItem: MenuItem): Result<Unit>
    suspend fun updateMenuItem(menuItem: MenuItem): Result<Unit>
    suspend fun removeMenuItem(id: String): Result<Unit>
}

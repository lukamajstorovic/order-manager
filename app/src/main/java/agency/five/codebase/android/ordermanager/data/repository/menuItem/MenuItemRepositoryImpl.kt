package agency.five.codebase.android.ordermanager.data.repository.menuItem

import agency.five.codebase.android.ordermanager.data.firebase.MenuItemService
import agency.five.codebase.android.ordermanager.model.MenuItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn

class MenuItemRepositoryImpl(
    private val menuItemService: MenuItemService,
    private val bgDispatcher: CoroutineDispatcher,
) : MenuItemRepository {
    override fun getMenuItems(establishmentId: String): Flow<List<MenuItem>> =
        menuItemService.getMenuItems(establishmentId).map {
            it.map { dbMenuItem ->
                dbMenuItem.toMenuItem()
            }
        }.shareIn(
            scope = CoroutineScope(bgDispatcher),
            started = SharingStarted.WhileSubscribed(1000L),
            replay = 1,
        )

    override suspend fun addMenuItem(menuItem: MenuItem): Result<Unit> {
        return menuItemService.addMenuItem(
            menuItem.toDbMenuItem()
        )
    }

    override suspend fun updateMenuItem(menuItem: MenuItem): Result<Unit> {
        return menuItemService.updateMenuItem(
            menuItem.toDbMenuItem()
        )
    }

    override suspend fun removeMenuItem(id: String): Result<Unit> {
        return removeMenuItem(id)
    }
}


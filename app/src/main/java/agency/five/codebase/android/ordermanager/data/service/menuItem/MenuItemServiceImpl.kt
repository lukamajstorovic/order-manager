package agency.five.codebase.android.ordermanager.data.service.menuItem

import agency.five.codebase.android.ordermanager.data.firebase.repository.menuitem.MenuItemRepository
import agency.five.codebase.android.ordermanager.model.MenuItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn

class MenuItemServiceImpl(
    private val menuItemRepository: MenuItemRepository,
    private val bgDispatcher: CoroutineDispatcher,
) : MenuItemService {
    override fun getMenuItems(establishmentId: String): Flow<List<MenuItem>> =
        menuItemRepository.getMenuItems(establishmentId).map {
            it.map { dbMenuItem ->
                dbMenuItem.toMenuItem()
            }
        }.shareIn(
            scope = CoroutineScope(bgDispatcher),
            started = SharingStarted.WhileSubscribed(1000L),
            replay = 1,
        )

    override suspend fun addMenuItem(menuItem: MenuItem): Result<Unit> {
        return menuItemRepository.addMenuItem(
            menuItem.toDbMenuItem()
        )
    }

    override suspend fun removeMenuItem(id: String): Result<Unit> {
        return menuItemRepository.removeMenuItem(id)
    }
}


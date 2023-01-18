package agency.five.codebase.android.ordermanager

import agency.five.codebase.android.ordermanager.data.di.dataModule
import agency.five.codebase.android.ordermanager.data.di.databaseModule
import agency.five.codebase.android.ordermanager.data.repository.OrderRepository
import agency.five.codebase.android.ordermanager.mock.MenuItemMock
import agency.five.codebase.android.ordermanager.ui.activeorders.di.activeOrdersModule
import agency.five.codebase.android.ordermanager.ui.completeorder.di.completeOrderModule
import agency.five.codebase.android.ordermanager.ui.confirmorder.di.confirmOrderModule
import agency.five.codebase.android.ordermanager.ui.selection.di.selectionModule
import android.app.Application
import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class OrderManagerApp : Application() {
    private val repository: OrderRepository by inject()
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@OrderManagerApp)
            modules(
                dataModule,
                activeOrdersModule,
                completeOrderModule,
                selectionModule,
                confirmOrderModule,
                databaseModule,
            )
            GlobalScope.launch {
                repository.addMenuItem(MenuItemMock.getMenuItemList()[0])
                repository.addMenuItem(MenuItemMock.getMenuItemList()[1])
            }
        }
    }
}

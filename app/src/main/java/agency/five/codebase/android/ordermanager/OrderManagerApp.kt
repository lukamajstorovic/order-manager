package agency.five.codebase.android.ordermanager

import agency.five.codebase.android.ordermanager.data.di.dataModule
import agency.five.codebase.android.ordermanager.data.di.databaseModule
import agency.five.codebase.android.ordermanager.data.repository.OrderRepository
import agency.five.codebase.android.ordermanager.data.repository.StaffRepository
import agency.five.codebase.android.ordermanager.mock.MenuItemMock
import agency.five.codebase.android.ordermanager.mock.StaffMock
import agency.five.codebase.android.ordermanager.ui.activeorders.di.activeOrdersModule
import agency.five.codebase.android.ordermanager.ui.completeorder.di.completeOrderModule
import agency.five.codebase.android.ordermanager.ui.confirmorder.di.confirmOrderModule
import agency.five.codebase.android.ordermanager.ui.selection.di.selectionModule
import agency.five.codebase.android.ordermanager.ui.staff.di.staffModule
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
    private val orderRepository: OrderRepository by inject()
    private val staffRepository: StaffRepository by inject()
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
                staffModule,
            )
            GlobalScope.launch {
                orderRepository.addMenuItem(MenuItemMock.getMenuItemList()[0])
                orderRepository.addMenuItem(MenuItemMock.getMenuItemList()[1])
                orderRepository.addMenuItem(MenuItemMock.getMenuItemList()[2])
                orderRepository.addMenuItem(MenuItemMock.getMenuItemList()[3])
                orderRepository.addMenuItem(MenuItemMock.getMenuItemList()[4])
                staffRepository.addStaff(StaffMock.getStaffList()[0])
                staffRepository.addStaff(StaffMock.getStaffList()[1])
                staffRepository.addStaff(StaffMock.getStaffList()[2])
            }
        }
    }
}

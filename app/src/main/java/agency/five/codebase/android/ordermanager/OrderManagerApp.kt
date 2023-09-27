package agency.five.codebase.android.ordermanager

import agency.five.codebase.android.ordermanager.data.di.dataModule
import agency.five.codebase.android.ordermanager.data.di.databaseModule
import agency.five.codebase.android.ordermanager.data.di.firebaseModule
import agency.five.codebase.android.ordermanager.data.di.repositoryModule
import agency.five.codebase.android.ordermanager.data.service.order.OrderService
import agency.five.codebase.android.ordermanager.data.service.staff.StaffService
import agency.five.codebase.android.ordermanager.ui.completeorder.di.completeOrderModule
import agency.five.codebase.android.ordermanager.ui.confirmorder.di.confirmOrderModule
import agency.five.codebase.android.ordermanager.ui.individualstaff.di.individualStaffModule
import agency.five.codebase.android.ordermanager.ui.login.di.authenticationModule
import agency.five.codebase.android.ordermanager.ui.menu.di.menuModule
import agency.five.codebase.android.ordermanager.ui.order.di.ordersModule
import agency.five.codebase.android.ordermanager.ui.registerstaff.di.registerStaffModule
import agency.five.codebase.android.ordermanager.ui.selection.di.selectionModule
import agency.five.codebase.android.ordermanager.ui.staff.di.staffModule
import android.app.Application
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class OrderManagerApp : Application() {
    private val orderService: OrderService by inject()
    private val staffService: StaffService by inject()
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@OrderManagerApp)
            modules(
                dataModule,
                ordersModule,
                completeOrderModule,
                selectionModule,
                confirmOrderModule,
                databaseModule,
                staffModule,
                authenticationModule,
                registerStaffModule,
                firebaseModule,
                repositoryModule,
                individualStaffModule,
                menuModule,
            )
            GlobalScope.launch {
            }
        }
    }
}

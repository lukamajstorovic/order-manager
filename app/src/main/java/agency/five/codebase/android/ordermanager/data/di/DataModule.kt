package agency.five.codebase.android.ordermanager.data.di

import agency.five.codebase.android.ordermanager.data.database.*
import agency.five.codebase.android.ordermanager.data.repository.OrderRepository
import agency.five.codebase.android.ordermanager.data.repository.OrderRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    single<OrderRepository> {
        OrderRepositoryImpl(get(), get(), get(), get(), Dispatchers.IO)
    }
    fun provideMenuItemDao(db: OrderManagerDatabase): MenuItemDao = db.getMenuItemDao()
    single {
        provideMenuItemDao(get())
    }
    fun provideOrderedItemDao(db: OrderManagerDatabase): OrderedItemDao = db.getOrderedItemDao()
    single {
        provideOrderedItemDao(get())
    }
    fun provideActiveOrderDao(db: OrderManagerDatabase): ActiveOrderDao = db.getActiveOrderDao()
    single {
        provideActiveOrderDao(get())
    }
    fun provideOrderedItemInActiveOrderDao(db: OrderManagerDatabase): OrderedItemInActiveOrderDao =
        db.getOrderedItemInActiveOrderDao()
    single {
        provideOrderedItemInActiveOrderDao(get())
    }
}

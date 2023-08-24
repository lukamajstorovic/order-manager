package agency.five.codebase.android.ordermanager.data.di

import agency.five.codebase.android.ordermanager.data.repository.establishment.EstablishmentRepository
import agency.five.codebase.android.ordermanager.data.repository.establishment.EstablishmentRepositoryImpl
import agency.five.codebase.android.ordermanager.data.repository.order.OrderRepository
import agency.five.codebase.android.ordermanager.data.repository.order.OrderRepositoryImpl
import agency.five.codebase.android.ordermanager.data.repository.staff.StaffRepository
import agency.five.codebase.android.ordermanager.data.repository.staff.StaffRepositoryImpl
import agency.five.codebase.android.ordermanager.data.room.OrderManagerDatabase
import agency.five.codebase.android.ordermanager.data.room.dao.ActiveOrderDao
import agency.five.codebase.android.ordermanager.data.room.dao.MenuItemDao
import agency.five.codebase.android.ordermanager.data.room.dao.OrderedItemDao
import agency.five.codebase.android.ordermanager.data.room.dao.OrderedItemInActiveOrderDao
import agency.five.codebase.android.ordermanager.data.room.dao.StaffDao
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    single<OrderRepository> {
        OrderRepositoryImpl(get(), get(), get(), get(), Dispatchers.IO)
    }
    single<StaffRepository> {
        StaffRepositoryImpl(Dispatchers.IO, get())
    }
    single<EstablishmentRepository> {
        EstablishmentRepositoryImpl(Dispatchers.IO, get())
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

package agency.five.codebase.android.ordermanager.data.di

import agency.five.codebase.android.ordermanager.data.repository.establishment.EstablishmentRepository
import agency.five.codebase.android.ordermanager.data.repository.establishment.EstablishmentRepositoryImpl
import agency.five.codebase.android.ordermanager.data.repository.menuItem.MenuItemRepository
import agency.five.codebase.android.ordermanager.data.repository.menuItem.MenuItemRepositoryImpl
import agency.five.codebase.android.ordermanager.data.repository.order.OrderRepository
import agency.five.codebase.android.ordermanager.data.repository.order.OrderRepositoryImpl
import agency.five.codebase.android.ordermanager.data.repository.staff.StaffRepository
import agency.five.codebase.android.ordermanager.data.repository.staff.StaffRepositoryImpl
import agency.five.codebase.android.ordermanager.data.room.NotConfirmedOrderService
import agency.five.codebase.android.ordermanager.data.room.NotConfirmedOrderServiceImpl
import agency.five.codebase.android.ordermanager.data.room.OrderManagerDatabase
import agency.five.codebase.android.ordermanager.data.room.dao.NotConfirmedOrderItemDao
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    single<OrderRepository> {
        OrderRepositoryImpl(get(), get(), Dispatchers.IO)
    }
    single<StaffRepository> {
        StaffRepositoryImpl(Dispatchers.IO, get())
    }
    single<EstablishmentRepository> {
        EstablishmentRepositoryImpl(Dispatchers.IO, get())
    }
    single<NotConfirmedOrderService> {
        NotConfirmedOrderServiceImpl(get(), Dispatchers.IO)
    }
    single<MenuItemRepository> {
        MenuItemRepositoryImpl(get(), Dispatchers.IO)
    }
    fun provideNotConfirmedOrderItemDao(db: OrderManagerDatabase): NotConfirmedOrderItemDao =
        db.getNotConfirmedOrderItemDao()
    single {
        provideNotConfirmedOrderItemDao(get())
    }
}

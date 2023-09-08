package agency.five.codebase.android.ordermanager.data.di

import agency.five.codebase.android.ordermanager.data.service.establishment.EstablishmentService
import agency.five.codebase.android.ordermanager.data.service.establishment.EstablishmentServiceImpl
import agency.five.codebase.android.ordermanager.data.service.menuItem.MenuItemService
import agency.five.codebase.android.ordermanager.data.service.menuItem.MenuItemServiceImpl
import agency.five.codebase.android.ordermanager.data.service.order.OrderService
import agency.five.codebase.android.ordermanager.data.service.order.OrderServiceImpl
import agency.five.codebase.android.ordermanager.data.service.staff.StaffService
import agency.five.codebase.android.ordermanager.data.service.staff.StaffServiceImpl
import agency.five.codebase.android.ordermanager.data.room.repository.NotConfirmedOrderRepository
import agency.five.codebase.android.ordermanager.data.room.repository.NotConfirmedOrderRepositoryImpl
import agency.five.codebase.android.ordermanager.data.room.OrderManagerDatabase
import agency.five.codebase.android.ordermanager.data.room.dao.NotConfirmedOrderItemDao
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    single<OrderService> {
        OrderServiceImpl(get(), get(), Dispatchers.IO)
    }
    single<StaffService> {
        StaffServiceImpl(Dispatchers.IO, get())
    }
    single<EstablishmentService> {
        EstablishmentServiceImpl(Dispatchers.IO, get())
    }
    single<NotConfirmedOrderRepository> {
        NotConfirmedOrderRepositoryImpl(get(), Dispatchers.IO)
    }
    single<MenuItemService> {
        MenuItemServiceImpl(get(), Dispatchers.IO)
    }
    fun provideNotConfirmedOrderItemDao(db: OrderManagerDatabase): NotConfirmedOrderItemDao =
        db.getNotConfirmedOrderItemDao()
    single {
        provideNotConfirmedOrderItemDao(get())
    }
}

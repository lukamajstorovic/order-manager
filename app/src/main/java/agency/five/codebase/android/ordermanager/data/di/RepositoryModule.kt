package agency.five.codebase.android.ordermanager.data.di

import agency.five.codebase.android.ordermanager.data.firebase.repository.establishment.EstablishmentRepository
import agency.five.codebase.android.ordermanager.data.firebase.repository.establishment.EstablishmentRepositoryImpl
import agency.five.codebase.android.ordermanager.data.firebase.repository.menuitem.MenuItemRepository
import agency.five.codebase.android.ordermanager.data.firebase.repository.menuitem.MenuItemRepositoryImpl
import agency.five.codebase.android.ordermanager.data.firebase.repository.order.OrderRepository
import agency.five.codebase.android.ordermanager.data.firebase.repository.order.OrderRepositoryImpl
import agency.five.codebase.android.ordermanager.data.firebase.repository.staff.StaffRepository
import agency.five.codebase.android.ordermanager.data.firebase.repository.staff.StaffRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<OrderRepository> {
        OrderRepositoryImpl(get())
    }
    single<StaffRepository> {
        StaffRepositoryImpl(get())
    }
    single<EstablishmentRepository> {
        EstablishmentRepositoryImpl(get())
    }
    single<MenuItemRepository> {
        MenuItemRepositoryImpl(get())
    }
}


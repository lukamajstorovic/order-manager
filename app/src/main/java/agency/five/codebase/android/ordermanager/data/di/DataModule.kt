package agency.five.codebase.android.ordermanager.data.di

import agency.five.codebase.android.ordermanager.data.room.*
import agency.five.codebase.android.ordermanager.data.repository.OrderRepository
import agency.five.codebase.android.ordermanager.data.repository.OrderRepositoryImpl
import agency.five.codebase.android.ordermanager.data.repository.StaffRepository
import agency.five.codebase.android.ordermanager.data.repository.StaffRepositoryImpl
import agency.five.codebase.android.ordermanager.data.room.dao.ActiveOrderDao
import agency.five.codebase.android.ordermanager.data.room.dao.MenuItemDao
import agency.five.codebase.android.ordermanager.data.room.dao.OrderedItemDao
import agency.five.codebase.android.ordermanager.data.room.dao.OrderedItemInActiveOrderDao
import agency.five.codebase.android.ordermanager.data.room.dao.StaffDao
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dataModule = module {
    single<OrderRepository> {
        OrderRepositoryImpl(get(), get(), get(), get(), Dispatchers.IO)
    }
    single<StaffRepository> {
        StaffRepositoryImpl(get(), Dispatchers.IO, get())
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
    fun provideStaffDao(db: OrderManagerDatabase): StaffDao =
        db.getStaffDao()
    single {
        provideStaffDao(get())
    }
}

package agency.five.codebase.android.ordermanager.data.di

import agency.five.codebase.android.ordermanager.data.room.OrderManagerDatabase
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val APP_DATABASE_NAME = "app_database.db"
val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            OrderManagerDatabase::class.java,
            APP_DATABASE_NAME,
        ).build()
    }
}

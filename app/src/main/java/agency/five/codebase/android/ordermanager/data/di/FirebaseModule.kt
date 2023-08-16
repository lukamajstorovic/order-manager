package agency.five.codebase.android.ordermanager.data.di

import agency.five.codebase.android.ordermanager.data.firebase.StaffService
import agency.five.codebase.android.ordermanager.data.firebase.StaffServiceImpl
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val firebaseModule=module{
    single<FirebaseFirestore> {
        FirebaseFirestore.getInstance()
    }
    single<StaffService> {
        StaffServiceImpl(get())
    }
}

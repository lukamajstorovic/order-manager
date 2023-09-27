package agency.five.codebase.android.ordermanager.data.di

import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val firebaseModule = module {
    single {
        FirebaseFirestore.getInstance()
    }
}

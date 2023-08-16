package agency.five.codebase.android.ordermanager.utils.module

import org.koin.dsl.module
import java.time.format.DateTimeFormatter

val dateTimeFormatterModule =
    module {
        single {
            DateTimeFormatter
                .ofPattern("EEE yyyy-MM-dd HH:mm:ss")
        }
    }

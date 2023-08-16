package agency.five.codebase.android.ordermanager.utils

import java.time.Instant
import java.time.format.DateTimeFormatter

object DateTimeFormatterUtil {
    private val formatter: DateTimeFormatter = DateTimeFormatter
        .ofPattern("EEE yyyy-MM-dd HH:mm:ss")

    fun formatInstant(instant: Instant): String {
        return formatter.format(instant)
    }
}

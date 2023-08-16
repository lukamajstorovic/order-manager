package agency.five.codebase.android.ordermanager.data.room.dao

import agency.five.codebase.android.ordermanager.data.room.model.DbStaff
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StaffDao {
    @Query("SELECT * FROM staff")
    fun getAllStaff(): Flow<List<DbStaff>>

    @Query("SELECT * FROM staff WHERE id=:id")
    fun getStaffById(id: Long) : Flow<DbStaff>

    @Query("SELECT * FROM staff WHERE username=:username AND password=:password")
    fun getStaffByCredentials(username: String, password: String) : DbStaff?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStaff(staff: DbStaff)

    @Query("DELETE FROM staff WHERE id=:id")
    fun deleteStaff(id: Long)
}

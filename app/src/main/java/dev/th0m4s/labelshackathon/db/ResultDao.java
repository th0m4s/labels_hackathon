package dev.th0m4s.labelshackathon.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

@Dao()
public interface ResultDao {
    @Query("SELECT * FROM results")
    List<APIResult> getAll();

    @Query("SELECT COUNT(*) FROM results")
    int getCount();

    @Query("SELECT * FROM results ORDER BY last_scanned DESC LIMIT :pos, 1")
    APIResult getSortedIndex(int pos);

    @Query("SELECT * FROM results WHERE code = :code")
    APIResult findByCode(String code);

    @Insert
    void insertAll(APIResult... results);

    @Delete
    void delete(APIResult result);

    @Query("UPDATE results SET last_scanned = :lastScanned WHERE code = :code")
    int updateLastScan(String code, Date lastScanned);
}

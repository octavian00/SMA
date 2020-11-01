package com.example.tema1.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE username LIKE :first LIMIT 1")
    User findByName(String first);

//    @Query("UPDATE User SET username=user.username, password=user.password, emailAdress=user.emailAdress, age=user.age WHERE id=user.id")
//    void update(User user);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}

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

    @Query("UPDATE User SET username = :username, password=:password, emailadress=:emailAdress, age=:age WHERE username = :username")
    void update(String username,String password, String emailAdress, int age);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}

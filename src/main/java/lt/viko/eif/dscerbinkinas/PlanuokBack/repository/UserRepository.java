package lt.viko.eif.dscerbinkinas.PlanuokBack.repository;


import lt.viko.eif.dscerbinkinas.PlanuokBack.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface UserRepository {

    @Insert("""
            INSERT INTO users(username, password, email, created_at)
            VALUES(#{username}, #{password}, #{email}, #{creationDate})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addUser(User user);


    @Select(""" 
        SELECT * FROM users
    """)
    List<User> getAllUsers();



    @Update("""
        UPDATE users
        SET username = #{username},
            password = #{password},
            email = #{email}
        """)
    void updateUser(User user);

    @Select("""
        SELECT * FROM users WHERE id= #{id}
    """)
    Optional<User> getUserById(@Param("id") Long id);


    @Select("""
        SELECT * FROM users WHERE username= #{username}
    """)
    Optional<User> getUserByUsername(@Param("username") String username);


    @Select("""
        SELECT * FROM users WHERE email= #{email}
    """)
    Optional<User> getUserByEmail(@Param("email") String email);


}

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


//    @Update("""
//        UPDATE food_place
//        SET name = #{name},
//            location = #{location},
//            description = #{description},
//            date_modified = #{dateModified}
//        WHERE id = #{id}
//        """)


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




}

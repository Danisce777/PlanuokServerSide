package lt.viko.eif.dscerbinkinas.PlanuokBack.repository;


import lt.viko.eif.dscerbinkinas.PlanuokBack.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserRepository {

    @Insert("""
            INSERT INTO users(username, password, email, created_at)
            VALUES(#{username}, #{password}, #{email}, #{creationDate})
            """)
    void addUser(User user);




}

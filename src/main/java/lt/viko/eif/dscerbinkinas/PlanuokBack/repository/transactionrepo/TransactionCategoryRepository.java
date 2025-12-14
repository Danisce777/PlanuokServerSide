package lt.viko.eif.dscerbinkinas.PlanuokBack.repository.transactionrepo;

import lt.viko.eif.dscerbinkinas.PlanuokBack.model.Transaction;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.TransactionCategory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface TransactionCategoryRepository {

    @Select("""
        SELECT * FROM categories 
        WHERE (is_default = TRUE OR user_id = #{userId})
        AND type = #{type}
        ORDER BY is_default DESC, name ASC
    """)
    List<TransactionCategory> findCategoriesByUserAndType(@Param("userId") Long userId,
                                               @Param("type") String type);
    @Select("""
        SELECT * FROM categories WHERE id = #{id}
    """)
    Optional<TransactionCategory> findById(Long id);

    @Insert("""
        INSERT INTO categories (name, type, is_default, user_id, created_at)
        VALUES (#{name}, #{type}, #{isDefault}, #{userId}, #{createdAt})
    """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createCategory(TransactionCategory category);

    @Select("""
        SELECT EXISTS(
            SELECT 1 FROM categories 
            WHERE name = #{name} 
            AND user_id = #{userId}
        )
    """)
    boolean existsByNameAndUserId(@Param("name") String name,
                                  @Param("userId") Long userId);

    @Delete("""
        DELETE FROM categories 
        WHERE id = #{id} AND user_id = #{userId} AND is_default = FALSE
    """)
    void deleteCustomCategory(@Param("id") Long id, @Param("userId") Long userId);

}

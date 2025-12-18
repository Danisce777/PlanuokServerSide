package lt.viko.eif.dscerbinkinas.PlanuokBack.repository.transactionrepo;

import lt.viko.eif.dscerbinkinas.PlanuokBack.model.Transaction;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface TransactionRepository {

    @Insert("""
        INSERT INTO transactions (description, creator_id, amount, creation_date, transaction_date, transaction_type, category_id, title)
        VALUES(#{description}, #{creator.id}, #{amount}, #{creationDate}, #{occurredDate}, #{transactionType}, #{category.id}, #{title})
    """)
    @Options(useGeneratedKeys = true, keyProperty = "transactionId")
    void addTransaction(Transaction transaction);

    @Select("""
        SELECT 
            t.transaction_id,
            t.description,
            t.amount,
            t.creation_date,
            t.transaction_type,
            t.transaction_date,
            t.title,
            c.id as category_id,
            c.name as category_name,
            c.type as category_type,
            c.is_default as category_is_default,
            c.user_id as category_user_id,
            c.created_at as category_created_at,
            u.id as creator_id,
            u.username as creator_username,
            u.email as creator_email,
            u.password as creator_password,
            u.created_at as creator_created_at
        FROM transactions t
        INNER JOIN users u ON t.creator_id = u.id
        LEFT JOIN categories c ON t.category_id = c.id
        WHERE t.creator_id = #{creatorId}
        ORDER BY t.creation_date DESC
    """)
    @Results(id = "TransactionResultMap", value = {
            @Result(property = "transactionId", column = "transaction_id"),
            @Result(property = "description", column = "description"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "creationDate", column = "creation_date"),
            @Result(property = "transactionType", column = "transaction_type"),
            @Result(property = "occurredDate", column = "transaction_date"),
            @Result(property = "title", column = "title"),
            @Result(property = "category.id", column = "category_id"),
            @Result(property = "category.name", column = "category_name"),
            @Result(property = "category.type", column = "category_type"),
            @Result(property = "category.isDefault", column = "category_is_default"),
            @Result(property = "category.userId", column = "category_user_id"),
            @Result(property = "category.createdAt", column = "category_created_at"),
            @Result(property = "creator.id", column = "creator_id"),
            @Result(property = "creator.username", column = "creator_username"),
            @Result(property = "creator.email", column = "creator_email"),
            @Result(property = "creator.password", column = "creator_password"),
            @Result(property = "creator.creationDate", column = "creator_created_at")
    })
    List<Transaction> findTransactionsByCreatorId(Long creatorId);

    @Select("""
        SELECT 
            t.transaction_id,
            t.description,
            t.amount,
            t.creation_date,
            t.transaction_type,
            t.transaction_date,
            t.title,
            c.id as category_id,
            c.name as category_name,
            c.type as category_type,
            c.is_default as category_is_default,
            c.user_id as category_user_id,
            c.created_at as category_created_at,
            u.id as creator_id,
            u.username as creator_username,
            u.email as creator_email,
            u.password as creator_password,
            u.created_at as creator_created_at
        FROM transactions t
        INNER JOIN users u ON t.creator_id = u.id
        LEFT JOIN categories c ON t.category_id = c.id
        WHERE t.transaction_id = #{transactionId}
    """)
    @ResultMap("TransactionResultMap")
    Optional<Transaction> findTransactionsById(Long transactionId);

    @Update("""
        UPDATE transactions
        SET 
            description = #{description},
            amount = #{amount},
            transaction_type = #{transactionType},
            category_id = #{category.id},
            transaction_date = #{occurredDate},
            title = #{title}
    WHERE transaction_id = #{transactionId}
    """)
    void updateTransaction(Transaction transaction);

    @Delete("""
       DELETE FROM transactions WHERE transaction_id = #{transactionId}
    """)
    void deleteTransaction(Long transactionId);
}

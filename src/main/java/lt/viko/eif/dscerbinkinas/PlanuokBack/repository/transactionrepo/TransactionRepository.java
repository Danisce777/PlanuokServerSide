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
        INSERT INTO transactions (description, creator_id, amount, creation_date, transaction_type, transaction_category)
        VALUES(#{description}, #{creator.id}, #{amount}, #{creationDate}, #{transactionType}, #{transactionCategory})
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
            t.transaction_category,
            u.id as creator_id,
            u.username as creator_username,
            u.email as creator_email,
            u.password as creator_password,
            u.created_at as creator_created_at
        FROM transactions t
        INNER JOIN users u ON t.creator_id = u.id
        WHERE t.creator_id = #{creatorId}
    """)
    @Results(id = "TransactionResultMap", value = {
            @Result(property = "transactionId", column = "transaction_id"),
            @Result(property = "description", column = "description"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "creationDate", column = "creation_date"),
            @Result(property = "transactionType", column = "transaction_type"),
            @Result(property = "transactionCategory", column = "transaction_category"),
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
            t.transaction_category,
            u.id as creator_id,
            u.username as creator_username,
            u.email as creator_email,
            u.password as creator_password,
            u.created_at as creator_created_at
        FROM transactions t
        INNER JOIN users u ON t.creator_id = u.id
        WHERE t.transaction_id = #{transactionId}
    """)
    @ResultMap("TransactionResultMap")
    Optional<Transaction> findTransactionsById(Long transactionId);


    @Update("""
        UPDATE transactions
        SET 
            description = #{description},
            amount = #{amount},
            creation_date = #{creationDate},
            transaction_type = #{transactionType},
            transaction_category = #{transactionCategory}
    WHERE transaction_id = #{transactionId}
    """)
    void updateTransaction(Transaction transaction);

    @Delete("""
       DELETE FROM transactions WHERE transaction_id = #{transactionId}
    """)
    void deleteTransaction(Long transactionId);
}

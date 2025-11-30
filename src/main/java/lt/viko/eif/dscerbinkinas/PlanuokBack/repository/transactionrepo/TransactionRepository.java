package lt.viko.eif.dscerbinkinas.PlanuokBack.repository.transactionrepo;

import lt.viko.eif.dscerbinkinas.PlanuokBack.model.Transaction;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Repository
@Mapper
public interface TransactionRepository {


//            transaction_id BIGSERIAL PRIMARY KEY,
//            description TEXT,
//            creator_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
//    amount NUMERIC(15,2) NOT NULL,
//    creation_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
//    transaction_type VARCHAR(20) NOT NULL CHECK (transaction_type IN ('expense', 'income')),
//    transaction_category VARCHAR(50)

//    private Long transactionId;
//    private BigDecimal amount;
//    private User creator;
//    private String description;
//    private OffsetDateTime creationDate;
//    private Transaction.TransactionType transactionType;
//    private String transactionCategory;

    @Insert("""
        INSERT INTO transactions (description, creator_id, amount, creation_date, transaction_type, transaction_category)
        VALUES(#{description}, #{creator.id}, #{amount}, #{creationDate}, #{transactionType}, #{transactionCategory})
    """)
    @Options(useGeneratedKeys = true, keyProperty = "transactionId")
    void addTransaction(Transaction transaction);


    @Select("""
        SELECT * FROM transactions
    """)
    List<Transaction> getAllTransactions();




}

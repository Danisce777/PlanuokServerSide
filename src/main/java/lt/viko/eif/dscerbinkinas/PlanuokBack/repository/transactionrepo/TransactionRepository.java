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

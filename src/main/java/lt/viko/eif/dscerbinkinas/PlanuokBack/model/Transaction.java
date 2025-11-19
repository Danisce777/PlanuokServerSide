package lt.viko.eif.dscerbinkinas.PlanuokBack.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    private Long transactionId;
    private BigDecimal amount;
    private User creator;
    private String description;
    private OffsetDateTime creationDate;
    private TransactionType transactionType;
    private String transactionCategory;

    public enum TransactionType {
        expense,
        income
    }



}

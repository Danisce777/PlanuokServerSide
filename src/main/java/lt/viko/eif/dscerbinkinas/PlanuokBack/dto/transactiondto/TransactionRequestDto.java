package lt.viko.eif.dscerbinkinas.PlanuokBack.dto.transactiondto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.Transaction;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.User;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Value
@Builder
public class TransactionRequestDto {

    @NotBlank
    BigDecimal amount;
    @NotBlank
    User creator;
    @NotBlank
    String description;
    @NotBlank
    OffsetDateTime creationDate;
    @NotBlank
    Transaction.TransactionType transactionType;
    @NotBlank
    String transactionCategory;

}

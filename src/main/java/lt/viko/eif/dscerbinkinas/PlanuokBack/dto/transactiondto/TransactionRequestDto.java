package lt.viko.eif.dscerbinkinas.PlanuokBack.dto.transactiondto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.Transaction;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.User;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@Builder
public class TransactionRequestDto {

    BigDecimal amount;
    String description;
    Transaction.TransactionType transactionType;
    String transactionCategory;
}

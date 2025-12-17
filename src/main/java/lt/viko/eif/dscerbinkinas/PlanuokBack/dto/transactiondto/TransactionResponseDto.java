package lt.viko.eif.dscerbinkinas.PlanuokBack.dto.transactiondto;

import lombok.Builder;
import lombok.Value;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.UserResponseDto;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.Transaction;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.TransactionCategory;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.User;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Value
@Builder
public class TransactionResponseDto {

     Long transactionId;
     BigDecimal amount;
     User creator;
     String description;
     OffsetDateTime creationDate;
     Transaction.TransactionType transactionType;
     TransactionCategory category;
     OffsetDateTime occurredDate;

     // Mapper
     public static TransactionResponseDto fromTransaction(Transaction transaction) {
         return TransactionResponseDto.builder()
                 .transactionId(transaction.getTransactionId())
                 .amount(transaction.getAmount())
                 .creator(transaction.getCreator())
                 .description(transaction.getDescription())
                 .creationDate(transaction.getCreationDate())
                 .transactionType(transaction.getTransactionType())
                 .category(transaction.getCategory())
                 .occurredDate(transaction.getOccurredDate())
                 .build();
     }



}

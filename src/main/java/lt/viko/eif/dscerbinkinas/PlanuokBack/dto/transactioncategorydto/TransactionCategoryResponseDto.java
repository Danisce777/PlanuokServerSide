package lt.viko.eif.dscerbinkinas.PlanuokBack.dto.transactioncategorydto;

import lombok.Builder;
import lombok.Value;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.Transaction;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.TransactionCategory;

@Value
@Builder
public class TransactionCategoryResponseDto {
    Long id;
    String name;
    Transaction.TransactionType type;
    Boolean isDefault;

    public static TransactionCategoryResponseDto fromCategory(TransactionCategory category) {
        return TransactionCategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .type(category.getType())
                .isDefault(category.getIsDefault())
                .build();
    }
}

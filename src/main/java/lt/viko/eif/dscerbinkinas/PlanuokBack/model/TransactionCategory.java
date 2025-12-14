package lt.viko.eif.dscerbinkinas.PlanuokBack.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCategory {

    private Long id;
    private String name;
    private Transaction.TransactionType type;
    private Boolean isDefault;
    private Long userId;
    private OffsetDateTime createdAt;

}

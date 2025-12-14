package lt.viko.eif.dscerbinkinas.PlanuokBack.dto.transactioncategorydto;

import lombok.Data;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.Transaction;

@Data
public class TransactionCategoryRequestDto {
    private String name;
    private Transaction.TransactionType type;
}

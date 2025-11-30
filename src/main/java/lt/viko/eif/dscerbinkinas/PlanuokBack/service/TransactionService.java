package lt.viko.eif.dscerbinkinas.PlanuokBack.service;

import lombok.AllArgsConstructor;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.UserRequestDto;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.UserResponseDto;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.transactiondto.TransactionRequestDto;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.transactiondto.TransactionResponseDto;
import lt.viko.eif.dscerbinkinas.PlanuokBack.jwt.AuthService;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.Transaction;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.User;
import lt.viko.eif.dscerbinkinas.PlanuokBack.repository.transactionrepo.TransactionRepository;
import lt.viko.eif.dscerbinkinas.PlanuokBack.utils.JwtUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionResponseDto addTransaction(TransactionRequestDto request, User authenticatedUser) {

        Transaction transaction = Transaction.builder()
                        .amount(request.getAmount())
                        .creator(authenticatedUser)
                        .description(request.getDescription())
                        .creationDate(OffsetDateTime.now())
                        .transactionType(request.getTransactionType())
                        .transactionCategory(request.getTransactionCategory())
                        .build();

        transactionRepository.addTransaction(transaction);
        return TransactionResponseDto.fromTransaction(transaction);
    }

    public List<TransactionResponseDto> getAllTransactions() {

        return transactionRepository.getAllTransactions().stream().map(TransactionResponseDto::fromTransaction).collect(Collectors.toList());
    }

}

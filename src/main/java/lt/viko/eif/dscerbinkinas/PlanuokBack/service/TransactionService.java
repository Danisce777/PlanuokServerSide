package lt.viko.eif.dscerbinkinas.PlanuokBack.service;

import lombok.AllArgsConstructor;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.transactiondto.TransactionRequestDto;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.transactiondto.TransactionResponseDto;
import lt.viko.eif.dscerbinkinas.PlanuokBack.exception.CategoryNotFoundException;
import lt.viko.eif.dscerbinkinas.PlanuokBack.exception.TransactionNotFoundException;
import lt.viko.eif.dscerbinkinas.PlanuokBack.exception.UnauthorizedException;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.Transaction;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.TransactionCategory;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.User;
import lt.viko.eif.dscerbinkinas.PlanuokBack.repository.transactionrepo.TransactionCategoryRepository;
import lt.viko.eif.dscerbinkinas.PlanuokBack.repository.transactionrepo.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionCategoryRepository categoryRepository;

    @Transactional
    public TransactionResponseDto addTransaction(TransactionRequestDto request, User authenticatedUser) {


        TransactionCategory category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        if (!category.getIsDefault() && !category.getUserId().equals(authenticatedUser.getId())) {
            throw new IllegalArgumentException("Cannot use another user's custom category");
        }

        if (!category.getType().equals(request.getTransactionType())) {
            throw new IllegalArgumentException(
                    "Category type (" + category.getType() + ") does not match transaction type ("
                            + request.getTransactionType() + ")"
            );
        }

        Transaction transaction = Transaction.builder()
                        .amount(request.getAmount())
                        .creator(authenticatedUser)
                        .description(request.getDescription())
                        .creationDate(OffsetDateTime.now())
                        .transactionType(request.getTransactionType())
                        .category(category)
                        .build();

        transactionRepository.addTransaction(transaction);
        return TransactionResponseDto.fromTransaction(transaction);
    }

    public List<TransactionResponseDto> findTransactionsByCreatorId(Long userId) {
        return transactionRepository.findTransactionsByCreatorId(userId)
                .stream()
                .map(TransactionResponseDto::fromTransaction)
                .collect(Collectors.toList());
    }

    public TransactionResponseDto findTransactionById(Long id) {
        Transaction transaction = transactionRepository.findTransactionsById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with id: " + id));

        return TransactionResponseDto.fromTransaction(transaction);
    }


    public TransactionResponseDto updateTransaction(TransactionRequestDto request, Long id, User authenticatedUser) {

        Transaction transaction = transactionRepository.findTransactionsById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with id: " + id));

        if(!transaction.getCreator().getId().equals(authenticatedUser.getId())) {
            throw new UnauthorizedException("You are not authorized to update this transaction");
        }

        TransactionCategory category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + request.getCategoryId()));


        if (!category.getIsDefault() && !category.getUserId().equals(authenticatedUser.getId())) {
            throw new UnauthorizedException("Cannot use another user's custom category");
        }

        if (!category.getType().equals(request.getTransactionType())) {
            throw new IllegalArgumentException(
                    "Category type (" + category.getType() + ") does not match transaction type ("
                            + request.getTransactionType() + ")"
            );
        }

        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription());
        transaction.setTransactionType(request.getTransactionType());
        transaction.setCategory(category);

        transactionRepository.updateTransaction(transaction);
        return TransactionResponseDto.fromTransaction(transaction);
    }

    public void deleteTransaction(Long id, User authenticatedUser) {

        Transaction transaction = transactionRepository.findTransactionsById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with id: " + id));

        if(!transaction.getCreator().getId().equals(authenticatedUser.getId())) {
            throw new UnauthorizedException("You are not authorized to delete this transaction");
        }

        transactionRepository.deleteTransaction(id);
    }
}
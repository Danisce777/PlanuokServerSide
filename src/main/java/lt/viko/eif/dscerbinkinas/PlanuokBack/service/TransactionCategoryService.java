package lt.viko.eif.dscerbinkinas.PlanuokBack.service;

import lombok.RequiredArgsConstructor;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.transactioncategorydto.TransactionCategoryRequestDto;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.transactioncategorydto.TransactionCategoryResponseDto;
import lt.viko.eif.dscerbinkinas.PlanuokBack.exception.CategoryAlreadyExistsException;
import lt.viko.eif.dscerbinkinas.PlanuokBack.exception.CategoryNotFoundException;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.Transaction;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.TransactionCategory;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.User;
import lt.viko.eif.dscerbinkinas.PlanuokBack.repository.transactionrepo.TransactionCategoryRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionCategoryService {

    private final TransactionCategoryRepository transactionCategoryRepository;

    public List<TransactionCategoryResponseDto> findCategoriesByUserAndType(Long userId, Transaction.TransactionType type) {
        return transactionCategoryRepository.findCategoriesByUserAndType(userId, type.name())
                .stream()
                .map(TransactionCategoryResponseDto::fromCategory)
                .toList();
    }

    public TransactionCategoryResponseDto createCategory(TransactionCategoryRequestDto request, User user) {

        if (transactionCategoryRepository.existsByNameAndUserId(request.getName(), user.getId())) {
            throw new CategoryAlreadyExistsException("Category already exists: " + request.getName());
        }

        TransactionCategory category = TransactionCategory.builder()
                .name(request.getName())
                .type(request.getType())
                .isDefault(false)
                .userId(user.getId())
                .createdAt(OffsetDateTime.now())
                .build();

        transactionCategoryRepository.createCategory(category);
        return TransactionCategoryResponseDto.fromCategory(category);
    }

    public void deleteCategory(Long categoryId, User user) {

        TransactionCategory category = transactionCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));

        if (category.getIsDefault()) {
            throw new IllegalArgumentException("Cannot delete default categories");
        }

        if (!category.getUserId().equals(user.getId())) {
            throw new IllegalArgumentException("Cannot delete another user's category");
        }

        transactionCategoryRepository.deleteCustomCategory(categoryId, user.getId());
    }

}
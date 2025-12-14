package lt.viko.eif.dscerbinkinas.PlanuokBack.controller;

import lombok.RequiredArgsConstructor;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.transactioncategorydto.TransactionCategoryRequestDto;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.transactioncategorydto.TransactionCategoryResponseDto;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.Transaction;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.User;
import lt.viko.eif.dscerbinkinas.PlanuokBack.service.TransactionCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class TransactionCategoryController {

    private final TransactionCategoryService transactionCategoryService;

    @GetMapping
    public List<TransactionCategoryResponseDto> findCategoriesByUserAndType(@RequestParam Transaction.TransactionType type, @AuthenticationPrincipal User user) {
        return transactionCategoryService.findCategoriesByUserAndType(user.getId(), type);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionCategoryResponseDto createCategory (@RequestBody TransactionCategoryRequestDto request, @AuthenticationPrincipal User user) {
        return transactionCategoryService.createCategory(request, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id, @AuthenticationPrincipal User user) {
        transactionCategoryService.deleteCategory(id, user);
    }


}

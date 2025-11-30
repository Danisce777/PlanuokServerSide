package lt.viko.eif.dscerbinkinas.PlanuokBack.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.UserRequestDto;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.UserResponseDto;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.transactiondto.TransactionRequestDto;
import lt.viko.eif.dscerbinkinas.PlanuokBack.dto.transactiondto.TransactionResponseDto;
import lt.viko.eif.dscerbinkinas.PlanuokBack.model.User;
import lt.viko.eif.dscerbinkinas.PlanuokBack.repository.transactionrepo.TransactionRepository;
import lt.viko.eif.dscerbinkinas.PlanuokBack.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponseDto addTransaction(@RequestBody TransactionRequestDto request, @AuthenticationPrincipal User user) {


        return transactionService.addTransaction(request, user);

    }

    @GetMapping
    public List<TransactionResponseDto> getAllTransactions() {
        return transactionService.getAllTransactions();
    }



}

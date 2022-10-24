package com.credibanco.assessment.card.controller;

import com.credibanco.assessment.card.dto.*;
import com.credibanco.assessment.card.repository.ITransactionRepository;
import com.credibanco.assessment.card.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    private ITransactionRepository tRepo;
    final
    TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/create")
    public TransactionDtoCreateResponse createTransaction(@Valid @RequestBody TransactionDto transactionDto){
        return transactionService.createTransaction(transactionDto);
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/cancel")
    public TransactionDtoCancelResponse cancelTransaction(@Valid @RequestBody TransactionDtoCancelRequest transactionDtoCancel){
        return transactionService.cancelTransaction(transactionDtoCancel);
    }

    @GetMapping("/time")
    public LocalDateTime currentTime(){
        return LocalDateTime.now();
    }
}

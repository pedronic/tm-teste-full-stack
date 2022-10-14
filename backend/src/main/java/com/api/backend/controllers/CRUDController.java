package com.api.backend.controllers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.backend.configs.security.UserDetailsServiceImpl;
import com.api.backend.dtos.AccountDto;
import com.api.backend.dtos.FeeDto;
import com.api.backend.dtos.SimulationDto;
import com.api.backend.dtos.TransactionDto;
import com.api.backend.models.AccountModel;
import com.api.backend.models.FeeModel;
import com.api.backend.models.TransactionModel;
import com.api.backend.models.UserModel;
import com.api.backend.services.AccountService;
import com.api.backend.services.FeeService;
import com.api.backend.services.RoleService;
import com.api.backend.services.TransactionService;

@RestController
// @CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/")
public class CRUDController {

    @Autowired
    public final UserDetailsServiceImpl userService;
    @Autowired
    public final AccountService accountService;
    @Autowired
    public final RoleService roleService;
    @Autowired
    public final TransactionService transactionService;
    @Autowired
    public final FeeService feeService;

    @Autowired
    public CRUDController(UserDetailsServiceImpl userService, AccountService accountService, RoleService roleService,
            TransactionService transactionService, FeeService feeService) {
        this.userService = userService;
        this.accountService = accountService;
        this.roleService = roleService;
        this.transactionService = transactionService;
        this.feeService = feeService;
    }

    /**
     * Use this method for creating and associating new accounts with existing
     * users.
     * 
     * @param username
     */
    @PostMapping("accounts/new")
    public ResponseEntity<AccountModel> setNewAccount(@RequestBody AccountDto username) {
        if (!userService.existsByUsername(username.getUsername())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var _account_n = accountService.max();
        _account_n += 1;
        AccountModel _newAccount = new AccountModel(_account_n, username.getValue());
        _newAccount.setUser(userService.findByUsername(username.getUsername()).get());
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.save(_newAccount));
    }

    /**
     * Use this method for creating fees and populating the database.
     * 
     * @param feeDto
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("fee/new")
    public ResponseEntity<String> setNewFee(@RequestBody @Valid FeeDto feeDto) {
        if (!feeService.findByType(feeDto.getType()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Fee already registered:\n" + feeService.findByType(feeDto.getType()).get().toString());
        }
        FeeModel _new_fee = new FeeModel(feeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(feeService.getFeeRepository().save(_new_fee).toString());
    }

    /**
     * Use this method for retrieving all Accounts from database
     * 
     * @param pageable
     * @return All Accounts
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("accounts")
    public ResponseEntity<Page<AccountModel>> getAllAccounts(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountRepository().findAll(pageable));
    }

    /**
     * Use this method to retrieve all Accounts from the specified User
     * 
     * @param username
     * @return All accounts from user
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("accounts/{username}")
    public ResponseEntity<List<AccountModel>> getAccountsFromUser(@PathVariable(value = "username") String username) {
        UserModel _user = userService.findByUsername(username).get();
        Optional<List<AccountModel>> accountModel = accountService.findByUser(_user);
        if (!accountModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(accountModel.get());
    }

    /**
     * Use this method to retrieve all Transactions from the specified User
     * 
     * @param username
     * @return All transactions from user
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("transactions/{username}")
    public ResponseEntity<List<TransactionModel>> getTransactionsByUsername(
            @PathVariable(value = "username") String username) {
        Optional<UserModel> _user = userService.findByUsername(username);
        if (!_user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Optional<List<TransactionModel>> transactionModel = transactionService.findByUser(_user.get());
        if (!transactionModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(transactionModel.get());
    }

    /**
     * Use this method to Schedule a new Transaction
     * 
     * @param username
     * @return JSON with the scheduled transaction
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("transactions/new")
    public ResponseEntity<String> scheduleNewTransaction(@RequestBody TransactionDto transactionDto) {
        if (userService.findByUsername(transactionDto.getUsername()).get().getUserId() != accountService
                .findByAccountNumber(transactionDto.getAccountFrom()).get().getUser().getUserId()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Username and Account Number From doesn't match!");
        }

        TransactionModel _newTransaction = new TransactionModel();
        _newTransaction.setUser(userService.findByUsername(transactionDto.getUsername()).get());
        var _val_fee_check = feeService.findByValueMinLessThanAndValueMaxGreaterThanEqual(transactionDto.getValue());

        if (!_val_fee_check.isPresent()) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                    .body("No Fee found for specified Transaction Value.");
        }

        var interval = ChronoUnit.DAYS.between(_newTransaction.getDateCreated(), transactionDto.getDateScheduled());
        var _interval_fee_check = feeService.findByIntervalMinLessThanAndIntervalMaxGreaterThanEqual((int) interval);

        if (!_interval_fee_check.isPresent()) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                    .body("Schedule Interval not allowed for this Transaction Value!");
        }

        if (!_val_fee_check.get().contains(_interval_fee_check.get())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Unnexpected conflict in the database detected. Please try again in a few moments!");
        }

        BigDecimal _fee_value = transactionDto.getValue().multiply(_interval_fee_check.get().getFee_rate());
        _fee_value = _fee_value.add(_interval_fee_check.get().getFee_value());

        _newTransaction.setFee_value(_fee_value);
        _newTransaction.setFee(_interval_fee_check.get());
        _newTransaction.setValue(transactionDto.getValue().add(_fee_value));
        _newTransaction.setAccountFrom(transactionDto.getAccountFrom());
        _newTransaction.setAccountTo(transactionDto.getAccountTo());
        _newTransaction.setDateScheduled(transactionDto.getDateScheduled());

        var _valid_transaction = _newTransaction.getValue()
                .compareTo(accountService.findByAccountNumber(transactionDto.getAccountFrom()).get().getValue()) <= 0;
        if (!_valid_transaction) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    "{\n \"valid\": " + Boolean.toString(_valid_transaction) + ",\n \"data\":\n\t " + transactionService.getTransactionRepository().save(_newTransaction).toString() + "\n}");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body("{\n \"valid\": " + Boolean.toString(_valid_transaction) + ",\n \"data\":\n\t " + transactionService.getTransactionRepository().save(_newTransaction).toString() + "\n}");
    }

    /**
     * Use this method to Simulate a new Transaction
     * 
     * @param username
     * @return JSON with the simulated transaction informations and validity
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("transactions/sim")
    public ResponseEntity<String> simulateNewTransaction(@RequestBody TransactionDto transactionDto) {
        if (userService.findByUsername(transactionDto.getUsername()).get().getUserId() != accountService
                .findByAccountNumber(transactionDto.getAccountFrom()).get().getUser().getUserId()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Username and Account Number From doesn't match!");
        }
        var _val_fee_check = feeService.findByValueMinLessThanAndValueMaxGreaterThanEqual(transactionDto.getValue());
        if (!_val_fee_check.isPresent()) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                    .body("No Fee found for specified Transaction Value.");
        }
        var interval = ChronoUnit.DAYS.between(LocalDateTime.now(), transactionDto.getDateScheduled());
        var _interval_fee_check = feeService.findByIntervalMinLessThanAndIntervalMaxGreaterThanEqual((int) interval);
        if (!_interval_fee_check.isPresent()) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body("Schedule Interval not allowed for this Transaction Value!");
        }
        if (!_val_fee_check.get().contains(_interval_fee_check.get())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Unexpected conflict in the database detected. Please try again in a few moments!");
        }
        SimulationDto sim = new SimulationDto();
        sim.setFee_rate(_interval_fee_check.get().getFee_rate());
        sim.setFee_value(_interval_fee_check.get().getFee_value());
        BigDecimal _fee_value = transactionDto.getValue().multiply(_interval_fee_check.get().getFee_rate());
        sim.setFee_total(_fee_value.add(_interval_fee_check.get().getFee_value()));
        sim.setSubtotal(transactionDto.getValue().add(_fee_value.add(_interval_fee_check.get().getFee_value())));
        sim.setType(_interval_fee_check.get().getType());
        var _valid_transaction = sim.getSubtotal()
                .compareTo(accountService.findByAccountNumber(transactionDto.getAccountFrom()).get().getValue()) <= 0;
        if (!_valid_transaction) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    "{\n \"valid\": " + Boolean.toString(_valid_transaction) + ",\n \"data\":\n\t " + sim.toString() + "\n}");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body("{\n \"valid\": " + Boolean.toString(_valid_transaction) + ",\n \"data\":\n\t " + sim.toString() + "\n}");
    }

}

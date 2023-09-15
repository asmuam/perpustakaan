/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.perpustakaan.controller;

/**
 *
 * @author asmua
 */
import com.polstat.perpustakaan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.polstat.perpustakaan.entity.Loan;
import com.polstat.perpustakaan.rpc.BorrowRequest;
import com.polstat.perpustakaan.rpc.ReturnRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(@RequestBody BorrowRequest request) {
        try {
            Loan loan = loanService.borrowBook(request.getMemberID(), request.getBookID(), request.getStartDate(), request.getDueDate());
            return ResponseEntity.ok(loan);
        } catch (Exception e) {
            String errString = e.getMessage();
            return ResponseEntity.badRequest().body(errString);
        }
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestBody ReturnRequest request) {
        try {
            int lateDays = loanService.returnBook(request.getMemberID(), request.getBookID(), request.getReturnDate());
            return ResponseEntity.ok("Book has been returned. Late days: " + lateDays);
        } catch (Exception e) {
            String errString = e.getMessage();
            return ResponseEntity.badRequest().body(errString);
        }
    }

}

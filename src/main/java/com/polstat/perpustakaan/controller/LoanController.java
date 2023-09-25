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
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/loan/{id}")
    public ResponseEntity<?> getLoan(@PathVariable Long id) {
        try {
            Loan loan = loanService.getLoanById(id);

            if (loan == null) {
                return ResponseEntity.notFound().build();
            }

            // Mengambil ID member dari objek Loan
            Long memberId = loan.getMember().getId();

            // Membuat tautan HATEOAS ke diri sendiri (detail peminjaman)
            Link selfLink = WebMvcLinkBuilder.linkTo(LoanController.class).slash("loan").slash(id).withSelfRel();

            // Mengonversi objek Loan dan ID member ke dalam respons JSON
            Map<String, Object> response = new HashMap<>();
            response.put("loan", loan);
            response.put("memberId", memberId);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            String errString = e.getMessage();
            return ResponseEntity.badRequest().body(errString);
        }
    }

    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(@RequestBody BorrowRequest request) {
        try {
            Loan loan = loanService.borrowBook(request.getMemberID(), request.getBookID(), request.getStartDate(), request.getDueDate());

            // Membuat tautan HATEOAS ke detail peminjaman
            Link selfLink = WebMvcLinkBuilder.linkTo(LoanController.class).slash("loan").slash(loan.getId()).withSelfRel();

            // Mengonversi objek Loan ke EntityModel dan menambahkan tautan HATEOAS
            EntityModel<Loan> loanModel = EntityModel.of(loan, selfLink);

            return ResponseEntity.ok(loanModel);
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

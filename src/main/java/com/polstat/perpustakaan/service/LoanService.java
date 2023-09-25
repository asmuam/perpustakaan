/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.perpustakaan.service;

import com.polstat.perpustakaan.entity.Loan;
import java.time.LocalDate;

/**
 *
 * @author asmua
 */
public interface LoanService {

    /**
     *
     * @param memberID
     * @param bookID
     * @param startDate
     * @param dueDate
     * @return
     */
    public Loan borrowBook(String memberID, Long bookID, LocalDate startDate, LocalDate dueDate);
    public int returnBook(String memberID, Long bookID, LocalDate returnDate);
    public Loan getLoanById(Long loanId);
}

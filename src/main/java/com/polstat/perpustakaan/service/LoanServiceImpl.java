/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.perpustakaan.service;

import com.polstat.perpustakaan.entity.Book;
import com.polstat.perpustakaan.entity.Loan;
import com.polstat.perpustakaan.entity.Loan.LoanStatus;
import com.polstat.perpustakaan.entity.Member;
import com.polstat.perpustakaan.repository.BookRepository;
import com.polstat.perpustakaan.repository.LoanRepository;
import com.polstat.perpustakaan.repository.MemberRepository;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author asmua
 */
@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Loan borrowBook(String memberId, Long bookID, LocalDate startDate, LocalDate dueDate) {
        // Retrieve member and book
        Book book = bookRepository.findById(bookID).orElse(null);
        List<Member> members = memberRepository.findByMemberID(memberId);

        if (book == null || members.isEmpty()) {
            throw new RuntimeException("Member or book not found.");
        }
        Member member = members.get(0); // Mengambil salah satu Member dari daftar (bisa tambahkan logika pemilihan yang sesuai)

        // Check if the book is available for loan
        if (loanRepository.existsByBookAndStatus(book, LoanStatus.ON_LOAN)) {
            throw new RuntimeException("Book is not available for loan.");
        }

        Loan loan = new Loan();
        loan.setMember(member);
        loan.setBook(book);
        loan.setStartDate(startDate != null ? startDate : LocalDate.now());
        loan.setDueDate(dueDate);
        loan.setStatus(LoanStatus.ON_LOAN);
        return loanRepository.save(loan);
    }

    @Override
    public int returnBook(String memberID, Long bookID, LocalDate returnDate) {
        // Retrieve member and book
        List<Member> members = memberRepository.findByMemberID(memberID);
        Book book = bookRepository.findById(bookID).orElse(null);
        if (book == null || members.isEmpty()) {
            throw new RuntimeException("Member or book not found.");
        }
        // Find the active loan for the member and book
        Loan activeLoan = loanRepository.findActiveLoan(memberID, bookID);

        if (activeLoan != null) {
            // Calculate late days and update lateDays field
            LocalDate currentDate = (returnDate != null) ? returnDate : LocalDate.now();
            int lateDays = (int) Math.max(0, ChronoUnit.DAYS.between(activeLoan.getDueDate(), currentDate));
            activeLoan.setLateDays(lateDays);

            // Update loan status to RETURNED
            activeLoan.setStatus(LoanStatus.RETURNED);

            // Save the updated loan
            loanRepository.save(activeLoan);

            return (lateDays); // Mengembalikan jumlah hari keterlambatan
        } else {
            throw new RuntimeException("No active loan found for this member and book.");
        }
    }

}

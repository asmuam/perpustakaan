/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.polstat.perpustakaan.repository;

/**
 *
 * @author asmua
 */
import com.polstat.perpustakaan.entity.Book;
import com.polstat.perpustakaan.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "loans", path = "loans")
public interface LoanRepository extends JpaRepository<Loan, Long> {

    // Anda dapat menambahkan operasi kustom jika diperlukan
    @RestResource(path = "existsByBookAndStatus", rel = "existsByBookAndStatus")
    boolean existsByBookAndStatus(@Param("book") Book book, @Param("status") Loan.LoanStatus loanStatus);

    @RestResource(path = "findActiveLoan", rel = "findActiveLoan")
    @Query("SELECT l FROM Loan l WHERE l.member.memberID = :memberID AND l.book.id = :bookID AND l.status = 'ON_LOAN'")
    Loan findActiveLoan(@Param("memberID") String memberID, @Param("bookID") Long bookID);
}


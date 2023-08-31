package com.polstat.perpustakaan.repository;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asmua
 */
import com.polstat.perpustakaan.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    public List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String keyword, String keyword0);

}

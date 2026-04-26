package com.library.library_backend.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.library.library_backend.model.Loan;

@Repository
public interface ILoanRepository extends JpaRepository <Loan, UUID> {

    List<Loan> findByLoanId(UUID loanId);

    // Buscar préstamos activos de un libro (return_date es null)
    // Esto sirve para validar si el libro ya está prestado
    @Query("SELECT l FROM Loan l WHERE l.book.bookId = :bookId AND l.returnDate IS NULL")
    List<Loan> findActiveLoansByBookId(@Param("bookId") UUID bookId);
}

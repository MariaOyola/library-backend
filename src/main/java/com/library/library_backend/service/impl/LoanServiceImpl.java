package com.library.library_backend.service.impl;

import com.library.library_backend.dto.request.LoanRequest;
import com.library.library_backend.dto.response.LoanResponse;
import com.library.library_backend.model.Book;
import com.library.library_backend.model.Loan;
import com.library.library_backend.model.UserAccount;
import com.library.library_backend.repository.IBookRepository;
import com.library.library_backend.repository.ILoanRepository;
import com.library.library_backend.repository.IUserAccountRepository;
import com.library.library_backend.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final ILoanRepository loanRepository;
    private final IBookRepository bookRepository;
    private final IUserAccountRepository userAccountRepository;

    @Override
    public List<LoanResponse> findAll() {
        return loanRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<LoanResponse> findByUser(UUID id) {
        return loanRepository.findByUserUserId(id)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public LoanResponse save(LoanRequest L) {
        // save y registerLoan hacen lo mismo — ambos usan toModel
        return toResponse(loanRepository.save(toModel(L)));
    }

    @Override
    public LoanResponse registerLoan(LoanRequest L) {
        // misma lógica que save
        return toResponse(loanRepository.save(toModel(L)));
    }

    @Override
    public LoanResponse update(UUID id, LoanRequest L) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        Book book = bookRepository.findById(L.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        UserAccount user = userAccountRepository.findById(L.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        loan.setBook(book);
        loan.setUser(user);

        return toResponse(loanRepository.save(loan));
    }

    @Override
    public LoanResponse returnBook(UUID loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        // Verificar que no esté ya devuelto
        if (loan.getReturnDate() != null) {
            throw new RuntimeException("Book already returned");
        }

        // El trigger de la BD actualiza available = TRUE automáticamente
        loan.setReturnDate(OffsetDateTime.now());
        return toResponse(loanRepository.save(loan));
    }

    @Override
    public void delete(UUID id) {
        loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        loanRepository.deleteById(id);
    }

    // Request → Model
    private Loan toModel(LoanRequest L) {
        Book book = bookRepository.findById(L.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        UserAccount user = userAccountRepository.findById(L.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!book.getAvailable()) {
            throw new RuntimeException("Book is not available for loan");
        }

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);
        loan.setLoanDate(OffsetDateTime.now());
        return loan;
    }

    // Model → Response
    private LoanResponse toResponse(Loan loan) {
        LoanResponse response = new LoanResponse();
        response.setLoanId(loan.getLoanId());
        response.setBookTitle(loan.getBook().getTitle());
        response.setBookAuthor(loan.getBook().getAuthor());
        response.setUserName(loan.getUser().getName());
        response.setUserEmail(loan.getUser().getEmail());
        response.setLoanDate(loan.getLoanDate());
        response.setReturnDate(loan.getReturnDate());
        response.setStatus(loan.getReturnDate() == null ? "ACTIVE" : "RETURNED");
        return response;
    }
}
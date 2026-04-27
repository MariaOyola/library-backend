package com.library.library_backend.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.library.library_backend.model.UserAccount;

@Repository
public interface IUserAccountRepository extends JpaRepository <UserAccount, UUID> {

    List<UserAccount> findByEmail(String email);

}

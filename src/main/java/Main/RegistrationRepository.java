package Main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegistrationRepository extends JpaRepository<Registration, Integer>{
    @Query("SELECT u FROM Registration u WHERE u.email = ?1")
    public Registration findByEmail(String email);
    @Query("SELECT u FROM Registration  u WHERE user_id = ?1")
    public Registration findById(int user_id);

}

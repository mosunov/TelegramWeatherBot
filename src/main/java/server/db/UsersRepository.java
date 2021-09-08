package server.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.db.entities.User;

@Repository
public interface UsersRepository extends JpaRepository<User,Long> {
}

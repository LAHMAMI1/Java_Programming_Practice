package fr.fortyTwo.chat.repositories;

import java.util.List;
import fr.fortyTwo.chat.models.User;

public interface UsersRepository {
    List<User> findAll(int page, int size);
}

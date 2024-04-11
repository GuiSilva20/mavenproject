package services;

import entities.User;
import repositories.UserRepository;
import org.springframework.stereotype.Service;
import exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("Usuário não encontrado com o id: " + id);
        }
        return user;
    }

    public User insert(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Usuário não encontrado com o id: " + id);
        }
    }

    public User update(Long id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        } else {
            throw new ResourceNotFoundException("Usuário não encontrado com o id: " + id);
        }
    }
}

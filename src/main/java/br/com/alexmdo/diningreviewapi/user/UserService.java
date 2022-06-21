package br.com.alexmdo.diningreviewapi.user;

import br.com.alexmdo.diningreviewapi.user.dto.CreateUser;
import br.com.alexmdo.diningreviewapi.user.dto.FindByName;
import br.com.alexmdo.diningreviewapi.user.dto.UpdateUser;
import br.com.alexmdo.diningreviewapi.user.exceptions.UserAlreadyExistsException;
import br.com.alexmdo.diningreviewapi.user.exceptions.UserNotFoundException;
import br.com.alexmdo.diningreviewapi.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public CreateUser registerUser(final CreateUser user) {
        List<User> users = userRepository.findByNameContainingIgnoreCase(user.getName());
        if (!users.isEmpty()) {
            throw new UserAlreadyExistsException("Cannot register user with the same name");
        }

        return userMapper
                .toCreateUserDTO(userRepository
                        .save(userMapper.toEntity(user)));
    }

    public UpdateUser updateUser(final UpdateUser updateUser) {
        Optional<User> foundUserOptional = userRepository.findById(updateUser.getId());
        if (foundUserOptional.isEmpty()) {
            throw new UserNotFoundException("User was not found given id " + updateUser.getId());
        }

        return userMapper
                .toUpdateUserDTO(userRepository
                        .save(userMapper
                                .toEntity(foundUserOptional.get(), updateUser)));
    }

    public List<FindByName> findByName(String name) {
        return userMapper
                .toFindByName(userRepository
                        .findByNameContainingIgnoreCase(name));
    }

    public void validateUserSubmittedDiningReview(final String name) {
        List<User> users = userRepository.findByNameContainingIgnoreCase(name);
        if (users.isEmpty()) {
            throw new UserNotFoundException("User was not found given name " + name);
        }
    }

}

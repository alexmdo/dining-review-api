package br.com.alexmdo.diningreviewapi.user;

import br.com.alexmdo.diningreviewapi.user.dto.CreateUser;
import br.com.alexmdo.diningreviewapi.user.dto.FindByName;
import br.com.alexmdo.diningreviewapi.user.dto.UpdateUser;
import br.com.alexmdo.diningreviewapi.user.model.User;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public CreateUser toCreateUserDTO(final User user) {
        CreateUser createUser = new CreateUser();
        createUser.setName(user.getName());
        return createUser;
    }

    public User toEntity(final CreateUser user) {
        User entity = new User();
        entity.setName(user.getName());
        return entity;
    }

    public User toEntity(final User foundUser, final UpdateUser updateUser) {
        foundUser.setId(updateUser.getId());
        foundUser.setCity(updateUser.getCity());
        foundUser.setState(updateUser.getState());
        foundUser.setZipCode(updateUser.getZipCode());
        foundUser.setInterestedInPeanutAllergies(updateUser.isInterestedInPeanutAllergies());
        foundUser.setInterestedInEggAllergies(updateUser.isInterestedInEggAllergies());
        foundUser.setInterestedInDairyAllergies(updateUser.isInterestedInDairyAllergies());

        return foundUser;
    }

    public UpdateUser toUpdateUserDTO(final User entity) {
        UpdateUser dto = new UpdateUser();

        dto.setId(entity.getId());
        dto.setCity(entity.getCity());
        dto.setState(entity.getState());
        dto.setZipCode(entity.getZipCode());
        dto.setInterestedInPeanutAllergies(entity.isInterestedInPeanutAllergies());
        dto.setInterestedInEggAllergies(entity.isInterestedInEggAllergies());
        dto.setInterestedInDairyAllergies(entity.isInterestedInDairyAllergies());

        return dto;
    }

    public List<FindByName> toFindByName(List<User> userEntities) {
        return userEntities
                .stream()
                .map(entity -> {
                    FindByName findByName = new FindByName();
                    findByName.setId(entity.getId());
                    findByName.setCity(entity.getCity());
                    findByName.setState(entity.getState());
                    findByName.setZipCode(entity.getZipCode());
                    findByName.setInterestedInPeanutAllergies(entity.isInterestedInPeanutAllergies());
                    findByName.setInterestedInEggAllergies(entity.isInterestedInEggAllergies());
                    findByName.setInterestedInDairyAllergies(entity.isInterestedInDairyAllergies());
                    return findByName;
                    })
                .collect(Collectors.toList());
    }
}

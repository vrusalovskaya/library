package org.library.application.mappers;

import org.library.application.models.User;
import org.library.persistence.entities.UserEntity;

public class UserMapper {

    private UserMapper() {

    }

    public static UserEntity toEntity(User user) {
        return new UserEntity(user.getId(), user.getName(), user.getBookList().stream().map(BookMapper::toEntity).toList());
    }

    public static User toModel(UserEntity userEntity) {
        return new User(userEntity.getId(), userEntity.getName(), userEntity.getBookEntities().stream().map(BookMapper::toModel).toList());
    }
}

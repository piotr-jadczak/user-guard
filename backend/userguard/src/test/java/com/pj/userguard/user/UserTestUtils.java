package com.pj.userguard.user;

import com.pj.userguard.user.dto.CreateUserDTO;
import com.pj.userguard.user.entity.Role;
import com.pj.userguard.user.entity.RoleName;
import com.pj.userguard.user.field.EmailAddress;
import com.pj.userguard.user.field.Username;

public class UserTestUtils {

    public static final String USERNAME = "john_kowalski";
    public static final String EMAIL = "john.kowalski@mail.com";
    public static final String PASSWORD = "whiteDog123!";
    public static final String ENCODED_PASSWORD = "ABC123!!";
    public static final Role USER_ROLE = Role.of(RoleName.USER);
    public static final Username USERNAME_OBJECT = Username.of(USERNAME);
    public static final EmailAddress EMAIL_ADDRESS = EmailAddress.of(EMAIL);
    public static final Long USER_ID = 1L;

    public static CreateUserDTO defaultCreateUserDTO() {
        return new CreateUserDTO(USERNAME, PASSWORD, EMAIL);
    }
}

package dao.hibernate;

import entity.UserDetails;

public interface UserDetailsDao {

    void createUserDetails(long userId, UserDetails details);

    UserDetails getUserDetailsById(long userId);

    void updateUserDetails(long userId, UserDetails userDetails);



}
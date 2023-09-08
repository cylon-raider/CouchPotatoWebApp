package com.gcu.CouchPotatoWebApp.business;

import com.gcu.CouchPotatoWebApp.data.UserDataService;
import com.gcu.CouchPotatoWebApp.model.LoginModel;
import com.gcu.CouchPotatoWebApp.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserBusinessService implements UserDetailsService {

    @Autowired
    private UserDataService userDataService;

    /**
     * Constructor for UserBusinessService.
     *
     * @param userDataService Service to interact with user data.
     */
    public UserBusinessService(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    /**
     * Create a new user.
     *
     * @param userModel The user model containing user details.
     * @return boolean indicating success or failure of user creation.
     */
    public boolean createUser(UserModel userModel){
        return userDataService.create(userModel);
    }

    /**
     * Load user details by username. This method is used by Spring Security during authentication.
     *
     * @param username The username of the user.
     * @return UserDetails containing user details required for authentication.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginModel user = userDataService.findByUsername(username);

        if(user != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("USER"));
            return new User(user.getUsername(), user.getPassword(), authorities);
        } else {
            throw new UsernameNotFoundException("Username not found");
        }
    }

    /**
     * Retrieve user authority based on username.
     *
     * @param username The username of the user.
     * @return UserModel containing user details and authority.
     */
    public UserModel getUserAuthority(String username) {
        return userDataService.getUserAuthority(username);
    }
}

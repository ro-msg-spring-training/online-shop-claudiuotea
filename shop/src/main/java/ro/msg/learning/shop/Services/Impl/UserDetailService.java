package ro.msg.learning.shop.Services.Impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.Models.Users;
import ro.msg.learning.shop.Repositories.Interfaces.IUserRepo;

@Service
@AllArgsConstructor
public class UserDetailService implements UserDetailsService {
    private IUserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users found = userRepo.findByUsername(s);
        if(found != null)
            return found;
        throw new UsernameNotFoundException("User -> " + s + " not found!");
    }
}

//package simple.run.SimpleRunWebApp.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import simple.run.SimpleRunWebApp.models.User;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    @Autowired
//    private UserRepositoryService userRepositoryService;
//
//    public CustomUserDetailsService(UserRepositoryService userRepositoryService) {
//        this.userRepositoryService = userRepositoryService;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepositoryService.findByLogin(username).isPresent() ? userRepositoryService.findByLogin(username).get() : null;
//        if (user == null) {
//            throw new UsernameNotFoundException("Unknown user: " + username);
//        }
//        return org.springframework.security.core.userdetails.User.builder()
//                .username(user.getName())
//                .password(user.getPassword())
//                .build();
//    }
//}

package sample.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import sample.data.User;
import sample.data.UserRepository;

@Component
public class UserRepositoryUserDetailsService implements UserDetailsService {
	final UserRepository users;

	@Autowired
	public UserRepositoryUserDetailsService(UserRepository users) {
		this.users = users;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = users.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Could not find " + username);
		}
		return new UserRepositoryUserDetails(user);
	}

	static class UserRepositoryUserDetails extends User implements UserDetails {
		private Set<GrantedAuthority> authorities = new HashSet<>();

		public UserRepositoryUserDetails(User user) {
			super(user);
			for(String role : user.getRoles()) {
				authorities.add(new SimpleGrantedAuthority("ROLE_"+ role));
			}
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return authorities;
		}

		@Override
		public String getUsername() {
			return getEmail();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

		private static final long serialVersionUID = 887022057184819072L;
	}
}

package bdd.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.morrice.SingleSignOn.user.business.IUserBusiness;
import com.morrice.SingleSignOn.user.repository.crud.UserRepository;
import com.morrice.SingleSignOn.user.repository.model.User;

@Component
public class UserDB {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private IUserBusiness userBusiness;
	
	public void insertUser(User user) {
		user.setPassword( userBusiness.passwordEncoder().encode( user.getPassword() )  );
		user.setEnabled(Boolean.TRUE);
		userRepository.save(user);
	}
	
	public void insertAleatoryUser() {
		Faker faker = new Faker();
		User user = new User();
		user.setLogin(faker.internet().emailAddress());
		user.setPassword(faker.internet().password());
		insertUser(user);
	}
	
}

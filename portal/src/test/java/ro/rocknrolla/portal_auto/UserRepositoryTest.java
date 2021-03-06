package ro.rocknrolla.portal_auto;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ro.rocknrolla.portal_auto.Application;
import ro.rocknrolla.portal_auto.entities.User;
import ro.rocknrolla.portal_auto.repositories.UserRepository;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("test")
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testFindAll() {
		List<User> users = userRepository.findAll();
		Assert.assertEquals(3, users.size());

	}

}



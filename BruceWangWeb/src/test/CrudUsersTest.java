package test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bruce.model.CrudUsers;
import com.bruce.model.Users;
import com.bruce.serviceInterface.CrudUsersServiceInter;
import com.bruce.serviceInterface.UsersServiceInter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext.xml" })
//@Transactional
public class CrudUsersTest {

	@Autowired
	private CrudUsersServiceInter crudUsersServiceInter;
	
	@Autowired
	private UsersServiceInter usersServiceInter;
	
	@Autowired
	private CacheManager cacheManager;

	@Test
	public void testCrud() {
		CrudUsers crudUser = new CrudUsers();
		crudUser.setFirstname("wang05");
		crudUser.setLastname("bruce05");
		crudUser.setPhone("75325910");
		crudUser.setEmail("wdquan1985@.com");
		crudUsersServiceInter.addUser(crudUser);
	}
	
	//*****非常重要*****，测试ehcache三级缓存功能。
	@Test
	public void getCruds() {
		for (int j = 0; j < 10; j++) {
			List<CrudUsers> list= crudUsersServiceInter.getAll();
			for (int i = 0; i < list.size(); i++) {
				System.out.println("name is:" + list.get(i).getFirstname() + ",xing is:" + list.get(i).getLastname());
			}
			System.out.println("Debug:这是第" + j + "次。");
		}
	}
	@Test
	public void setCruds() {
		CrudUsers user = new CrudUsers();
		for (int j = 30; j < 50; j++) {
			user.setFirstname("bruce" + j);
			user.setLastname("wang");
			user.setEmail("wdquan1985@163.com");
			user.setPhone("13675325910");
			crudUsersServiceInter.addUser(user);
		}
	}
	
	//*****非常重要*****，测试ehcache三级缓存功能。
	@Test
	public void getUsers() {
		for (int j = 0; j < 10; j++) {
			List<Users> list= usersServiceInter.getAll();
			for (int i = 0; i < list.size(); i++) {
				System.out.println("name is:" + list.get(i).getUsername() + ",password is:" + list.get(i).getPassword());
			}
			System.out.println("Debug:这是第" + j + "次。");
		}
	}
	
//	@Test
//	public void setUsers() {
//		Users user = new Users("user", "user", "ROLE_ADMIN", true);
//		usersServiceInter.addUser(user);
//	}
}



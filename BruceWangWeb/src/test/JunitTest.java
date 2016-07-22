package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bruce.daoInterface.UsersDao;
import com.bruce.model.Users;
import com.bruce.service.UsersService;

//import com.sextant.base.service.UserService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext.xml" })
//@Transactional
public class JunitTest extends AbstractJUnit4SpringContextTests{
	//非常重要 ，虽然spring是用了xml bean，而不是注解bean，而且这里的被注入对象也不是interface类型，而是实体类。
	//但是注入就是用bean给我们定义的空对象赋值，不管这个对象是interface类型的，还是实体类，都是可以被注入的。
	//之前使用了接口，其实就是使用了多态，跟注入没有关系。

//	@Autowired
//	UsersService usersService;
	
	@Autowired
	UsersDao usersDao;
	
	
	@Test
	public void findByName() {
		try {
			Users user = usersDao.findByName("bruce");
			System.out.println("密码是:" + user.getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	
//	@Test
//	public void testPassword() {
//		try {
//			String password = userService.encryptPassword("123456a");
//			System.out.println("密码是:" + password);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//    public static String StringFilter(String str) throws PatternSyntaxException {
//	    // 只允许字母和数字      
//		// String   regEx  =  "[^a-zA-Z0-9]";               
//		// 清除掉所有特殊字符 
//	    String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？_《》]";
//	    Pattern   p   =   Pattern.compile(regEx);
//	    Matcher   m   =   p.matcher(str);
//	    return   m.replaceAll("").trim();
//    }	
}



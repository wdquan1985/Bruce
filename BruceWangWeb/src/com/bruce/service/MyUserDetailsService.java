package com.bruce.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bruce.dao.UsersDaoImpl;
import com.bruce.daoInterface.UsersDao;
import com.bruce.model.Users;
import com.sun.org.apache.regexp.internal.recompile;

//自定义UserDetailsService.
@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService{
    private Map<String, Users> userMap = null;
    
    @Autowired  
    JdbcTemplate jdbcTemplate;
    
    private static Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
    private final String sqlLoadUser;
    private final String sqlLoadAuthorities;
    private final RowMapper<Users> myUserDetailsRowMapper;
    private final RowMapper<GrantedAuthority> authorityRowMapper;
    @Autowired
    private UsersDao usersDao; 
    
    public MyUserDetailsService() {  
        super();  
        sqlLoadUser = "SELECT id,username,password,enabled,content FROM users WHERE username=?"; 
        sqlLoadAuthorities = "SELECT role FROM users WHERE username=?";  
        myUserDetailsRowMapper = new RowMapper<Users>() {  
            @Override  
            public Users mapRow(ResultSet rs, int rowNum) throws SQLException {  
                return new Users(rs.getLong(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),rs.getBoolean(5),rs.getString(6),null);
            }
        };  
        authorityRowMapper = new RowMapper<GrantedAuthority>() {  
            @Override  
            public GrantedAuthority mapRow(ResultSet rs, int rowNum)  
                    throws SQLException {  
              return new SimpleGrantedAuthority(rs.getString(1));  
            }  
        };  
    } 

    @Override
    public UserDetails loadUserByUsername(String username)  
            throws UsernameNotFoundException {  
        try {  
//            Users userFromQuery = jdbcTemplate.queryForObject(sqlLoadUser,
//                    myUserDetailsRowMapper, username);
        	Users userFromQuery = usersDao.findByName(username);
        	System.out.println("the user name is:" + userFromQuery.getUsername());
//            logger.debug("查询得到用户：{}", userFromQuery); 
            List<GrantedAuthority> authorities = jdbcTemplate.query(  
                    sqlLoadAuthorities, authorityRowMapper, username);  
            logger.debug("得到其权限：{}", authorities);
            return new Users(userFromQuery.getId(), userFromQuery.getUsername(),  
                    userFromQuery.getPassword(), "", userFromQuery.isEnabled(),  
                    userFromQuery.getContent(), authorities);
        } catch (EmptyResultDataAccessException e) { 
            logger.debug("查询结果集为空:{}", username);
            throw new UsernameNotFoundException("用户名或密码不正确");
        }  
    }

//	public UsersDao getUsersDao() {
//		return usersDao;
//	}
//
//	public void setUsersDao(UsersDao usersDao) {
//		this.usersDao = usersDao;
//	}
}

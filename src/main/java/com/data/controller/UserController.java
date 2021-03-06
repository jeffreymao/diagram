package com.data.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.data.entity.UserEntity;
import com.data.mapper.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping("/getAllCompanys")
	public List<Map<String,String>> getAllCompanys() {
		return userMapper.getAllCompanys();
	}
	
	@RequestMapping("/getUsers")
	public List<UserEntity> getUsers() {
		List<UserEntity> users=userMapper.getAll();
		return users;
	}
	
    @RequestMapping("/getUser")
    public UserEntity getUser(Long id) {
    	UserEntity user=userMapper.getOne(id);
        return user;
    }
    
    @RequestMapping("/add")
    public void save(@RequestParam(value="userJson",defaultValue="{}") String userJson) {
    	ObjectMapper mapper = new ObjectMapper();
//    	userJson = "{\"userName\":\"landy\",\"passWord\":\"456\",\"userSex\":\"woman\",\"nickName\":\"baby123\"}";
    	System.out.println("add user param :"+userJson);
    	UserEntity user;
		try {
			if (userJson !="{}"){
				user = mapper.readValue(userJson, UserEntity.class);
				userMapper.insert(user);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @RequestMapping(value="update")
    public void update(UserEntity user) {
    	userMapper.update(user);
    }
    
    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
    	userMapper.delete(id);
    }
    
    
}
package ld.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Transactional
	public void insert() {
		userDao.insert();
		System.out.println("插入成功");
	}
}

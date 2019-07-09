package com.rab3.services;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.rab3.entities.CustomerLoginEntity;
import com.rab3.repositories.CustomerLoginRepository;

@Service
public class LoginService {

	private CustomerLoginRepository customerLoginRepository;
	
	
	public boolean isLoginValid(String username, String password) {
		
		CustomerLoginEntity customerLoginEntity = customerLoginRepository.getLoginByUserName(username);
		if(customerLoginEntity == null) {
			return false;
		} else {
			String hashedPassword = DigestUtils.sha256Hex(password);
			if(hashedPassword.equals(customerLoginEntity.getPassword())) {
				customerLoginEntity.setLastSignedOn(new Date());
				customerLoginRepository.update(customerLoginEntity);
				return true;
			} else {
				return false;
			}
		}
		
	}


	public CustomerLoginRepository getCustomerLoginRepository() {
		return customerLoginRepository;
	}


	public void setCustomerLoginRepository(CustomerLoginRepository customerLoginRepository) {
		this.customerLoginRepository = customerLoginRepository;
	}
	
	
}

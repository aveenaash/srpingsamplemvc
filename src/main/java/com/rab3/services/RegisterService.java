package com.rab3.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.rab3.dtos.SignUpDto;
import com.rab3.entities.CustomerEntity;
import com.rab3.entities.CustomerLoginEntity;
import com.rab3.repositories.CustomerLoginRepository;

@Service
public class RegisterService {

	private CustomerLoginRepository customerLoginRepository;
	
	public boolean register(SignUpDto signUpDto) {
		
		CustomerLoginEntity existingLogin = customerLoginRepository.getLoginByUserName(signUpDto.getUsername());
		if(existingLogin != null) {
			return false;
		} else {
			CustomerLoginEntity customerLogin = new CustomerLoginEntity();
			customerLogin.setUserName(signUpDto.getUsername());
			
			String hashedPassword = DigestUtils.sha256Hex(signUpDto.getPassword());
			customerLogin.setPassword(hashedPassword);
			
			
			CustomerEntity customerEntity = new CustomerEntity();
			customerEntity.setFirstName(signUpDto.getFirstname());
			customerEntity.setLastName(signUpDto.getLastname());
			customerEntity.setPhone(signUpDto.getPhone());
			customerEntity.setEmail(signUpDto.getEmail());
			customerEntity.setAddress(signUpDto.getAddress());
			
			customerLogin.setCustomer(customerEntity);
			
			customerLoginRepository.save(customerLogin);
			
			return true;
		}
		
		
	}

	public CustomerLoginRepository getCustomerLoginRepository() {
		return customerLoginRepository;
	}

	public void setCustomerLoginRepository(CustomerLoginRepository customerLoginRepository) {
		this.customerLoginRepository = customerLoginRepository;
	}
	
}

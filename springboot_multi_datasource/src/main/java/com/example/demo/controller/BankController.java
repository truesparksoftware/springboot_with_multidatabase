package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Bank;
import com.example.demo.repo.BankRepository;

@RestController
@RequestMapping("/bank")
public class BankController {

	@Autowired
	BankRepository bankRepository;
	
	@GetMapping("/all")
	public List<Bank> getAllBanks(){
		return (List<Bank>) bankRepository.findAll();
	}
	
	@PostMapping("/save")
	public Bank saveBank(@RequestBody Bank bank) {
     return bankRepository.save(bank);		
	}
}

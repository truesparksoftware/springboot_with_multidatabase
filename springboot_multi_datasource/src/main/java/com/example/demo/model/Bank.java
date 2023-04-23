package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name="bank1")
public class Bank {
	@Id
	private String id;
	private String name;
	private String loc;
}

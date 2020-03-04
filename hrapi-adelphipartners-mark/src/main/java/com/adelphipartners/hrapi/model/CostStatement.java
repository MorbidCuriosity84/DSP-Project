package com.adelphipartners.hrapi.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.*;

@Embeddable
@Data
public class CostStatement {

	@Column(name = "COSTS")
	private BigDecimal costs;
	@Column(name = "DATE")
	private LocalDate date;
	/*
	@ManyToOne(fetch = FetchType.EAGER)
	private Department department;
	*/
	public CostStatement() {}
	
	public CostStatement(LocalDate date) 
	{
		Random rnd = new Random();
		long val = rnd.nextInt(250000);
		BigDecimal bd = BigDecimal.valueOf(val);
		this.date = date;
		this.costs = bd;
	}
	/*
	public CostStatement(Department dept, LocalDate date) 
	{
		Random rnd = new Random();
		long val = rnd.nextInt(250000);
		BigDecimal bd = BigDecimal.valueOf(val);
		this.date = date;
		this.costs = bd;
		this.department = dept;
	}
	
	@Override
	public String toString()
	{
		String pattern = "MM-YYYY";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern); 
		return "Costs for " + this.date.format(dtf) + " : £" + this.costs.intValue();
	}
	*/

	public BigDecimal getCosts() {
		return costs;
	}

	public void setCosts(BigDecimal costs) {
		this.costs = costs;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	/*
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
 	*/
}

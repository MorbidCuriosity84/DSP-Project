package com.adelphipartners.hrapi.model;

import java.math.BigDecimal;
import java.text.DateFormat;
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
public class IncomeStatement {

	@Column(name = "REVENUE")
	private BigDecimal revenue;
	@Column(name = "DATE")
	private LocalDate date;
	/*
	@ManyToOne(fetch = FetchType.EAGER)
	private Department department;
	*/
	public IncomeStatement() {}
	
	public IncomeStatement(LocalDate date) 
	{
		Random rnd = new Random();
		long val = rnd.nextInt(250000);
		BigDecimal bd = BigDecimal.valueOf(val);
		this.date = date;
		this.revenue = bd;
	}
	/*
	public IncomeStatement(Department dept, LocalDate date) 
	{
		Random rnd = new Random();
		long val = rnd.nextInt(250000);
		BigDecimal bd = BigDecimal.valueOf(val);
		this.date = date;
		this.revenue = bd;
		this.department = dept;
	}
	
	@Override
	public String toString()
	{
		String pattern = "MM-YYYY";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern); 
		return "Income for " + this.date.format(dtf) + " : £" + this.revenue.intValue();
	}
	 */

	public BigDecimal getRevenue() {
		return revenue;
	}

	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
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
	}*/
}

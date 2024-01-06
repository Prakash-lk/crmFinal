package com.examly.crm.service;

import java.util.*;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.crm.model.Customer;
import com.examly.crm.model.Opportunity;
import com.examly.crm.model.Sale;
import com.examly.crm.repository.CustomerRepository;
import com.examly.crm.repository.OpportunityRepository;
import com.examly.crm.repository.SaleRepository;

@Service
public  class SaleServiceImp implements SaleService{


	@Autowired
	private SaleRepository saleRepository;
	@Autowired
    private CustomerRepository customerRepository;
	@Autowired
	private OpportunityRepository opportunityRepository;


	//@Override
	@Override
	public Sale addSale(Long customerid,Long opportunityid, Sale sale) {
		Customer customer = this.customerRepository.findById(customerid).orElseThrow(()->new RuntimeException("Customer not found"));
		Opportunity opportunity = this.opportunityRepository.findById(opportunityid).orElseThrow(()->new RuntimeException("opportunity not found"));
		Sale newsale=new Sale();
		newsale.setAmount(sale.getAmount());
		newsale.setName(sale.getName());
		newsale.setDate(sale.getDate());
		newsale.setNotes(sale.getNotes());
		newsale.setCustomer_id(customer.getId());
		System.out.println(customer.getId());
		newsale.setOpportunity_id(opportunity.getId());
		System.out.println(opportunity.getId());
		return this.saleRepository.save(newsale);
	}

	@Override
	public List<Sale> getSale() {
		return this.saleRepository.findAll();
	}

	@Override
	public Sale getSaleById(Long id) {
		return this.saleRepository.findById(id).orElseThrow(()->new RuntimeException("Sale not found"));

	}

	@Override
	public Sale updateSale(Long id, Sale sale) {
		Sale updatedSale=saleRepository.findById(id).orElseThrow(()->new RuntimeException("Sale not found"));
        updatedSale.setName(sale.getName());
        updatedSale.setAmount(sale.getAmount());
        updatedSale.setNotes(sale.getNotes());
        updatedSale.setDate(sale.getDate());
        return this.saleRepository.save(updatedSale);
	}

	@Override
	public void deleteSale(Long id) {
		Sale deleteSale=saleRepository.findById(id).orElseThrow(()->new RuntimeException("Sale not found"));
        this.saleRepository.delete(deleteSale);

	}






}

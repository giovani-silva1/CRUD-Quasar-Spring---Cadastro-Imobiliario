package br.com.sgci.controller.dto;

import java.util.List;

public class ResponsePagedCommomDTO<T> {
	
	private List<T> data;
	private Long totalRecords;
	private int totalPages;
	private int pageSize;
	
	
	public ResponsePagedCommomDTO(List<T> data, Long totalRecords, int totalPages, int pageSize) {
		super();
		this.data = data;
		this.totalRecords = totalRecords;
		this.totalPages = totalPages;
		this.pageSize = pageSize;
	}


	public List<T> getData() {
		return data;
	}


	public void setData(List<T> data) {
		this.data = data;
	}


	public Long getTotalRecords() {
		return totalRecords;
	}


	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}


	public int getTotalPages() {
		return totalPages;
	}


	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
 
	
	

}

package br.com.sgci.controller.dto;

import org.springframework.data.domain.Sort.Direction;

public class FilterPageableDTO {
	
	private Integer page;
	private Integer size;
	private Direction direction;
	private String ordernarPor;

	
	public FilterPageableDTO() {
		
	}


	public FilterPageableDTO(Integer page, Integer size, Direction direction, String ordernarPor) {
		super();
		this.page = page;
		this.size = size;
		this.direction = direction;
		this.ordernarPor = ordernarPor;
	}


	public Integer getPage() {
		return page;
	}


	public void setPage(Integer page) {
		this.page = page;
	}


	public Integer getSize() {
		return size;
	}


	public void setSize(Integer size) {
		this.size = size;
	}


	public Direction getDirection() {
		return direction;
	}


	public void setDirection(Direction direction) {
		this.direction = direction;
	}


	public String getOrdernarPor() {
		return ordernarPor;
	}


	public void setOrdernarPor(String ordernarPor) {
		this.ordernarPor = ordernarPor;
	}
	
	
	
	
	
	
	
}

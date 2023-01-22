package com.jlfilho.course.domain.enums;

public enum OrderStatus {
	WAITING_PAYMENT(1, "Aguardando pagamento"),
	PAID(2, "Pago"),
	SHIPPED(3, "Enviado"),
	DELIVERED(4, "Entregue"),
	CANCELED(5, "Cancelado");
	
	private int id;
	private String descrition;
	
	private OrderStatus(int id, String descrition) {
		this.id = id;
		this.descrition = descrition;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrition() {
		return descrition;
	}

	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}
	
	public static OrderStatus toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		for (OrderStatus x : OrderStatus.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
	}
	
	
	
	

}

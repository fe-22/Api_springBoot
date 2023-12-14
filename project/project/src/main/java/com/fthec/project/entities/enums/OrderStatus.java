package com.fthec.project.entities.enums;

public enum OrderStatus {
	
		WAITING_PAYMENT(1),//ESPERANDO PAGAMENTO, enum no padrão java e enumerado, assim devemos atribuir numeros dentro do parente.
		PAID(2),//PAGO
		SHIPPED(3),//ENVIADO
		DELIVERED(4),//ENTREGUE
		CANCELED(5);//CANCELADO
	
	private int code;//enumeração manual devemos criar um construtor.
	
	private OrderStatus(int code) {//esse construtor para enumeração, dever ser do tipo private. 
		this.code = code;
	}
	
	public int getCode() {
		return code;//esse método torna o contrutor acessivel ao mundo exterior.
	}
	
	public static OrderStatus valueOf(int code) {//método estatico que não precisa de instaciação, converte enumeraçao para numerico. 
		 for (OrderStatus value : OrderStatus.values()) {//teste para percorrer os códigos númericos
			 if (value.getCode() == code) {//percorre todos os valores possiveis do tipo enumerado
				 return value;
			 }
			 
		 }
		 
		 throw new IllegalArgumentException("Invalid OrderStatus code");
	}

}

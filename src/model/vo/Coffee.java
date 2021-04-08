package model.vo;

public class Coffee {
	private int orderNo;  // 커피 주문 번호
	private String coffeeName; // 커피명
	private int cups; // 잔의 수
	private int price; // 가격
	
	public Coffee() { // 기본생성자
		
	}
	
	public Coffee(int type, int cups) { // 객체생성이 되면서 type과 cups를 받는다.
		setCoffeeName(type); // 호출해서 type을 넘겨줌
		setCups(cups);		 // 호출해서 cups를 넘겨줌
	}

	public String getCoffeeName() {
		return coffeeName;
	}
	
	public void setCoffeeName(int type) {
		switch(type) {
		case 1:
			this.coffeeName = "아메리카노";
			this.price = 3000;
			break;
		case 2:
			this.coffeeName = "카페라떼";
			this.price = 3500;
			break;
		case 3:
			this.coffeeName = "카푸치노";
			this.price = 4000;
			break;
			
		}
	}
	
	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
 
	public int getCups() {
		return cups;
	}

	public void setCups(int cups) { // 컵의 수를 받아서 cups에 대입
		this.cups = cups;
		setPrice(price*cups);   // 메서드에서 메서드 호출 ,,, 가격을 넘겨줘야함
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public void plusCups(int cups) {
		this.cups += cups;				 // 컵 누적
		setPrice(this.price*this.cups); 
	}
	
	@Override
	public String toString() {
		
		return "주문번호:"+orderNo+", 커피명:"+coffeeName+", 잔의 수:"+cups+",가격"+price;  // 
	}
}

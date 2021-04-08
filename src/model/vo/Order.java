package model.vo;

import java.util.Hashtable;

public class Order {
	private Hashtable<String, Coffee> ht;  // 키값과 데이터
	private int orderNo;
	private int price;
	
	public Order() {  // 주문정보
		ht = new Hashtable<String, Coffee>(); // 해쉬테이블 객체생성 이 객체를 ht에 연결
		
	}
	
	public void add(Coffee coffee) { // 커피 객체가 넘어오면 커피의 이름을 key값, coffee를 데이터값으로 해서 해쉬테이블에 넣음
		ht.put(coffee.getCoffeeName(), coffee);
	}
	
	public Hashtable<String, Coffee> getHt(){ // 객체생성한 ht 참조변수를 리턴, 해쉬테이블 자체를 리턴시킨다.
		
		return ht;
	}
	
	public int getOrderNo() {
		
		return orderNo;
	}
	
	public void setOrderNo(String type) {
		
		this.orderNo = ht.get(type).getOrderNo();
	}
	
	public void setOrderNo2(int orderNo) { // 주문번호를 주고 해쉬테이블의 키값을 가지고, ArrayList의 크기를 받음
		this.orderNo = orderNo;
		for(String key:ht.keySet()) 
			ht.get(key).setOrderNo(orderNo); 
	}
	
	public int getPrice() {
		
		return price;
	}
	
	public void setPrice() {
		for(String key:ht.keySet())
			this.price += ht.get(key).getPrice();
	}
	
	public void editprice() {
		this.price = 0;
		for(String key:ht.keySet())
			this.price += ht.get(key).getPrice();
	}
	
	public String toString() {
		String a = "주문번호 : "+orderNo+"\n";
		if(ht.containsKey("아메리카노")) {
			String b = "아메리카노"+"잔수 :"+ht.get("아메리카노").getCups()+",소계 : "+ht.get("아메리카노").getPrice()+"\n";
			
			a += b; // a와 b의 문자열을 연결해서 a 에 넣어라
		}
		if(ht.containsKey("카페라떼")) {
			String c = "카페라떼"+"잔수 :"+ht.get("카페라떼").getCups()+",소계 : "+ht.get("카페라떼").getPrice()+"\n";
			
			a += c; // a와 b의 문자열을 연결해서 a 에 넣어라
		}
		if(ht.containsKey("카푸치노")) {
			String d = "카푸치노"+"잔수 :"+ht.get("카푸치노").getCups()+",소계 : "+ht.get("카푸치노").getPrice()+"\n";
			
			a += d; // a와 b의 문자열을 연결해서 a 에 넣어라
		}
		
		String e = "총계 : "+price;
		a += e;
		
		return a;
	}
}

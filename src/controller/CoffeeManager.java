package controller;

import java.util.ArrayList;

import model.dao.CoffeeDao;
import model.exception.CoffeeException;
import model.vo.Coffee;
import model.vo.Order;

public class CoffeeManager {
	private ArrayList<Order> orderList; // 주문 리스트
	private CoffeeDao coDao; // 주문 정보를 저장 (Dao 는 데이터관련)
	private Order od;

	public CoffeeManager() {
		// 커피매니저 객체생성되면서 orderArr 배열 10개 생성
		coDao = new CoffeeDao(); // 커피 주문을 10개 받는 배열
		orderList = coDao.openList();
		od = new Order();

	}

	public Order insertCoffee(Coffee coffee) { 	 // 커피 주문 정보등록
		
		switch(coffee.getCoffeeName()) {
		case "아메리카노":
			if(!(od.getHt().containsKey("아메리카노"))) { // od. 이 가져온 해쉬테이블의 키값이 아메리카노 가 아니냐? 키값 없으면TRUE
				od.getHt().put(coffee.getCoffeeName(), coffee);
			}else {
				// 키값이 있는 경우
				od.getHt().get("아메리카노").plusCups(coffee.getCups());
			}
			break;
		
		case "카페라떼":
			if(!(od.getHt().containsKey("카페라떼"))) { // 
				od.getHt().put(coffee.getCoffeeName(), coffee);
			}else {
				// 키값이 있는 경우
				od.getHt().get("카페라떼").plusCups(coffee.getCups());
			}
			break;
		
		case "카푸치노":
			if(!(od.getHt().containsKey("카푸치노"))) { 
				od.getHt().put(coffee.getCoffeeName(), coffee);
			}else {
				od.getHt().get("카푸치노").plusCups(coffee.getCups());
			}
			break;
		}
		
		return od;
	}
	
	public void insertOrder() { // 주문 들어온거를 오더리스트에 저장
		orderList.add(od);
		od.setOrderNo2(orderList.size()); 
	}

	public Order verifyCoffee(int orderNo) throws CoffeeException { // 주문 내역확인

		if (orderNo > orderList.size()) { // 마지막 주문번호보다 큰번호가 들어오면 커피익셉션 발생시켜서 던져라
			throw new CoffeeException("잘못된 주문 정보입니다.");

		}

		return orderList.get(orderNo - 1); // 주문번호를 저장.
	}

	public void updateCoffee(int orderNo, Coffee coffee) { // 주문번호
		coffee.setOrderNo(orderNo);
		Order localOd = orderList.get(orderNo-1); // 실제 위치 get으로 가져옴
		
		switch(coffee.getCoffeeName()) {
		case "아메리카노":
			if(localOd.getHt().containsKey("아메리카노")) {
				localOd.getHt().replace("아메리카노", coffee);
			}else {
				localOd.getHt().put("아메리카노", coffee);
			}
			break;
			
		case "카페라떼":
			if(localOd.getHt().containsKey("카페라떼")) {
				localOd.getHt().replace("카페라떼", coffee);
			}else {
				localOd.getHt().put("카페라떼", coffee);
			}
			break;	
		
		case "카푸치노":
			if(localOd.getHt().containsKey("카푸치노")) {
				localOd.getHt().replace("카푸치노", coffee);
			}else {
				localOd.getHt().put("카푸치노", coffee);
			}
			break;	
		}
		orderList.set(orderNo-1, localOd);  // 주문번호
		localOd.editprice();  // 가격수정
	}

	public void deleteCoffee(int idx) { // 넘어온 번호를 삭제해라

		orderList.remove(idx); // 해당되는 리스트에서 삭제
			for (int i = idx; i < orderList.size(); i++) {
				orderList.get(i).setOrderNo2(i + 1);
			
			}
	}
	
	public boolean deleteSubCoffee(int idx, int sel) {
		Order localOd = orderList.get(idx); // 주문리스트의 idx번째 가져옴(실제위치)
		
		switch(sel) {
		case 1:
			orderList.get(idx).getHt().remove("아메리카노");
			break;
		case 2:
			orderList.get(idx).getHt().remove("카페라떼");
			break;
		case 3:
			orderList.get(idx).getHt().remove("카푸치노");
			break;
			
		}
		orderList.set(idx, localOd);  // 다시 저장
		localOd.editprice();		  // 다시 계산
		
		if(localOd.getHt().isEmpty()) { // .isEmpty - 해쉬테이블이 비었냐고 물어봄
			return true;
		}
		return false;
	}
	
	public boolean check(int idx, int sel) {
		switch(sel) {
		case 1:
			if(orderList.get(idx).getHt().containsKey("아메리카노"))
				return true;
			else
				return false;
		case 2:	
			if(orderList.get(idx).getHt().containsKey("카페라뗴"))
				return true;
			else
				return false;
		case 3:	
			if(orderList.get(idx).getHt().containsKey("카푸치노"))
				return true;
			else
				return false;
		default:
			return false;
		}
	}
	
	

	public ArrayList<Order> getOrderList() { // 리스트에 있는내용을 보고싶다.

		return orderList;
	}

	public void setOrderList(ArrayList<Order> orderList) {
		this.orderList = orderList;
	}

	public void close() {
		coDao.saveList(orderList);
	}
	
	public Order getOrder() {
		return od;
	}
	
	public void clearOrder() {
		od = new Order();
	}

}

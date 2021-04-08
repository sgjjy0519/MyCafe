package model.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.vo.Coffee;
import model.vo.Order;

public class CoffeeDao {
	
	public ArrayList<Order> openList(){
		ArrayList<Order> list = new ArrayList<Order>();
		
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("coffee.dat"))){
			while(in.available()!= -1) {// 저장된 데이터가 있냐없냐, 데이터없으면 -1, 
				list.add((Order)in.readObject());
			}
		}catch(EOFException e) {
			System.out.println("불러오기 성공하였습니다.");
		}catch(IOException e) { 
			System.out.println(e.getMessage());
		}catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		return list;
	}
	
	public int saveList(ArrayList<Order> list) {
		int result = -1;
		
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("coffee.dat"))){
			for(Order lst : list) {
				out.writeObject(lst);
			}
		result = 1;
	}catch(IOException e) {
		System.out.println(e.getMessage());
		}
		return result;
	}
	
	public void outStream(ObjectOutputStream out, Coffee co) {
		try {
			out.writeObject(co);
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}

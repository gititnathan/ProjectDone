package ServerPos;
import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import javazoom.jl.player.Player;

public class MultiThread extends Thread{
	
	private Server ser;
	private Socket sock;
	private String table;
	private String msg;
	private int tableNum;
	
	public MultiThread(Server ser,Socket sock){
		this.ser = ser;
		this.sock = sock;
	}


	public void run(){
		try {
			InputStream is = sock.getInputStream(); // 데이터 받기 (스트림개방)
			DataInputStream dis = new DataInputStream(is); // 데이터 받기 스트림 업그레이드

			while(true){
				msg = dis.readUTF();
				if(msg.equals("orders")) {
					table = dis.readUTF();
					
					tableNum = Integer.parseInt(table);
					ser.tableSeat[tableNum-1].setIcon(new ImageIcon(getClass().getResource("/image/"+table+".png")));
					
					ObjectInputStream input = new ObjectInputStream(dis); 
					
					HashMap<String, ArrayList<Order>> tmp = ser.getFooldList();
					if(tmp.containsKey(table)){
						ArrayList<Order> relay = tmp.get(table);
						relay.addAll((ArrayList<Order>)input.readObject());
						ser.setMenulist(table, relay);
						Music doorbell = new Music(false);
						doorbell.start();
						JOptionPane.showMessageDialog(ser, table+"번 테이블 추가 주문");
					}else{
						ser.setMenulist(table, (ArrayList<Order>)input.readObject());
						Music doorbell = new Music(false);
						doorbell.start();
					}
				}else if(msg.equals("alert")) {
					table = dis.readUTF();
					Music doorbell = new Music(false);
					doorbell.start();
					JOptionPane.showMessageDialog(ser, table+"번 테이블 호출");
				}else {
					table = dis.readUTF();
					JOptionPane.showMessageDialog(ser, table+"번 Error 관리자에게 문의해주세요");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

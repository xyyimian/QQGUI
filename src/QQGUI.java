import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;

public class QQGUI extends JFrame implements ActionListener{

	private JLabel userLa;	//show "用户名
	private JLabel pwdLa;	//show "密码
	private JLabel verCodeLa;	//show "verCdoe
	private JTextField userTxt;  //input username
	private JPasswordField pwdTxt;	//input password
	private JTextField verCodeTxt;	//input verCode
	private JButton sureBt;		// sure Button
	private JButton quitBt;	//quit button
	private Mypanel mp;		// whole panel
	
	public QQGUI(){
		Init();
	}
	
	public void Init(){
		Frame frame =  new Frame("QQ log in");
		
		//用户文本
		userLa = new JLabel();
		userLa.setText("username");
		userLa.setSize(70,100);
		userLa.setLocation(100,55);
		
		//password text
		pwdLa = new JLabel();
		pwdLa.setText("password");
		pwdLa.setSize(70,50);
		pwdLa.setLocation(100,120);
		
		//user input box
		userTxt = new JTextField();
		userTxt.setSize(100,20);
		userTxt.setLocation(170,95);
		
		pwdTxt = new JPasswordField(30);
		pwdTxt.setSize(100,20);
		pwdTxt.setLocation(170,135);
		
		sureBt = new JButton("log in");
		sureBt.setSize(60,25);
		sureBt.setLocation(135,260);
		
		quitBt = new JButton("quit");
		quitBt.setSize(60,25);
		quitBt.setLocation(240,260);
		
		verCodeLa = new JLabel();
		verCodeLa.setText("verCode");
		verCodeLa.setSize(60,50);
		verCodeLa.setLocation(100,165);
		
		verCodeTxt = new JTextField();
		verCodeTxt.setSize(100,20);
		verCodeTxt.setLocation(170, 180);
		
		mp = new Mypanel();
		mp.setSize(100,30);
		mp.setLocation(280,175);
		
		JComboBox xlk = new JComboBox();
		xlk.setSize(60,20);
		xlk.setLocation(200,220);
		xlk.addItem("online");
		xlk.addItem("offline");
		xlk.addItem("busy");
		
		this.setLayout(null);		
		this.setSize(500,400);
		this.add(userLa);
		this.add(pwdLa);
		this.add(userTxt);
		this.add(pwdTxt);
		this.add(sureBt);
		this.add(quitBt);
		this.add(verCodeLa);
		this.add(verCodeTxt);
		this.add(xlk);
		this.add(mp);
		sureBt.addActionListener(this);
		quitBt.addActionListener(this);
		this.setVisible(true);
		
	}
	
	
	public void actionPerformed(ActionEvent e){
		//获取点击事件的强制源转换
		JButton bt = (JButton)e.getSource();
		//
		String str = bt.getText();
		String password = String.valueOf(this.pwdTxt.getPassword());
		if(str.equals("log in")){	//	否则说明是退出按钮
			if(!CheckIsNull()){
				String user = userTxt.getText().trim();
				if(checkUserAndPwd(user,password)){
					this.setVisible(false);
					MainFrame frame  = new MainFrame();
				}
				else{
					JOptionPane pane = new JOptionPane("username or password error");		//确认对话框;
					JDialog dialog = pane.createDialog(this, "error");
					dialog.show();
				}
			}
		}
		else{
			System.exit(0);
		}
	}
	
	private boolean CheckIsNull(){
		boolean flag = false;
		if(userTxt.getText().trim().equals(" ")){
			flag = true;
		}
		else{
			if(pwdTxt.getPassword().equals(" ")){
				flag = true;
			}
		}
		return flag;
	}
	
	
	private boolean checkUserAndPwd(String username, String pwd){
		boolean result = false;
		try{
			FileReader fileReader = new FileReader("./database.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String str = bufferedReader.readLine();
			
			while(str!= null){
				String[] strs = str.split(",");
				if(strs[0].equals(username)){
					if(strs[1].equals(pwd))
						result = true;
				}
				str = bufferedReader.readLine();
			}
			fileReader.close();
		}catch(Exception ex){
			System.out.println("some error happen while compare user with pwd");
		}
		return result;
	}
}

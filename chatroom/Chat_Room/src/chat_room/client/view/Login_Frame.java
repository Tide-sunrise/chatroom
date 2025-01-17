package chat_room.client.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import chat.common.User;
import chat_room.client.backstage.ClientManage;
import chat_room.client.backstage.ClientServerTogether;
import chat_room.client.tools.ClientCollection;
import chat_room.client.tools.ManageClientCollction;
import chat_room.client.tools.Tools;
/**
 * 登陆的界面
 * @author Administrator
 *
 */
public class Login_Frame extends JFrame {
	//定义需要的组件
	private JLabel jl1,jl2;	//标签
	private JTextField jtf;	//文本框
	private JPasswordField jpf;	//密码文本框
	private JButton jb1,jb2,jb3;	//按钮
	private JFrame jf;
	public Login_Frame() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		super("登录界面");
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		this.jf = this;
		this.setLayout(null);
		this.setSize(400,300);
		Container c = this.getContentPane();
		jl1 = new JLabel("用户名");
		jl1.setBounds(100, 60, 50, 20);
		c.add(jl1);
		
		jtf = new JTextField();
		//获得光标
		jtf.grabFocus();
		jtf.setBounds(160, 60, 120, 20);
		c.add(jtf);
		
		jl2 = new JLabel("密码");
		jl2.setBounds(100, 140, 50, 20);
		c.add(jl2);
		
		jpf = new JPasswordField();
		jpf.setBounds(160, 140, 120, 20);
		c.add(jpf);
		
		jb1 = new JButton("登录");
		jb1.setBounds(90, 210, 60, 20);
		c.add(jb1);
		 //登陆按扭添加事件监听
        jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String Name = jtf.getText();
				String PassWord = new String(jpf.getPassword());
				if(Name.equals("")){
					JOptionPane.showMessageDialog(jf, "用户名不能为空");
				}else if(PassWord.equals("")){
					JOptionPane.showMessageDialog(jf, "密码不能为空");
				}else{
					User user = new User(Name, PassWord);
					ClientManage cm = new ClientManage();
					if(cm.IsConnect()){
						if(!cm.Check_isLogin(user)){
							if(cm.Login()){
								JOptionPane.showMessageDialog(jf, "登录成功");
								jf.dispose();
                                Client_Frame client = null;
                                try {
                                    client = new Client_Frame(user.getName(),cm);
                                } catch (UnsupportedLookAndFeelException ex) {
                                    throw new RuntimeException(ex);
                                } catch (ClassNotFoundException ex) {
                                    throw new RuntimeException(ex);
                                } catch (InstantiationException ex) {
                                    throw new RuntimeException(ex);
                                } catch (IllegalAccessException ex) {
                                    throw new RuntimeException(ex);
                                }
                                //获得聊天界面
								String name = user.getName();
								//添加入聊天界面集合
								ManageClientCollction.addClientCollction(name, client);
								ClientServerTogether clientConServer = new ClientServerTogether(cm,client);
								//将该线程对象加入线程序集合
								ClientCollection.addClientThreadCollection(user.getName(), clientConServer);
								//启动客户端继续与服务器端通信的后台线程
								Thread t = new Thread(clientConServer);
								t.start();
							}else{
								JOptionPane.showMessageDialog(jf, "用户名或密码错误");
								return ;
							}
						}else{
							JOptionPane.showMessageDialog(jf, "用户已然登录");
							return ;
						}
					}else{
						JOptionPane.showMessageDialog(jf,"连接不到服务器");
					}
				}
			}
		});
		
		jb2 = new JButton("重置");
		jb2.setBounds(170, 210, 60, 20);
		c.add(jb2);
		//重置按扭注册事件监听
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jtf.setText("");
				jpf.setText("");
				//获得光标
				jtf.grabFocus();
			}
		});
		jb3 = new JButton("注册");
		jb3.setBounds(250, 210, 60, 20);
		c.add(jb3);
	    //注册按扭添加事件监听
	    jb3.addActionListener(new ActionListener() {
				
	    	@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//启动注册界面
                try {
                    Register_Frame register = new Register_Frame();
                } catch (UnsupportedLookAndFeelException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (InstantiationException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
                jf.dispose();
			}
		});
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Tools.setFrameCenter(this);
		this.setVisible(true);
	}
	
	
}

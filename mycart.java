import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class mycart implements ActionListener,ItemListener
{
	int price[] = {80,250,55,15,40,80,120,75,45};
	JFrame fr; 
	JLabel l1,l2,ll2,ll3,l3,l4,l5,l6,l7,l8,l9,l10;
	JTextField t1,t3,t4,t5;
	JTextField tt1;
	JPasswordField t2,tt2;
	List ls1,ls2;
	JButton b1,b2,b3,b4,b5;
	JButton bb1,bb2;
	Choice cb; 
	Image img;
	Checkbox c1; 	
	double tot=0,dis=0,gtot=0;

	Connection con;
	PreparedStatement ps;
	ResultSet rs;	

	public mycart()
	{
		fr = new JFrame("Shopping Cart");
		fr.setSize(1360,768);
		fr.setLayout(null);
		//fr.setContentPane(new JLabel(new ImageIcon("images/back.png")));
		fr.setContentPane(new JLabel(new ImageIcon("images/back1.png")));

		img = Toolkit.getDefaultToolkit().getImage("images/cart1.png");
		fr.setIconImage(img);

		l1 = new JLabel("Shopping Cart");
		l1.setBounds(450,20,400,70);
		l1.setFont(new Font("jokerman",Font.BOLD,50));
		l1.setForeground(Color.gray);
		fr.add(l1);

		l9 = new JLabel("SignIn");
		l9.setForeground(Color.green);
		l9.setFont(new Font("verdana",Font.BOLD,20));
		l9.setBounds(100,90,120,25);
		fr.add(l9);

		l2 = new JLabel("UserName");
		l3 = new JLabel("Password");
		l2.setBounds(100,130,110,30);
		l3.setBounds(100,170,110,30);
		l2.setFont(new Font("Verdana",Font.PLAIN,20));
		l3.setFont(new Font("Verdana",Font.PLAIN,20));
		l2.setForeground(Color.white);
		l3.setForeground(Color.white);
		fr.add(l2);
		fr.add(l3);
		
		t1 = new JTextField();
		t2 = new JPasswordField(); 
		t2.setEchoChar('*');
		t1.setBounds(230,130,205,30);
		t2.setBounds(230,170,205,30);
		t1.setFont(new Font("verdana",Font.BOLD,18));
		t2.setFont(new Font("verdana",Font.BOLD,18));
		fr.add(t1);
		fr.add(t2);

		b1 = new JButton("Login");
		b2 = new JButton("Logout");
		b1.setBounds(230,208,100,30);
		b2.setBounds(335,208,100,30);
		b1.setFont(new Font("verdana",Font.BOLD,15));
		b2.setFont(new Font("verdana",Font.BOLD,15));
		b1.addActionListener(this);
		b2.addActionListener(this);
		b2.setEnabled(false);
		fr.add(b1);
		fr.add(b2);

		l10 = new JLabel("SignUp");
		l10.setForeground(Color.green);
		l10.setFont(new Font("verdana",Font.BOLD,20));
		l10.setBounds(100,258,120,25);
		fr.add(l10);

		ll2 = new JLabel("UserName");
		ll3 = new JLabel("Password");
		ll2.setBounds(100,298,110,30);
		ll3.setBounds(100,338,110,30);
		ll2.setFont(new Font("Verdana",Font.PLAIN,20));
		ll3.setFont(new Font("Verdana",Font.PLAIN,20));
		ll2.setForeground(Color.white);
		ll3.setForeground(Color.white);
		fr.add(ll2);
		fr.add(ll3);
		
		tt1 = new JTextField();
		tt2 = new JPasswordField(); 
		tt2.setEchoChar('*');
		tt1.setBounds(230,298,205,30);
		tt2.setBounds(230,338,205,30);
		tt1.setFont(new Font("verdana",Font.BOLD,18));
		tt2.setFont(new Font("verdana",Font.BOLD,18));
		fr.add(tt1);
		fr.add(tt2);

		bb1 = new JButton("Submit");
		bb2 = new JButton("Reset");
		bb1.setBounds(230,376,100,30);
		bb2.setBounds(335,376,100,30);
		bb1.setFont(new Font("verdana",Font.BOLD,15));
		bb2.setFont(new Font("verdana",Font.BOLD,15));
		bb1.addActionListener(this);
		bb2.addActionListener(this);
		fr.add(bb1);
		fr.add(bb2);
		
		cb = new Choice();
		cb.add("Pasta");
		cb.add("Pizza");
		cb.add("Pastry");
		cb.add("Cold Drink");
		cb.add("Biscuits");
		cb.add("Namkeen");
		cb.add("Ice Cream");
		cb.add("Dhokla");
		cb.add("Coffee");
		cb.setBounds(500,130,180,30);
		cb.setFont(new Font("verdana",Font.BOLD,18));
		cb.setVisible(false);
		cb.addItemListener(this);
		fr.add(cb);		
		
		ls1 = new List();
		ls2 = new List();
		ls1.setBounds(700,130,150,200);
		ls2.setBounds(851,130,150,200);
		ls1.setFont(new Font("verdana",Font.BOLD,18));
		ls2.setFont(new Font("verdana",Font.BOLD,18));
		ls1.setForeground(Color.blue);
		ls2.setForeground(Color.blue);
		ls1.setVisible(false);
		ls2.setVisible(false);
		ls2.setEnabled(false);
		fr.add(ls1);
		fr.add(ls2);

		b3 = new JButton("Total");
		b3.setBounds(700,350,150,30);	
		b3.setFont(new Font("verdana",Font.BOLD,15));
		b3.setVisible(false);	
		b3.addActionListener(this);
		fr.add(b3);
	
		b4 = new JButton("Remove Item");
		b5 = new JButton("Clear Cart");
		b4.setBounds(1010,130,150,30);	
		b5.setBounds(1010,170,150,30);	
		b4.setFont(new Font("verdana",Font.BOLD,15));
		b5.setFont(new Font("verdana",Font.BOLD,15));
		b4.setVisible(false);
		b5.setVisible(false);
		b4.addActionListener(this);
		b5.addActionListener(this);
		fr.add(b4);
		fr.add(b5);
		
		t3 = new JTextField();
		t4 = new JTextField();
		t5 = new JTextField();
		t3.setEditable(false);
		t4.setEditable(false);
		t5.setEditable(false);
 		t3.setBounds(851,350,150,30);
		t4.setBounds(851,390,150,30);
		t5.setBounds(851,430,150,30);
		t3.setFont(new Font("verdana",Font.BOLD,18));
		t4.setFont(new Font("verdana",Font.BOLD,18));
		t5.setFont(new Font("verdana",Font.BOLD,18));
		t3.setVisible(false);
		t4.setVisible(false);
		t5.setVisible(false);
		t3.setForeground(Color.blue);
		t4.setForeground(Color.blue);
		t5.setForeground(Color.blue);
		fr.add(t3);
		fr.add(t4);
		fr.add(t5);

		c1 = new Checkbox("Discount 10%");
		c1.setBounds(700,390,150,30);
		c1.setFont(new Font("verdana",Font.BOLD,16));
		c1.setForeground(Color.yellow);
		c1.setVisible(false);
		c1.setBackground(Color.black);
		c1.addItemListener(this);
		fr.add(c1);

		l4 = new JLabel("Grand Total");
		l4.setBounds(700,430,150,30);
		l4.setForeground(Color.green);
		l4.setFont(new Font("verdana",Font.BOLD,18));
		l4.setVisible(false);
		fr.add(l4);	

		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			if(t1.getText().trim().length()==0)
			{
				JOptionPane.showMessageDialog(fr,"Please enter UserName");
			}
			else if(t2.getText().trim().length()==0)
			{
				JOptionPane.showMessageDialog(fr,"Please enter Password");
			}
			else
			{
				try
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","career");
					ps = con.prepareStatement("select * from mycart where username=? and password=?");
					ps.setString(1,t1.getText());
					ps.setString(2,t2.getText());
					rs = ps.executeQuery();
					
					if(rs.next()==true)
					{
						JOptionPane.showMessageDialog(fr,"Welcome, "+t1.getText().toUpperCase());
						b2.setEnabled(true);
						cb.setVisible(true);
						ls1.setVisible(true);
						ls2.setVisible(true);
						b3.setVisible(true);
						b4.setVisible(true);
						b5.setVisible(true);
						t3.setVisible(true);
						t4.setVisible(true);
						t5.setVisible(true);
						c1.setVisible(true);
						l4.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(fr,"sorry! username or password is invalid");
					}
				}
				catch(Exception e)
				{
				}
			}
		}

		if(ae.getActionCommand().equals("Logout"))
		{
			t1.setText("");
			t2.setText("");
			cb.setVisible(false);
			ls1.setVisible(false);
			ls2.setVisible(false);
			b2.setEnabled(false);
			b3.setVisible(false);
			b4.setVisible(false);
			b5.setVisible(false);
			t3.setVisible(false);
			t4.setVisible(false);
			t5.setVisible(false);
			c1.setVisible(false);
			l4.setVisible(false);
		}

		if(ae.getSource()==b3)
		{
			tot=0;
			for(int i=0 ; i<ls2.getItemCount() ; i++)
			{
				String pr = ls2.getItem(i);
				tot = tot + Double.parseDouble(pr);
				gtot = tot;
				if(c1.getState()==true)
				{
					dis = tot * 10 / 100;
					gtot = tot - dis;
					t4.setText(""+dis);
				}
				t3.setText(""+tot);
				t5.setText(""+gtot);
			}
		}
		
		if(ae.getSource()==b4)
		{
			try
			{
				if(ls1.getItemCount()!=0)
				{
					if(t3.getText().length()!=0)
					{
						int indx = ls1.getSelectedIndex();
						if(indx==-1)
						{
							JOptionPane.showMessageDialog(fr,"Please select an Item");
						}
						else
						{
							int pr = Integer.parseInt(ls2.getItem(indx));
							tot = tot - pr;
							gtot = tot;
							if(c1.getState()==true)
							{
								dis = tot * 10 / 100;
								gtot = tot - dis;
								t4.setText(""+dis);
							}
							t3.setText(""+tot);
							t5.setText(""+gtot);
							ls1.remove(indx);
							ls2.remove(indx);
						}
					}
					else
					{
						int indx = ls1.getSelectedIndex();
						ls1.remove(indx);
						ls2.remove(indx);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(fr,"Your cart is empty");
				}
			}
			catch(Exception e)
			{
			}	
		}

		if(ae.getSource()==b5)
		{
			if(ls1.getItemCount()!=0)
			{
				ls1.clear();
				ls2.clear();
				tot=0;
				dis=0;
				gtot=0;
				t3.setText("");
				t4.setText("");
				t5.setText("");
				c1.setState(false);
				cb.select(0);
			}
			else
			{
				JOptionPane.showMessageDialog(fr,"Your cart is already empty");
			}
		}

		if(ae.getSource()==bb1)
		{
			if(tt1.getText().trim().length()==0)
			{
				JOptionPane.showMessageDialog(fr,"Please enter UserName");
			}
			else if(tt2.getText().trim().length()==0)
			{
				JOptionPane.showMessageDialog(fr,"Please enter Password");
			}
			else
			{
				try
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","career");
					ps = con.prepareStatement("insert into mycart values(?,?)");
					ps.setString(1,tt1.getText());
					ps.setString(2,tt2.getText());
					int z = ps.executeUpdate();
					if(z>0)
					{
						JOptionPane.showMessageDialog(fr,"User Created Succesfully");
					}
					else
					{
						JOptionPane.showMessageDialog(fr,"User Not Created");
					}	
					con.close();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(fr,"user already exists");
				}
			}
		}

		if(ae.getSource()==bb2)
		{
			tt1.setText("");
			tt2.setText("");
		}
	}

	public void itemStateChanged(ItemEvent ie)
	{
		String str = ie.getItem().toString();	

		if(str.equals("Discount 10%"))
		{
			if(c1.getState()==true)
			{
				dis = tot * 10 / 100;
				gtot = tot - dis;
				t4.setText(""+dis);
				t5.setText(""+gtot);
			}
			else
			{
				dis=0;
				gtot = tot;
				t4.setText(""+dis);
				t5.setText(""+gtot);
			}
		}
		else
		{
			t3.setText("");
			t4.setText("");
			t5.setText("");
			c1.setState(false);
			tot=0;
			dis=0;
			gtot=0;
			int indx = cb.getSelectedIndex();
			String itm = cb.getSelectedItem().toString();
			ls1.addItem(itm);
			ls2.addItem(price[indx]+"");
		}
	}

	public static void main(String args[])
	{
		new mycart();
	}
}
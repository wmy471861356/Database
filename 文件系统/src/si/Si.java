/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si;

import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ������
 */
public class Si {
    static Statement sta=null;
        static String sql=null;  
        static ResultSet rs;
        private static void createTable(){
        try{
            sql="create table students(Sno int primary key,Sname varchar(10),Ssex varchar(6) CHECK (Ssex='��' OR Ssex='Ů'),Sage int CHECK (Sage>14 AND Sage<46),Sdept varchar(10))";
            sta.execute(sql);  //������Ϣ��
        }
        catch(SQLException e){
           System.out.println("ѧ����Ϣ���Ѵ���");
        }
        try{
            sql="create table reward(Sno int primary key,Reward varchar(50),constraint fk_Sno foreign key(Sno) references students(Sno))";
            sta.execute(sql);  //����������
        }
        catch(SQLException e){
            System.out.println("�������Ѵ���");
        } 
        //����ԭʼ����
        sql="insert into students values"+"(20100001,'ʷ����','Ů',20,'�����'),(20100100,'������','��',21,'��е'),(20100234,'����','��',21,'����')";
        try {
            sta.execute(sql);
            sql="insert into reward values"+"(20100001,'��'),(20100100,'��'),(20100234,'��ɽ�')";
            sta.execute(sql);
            System.out.println("������ɣ�");
        } catch (SQLException ex) {
            Logger.getLogger(Si.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	public static void main(String[] args) throws IOException {
            Scanner temp = new Scanner(System.in);
            Connection con=null;
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");//����JDBC����
        }
        catch(Exception e){}
        //�������ݿ�
        try{
            String uri="jdbc:derby:StudentMessage;create=true";
            con=DriverManager.getConnection(uri);
            sta=con.createStatement();
        }
        catch(Exception e){
            System.out.print("���ݿ�����ʧ��");
        }
        //createTable();  //��������ԭʼ����
	while (true) {
            //����ѡ��
	    System.out.println("1 �鿴ѧ����Ϣ");
	    System.out.println("2 ¼��ѧ����Ϣ");
	    System.out.println("3 �޸�ѧ����Ϣ");
	    System.out.println("4 ɾ��ѧ����Ϣ");
	    System.out.println("5 �˳�ϵͳ");
            String choice = temp.nextLine();
	    //�ж�
	    switch (choice) {
	    case "1":
            System.out.println("�鿴��ָ��ѧ����Ϣ/����ѧ����Ϣ(1/2)");
            String confirm = temp.nextLine();
            if(confirm.equals("1") ){
		queryone();
	    }else{
		query();
	    }
	        break;
	    case "2":
	        add();		
	        break;
	    case "3":
	        modify();
	        break;
	    case "4":
		delete();
		break;
	    case "5":
		System.out.println("��л����ʹ�ã��ټ���");
		System.exit(0);
		default:
		System.out.println("������������������ѡ��");
		}
		}
	}
	//��ѯ����ѧ����Ϣ
	private static void query(){
        try{
            rs=sta.executeQuery("select * from students,reward where students.Sno=reward.Sno");
            while(rs.next()){  
                int number=rs.getInt(1);
                System.out.print(number+"\t");
                String name=rs.getString(2);
                System.out.print(name+"\t");
                String sex=rs.getString(3);
                System.out.print(sex+"\t");
                int age=rs.getInt(4);
                System.out.print(age+"\t");
                String dept=rs.getString(5);
                System.out.print(dept+"\t");
                String reward=rs.getString(7);
                System.out.print(reward+"\t");
                System.out.print("\n");
            }
        }catch(Exception e){
                System.out.println("��ѯ����");
        }
	}
        //��ѯָ��ѧ��
        private static void queryone(){
                Scanner temp = new Scanner(System.in);
		System.out.println("��������Ҫ�鿴��ѧ��ѧ�ţ�");
		String nu = temp.nextLine();
                int num=Integer.parseInt(nu);
                System.out.println("��������Ҫ�鿴��ѧ��������");
                String na = temp.nextLine();
            try {
                int flag=0;
                rs=sta.executeQuery("select * from students,reward where students.Sno=reward.Sno AND students.Sno="+num+"");
                while(rs.next()){
                flag=1;
                int number=rs.getInt(1);
                System.out.print(number+"\t");
                String name=rs.getString(2);
                System.out.print(name+"\t");
                String sex=rs.getString(3);
                System.out.print(sex+"\t");
                int age=rs.getInt(4);
                System.out.print(age+"\t");
                String dept=rs.getString(5);
                System.out.print(dept+"\t");
                String reward=rs.getString(7);
                System.out.print(reward+"\t");
                System.out.print("\n");
                }
                if(flag==0){
                    System.out.println("�鿴ʧ�ܣ�");
                    System.out.println("��ѧ��������");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Si.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
	//���ѧ����Ϣ
	private static void add(){
		Scanner temp = new Scanner(System.in);
		String nu;
		System.out.println("��������ͬѧѧ�ţ�");
	        nu = temp.nextLine();
                int num;
                num=Integer.parseInt(nu);
            try {
                int flag=0;
                rs=sta.executeQuery("select * from students where Sno="+num+"");
                while(rs.next()){
                    flag=1;
                }
                if(flag==1){
                    System.out.println("��ѧ���Ѵ��ڣ�");
                    return;
                }
            } catch (SQLException e) {           
            }
		System.out.println("��������ͬѧ������");
		String name = temp.nextLine();
                String sex;
                System.out.println("��������ͬѧ�Ա�");
                sex = temp.nextLine();
                String ag;
                System.out.println("��������ͬѧ���䣺");
                ag = temp.nextLine();
                int age=Integer.parseInt(ag);
		System.out.println("��������ͬѧרҵ��");
		String dept = temp.nextLine();
                System.out.println("��������ͬѧ�����");
		String reward = temp.nextLine();
            try {
                sql="insert into students values"+"("+num+",'"+name+"','"+sex+"',"+age+",'"+dept+"')";
                sta.execute(sql);
                sql="insert into reward values"+"("+num+",'"+reward+"')";
                sta.execute(sql);
                System.out.println("¼��ѧ����Ϣ�ɹ���");	
            } catch (SQLException ex) {
                System.out.println("¼��ʧ�ܣ�");
                System.out.println("�����������Ա��Ƿ����������");
                System.out.println("���䣺15��-45��");
                System.out.println("�Ա𣺷��м�Ů");
            }
            
	}
	//�޸�ѧ����Ϣ
	private static void modify(){
		Scanner temp = new Scanner(System.in);
		System.out.println("��������Ҫ�޸ĵ�ѧ��ѧ�ţ�");
		String nu = temp.nextLine();
                int num=Integer.parseInt(nu);       
                System.out.println("��������Ҫ���ĵ�������");
                String name = temp.nextLine();
                //�ж�ѧ���Ƿ����
                try {
                    sql="select * from students where Sno="+num+"";
                    rs=sta.executeQuery(sql);
                    int flag=0;
                while(rs.next()){
                    flag=1;
                }
                if(flag==0){
                    System.out.println("��ѧ�������ڣ�");
                    return;
                }
                } catch (SQLException e) {
                    System.out.println("�޸�ʧ�ܣ�");
                    return;
                }
                System.out.println("��������Ҫ���ĵ�ͬѧ�Ա�");
                String sex = temp.nextLine();
                System.out.println("��������Ҫ���ĵ�ͬѧ���䣺");
                String ag=temp.nextLine();
                int age=Integer.parseInt(ag);
                System.out.println("��������Ҫ���ĵ�רҵ��");
                String dept = temp.nextLine();
                System.out.println("��������Ҫ���ĵĽ��");
	        String reward = temp.nextLine();
            try {
                sql="update students set Sname='"+name+"',Ssex='"+sex+"',Sage="+age+" ,Sdept='"+dept+"'where Sno="+num+"";
                sta.execute(sql);
                sql="update reward set Reward='"+reward+"' where Sno="+num+"";
                sta.execute(sql);
                System.out.println("ѧ����Ϣ���³ɹ���");
            } catch (SQLException ex) {
                System.out.println("�޸�ʧ�ܣ�");
                System.out.println("�����������Ա��Ƿ����������");
                System.out.println("���䣺15��-45��");
                System.out.println("�Ա𣺷��м�Ů");
            }
	}
	//ɾ��
	private static void delete(){
		Scanner temp = new Scanner(System.in);
		System.out.println("��������Ҫɾ����ѧ��ѧ�ţ�");
		String nu = temp.nextLine();
                int num=Integer.parseInt(nu);
                System.out.println("��������Ҫɾ����ѧ��������");
		String na = temp.nextLine();
            try {
                rs=sta.executeQuery("select * from students,reward where students.Sno=reward.Sno AND students.Sno="+num+"");
                int flag=0;
                while(rs.next()){
                flag=1;
                System.out.println("��ȷ��ɾ����ѧ����Ϣ(Y/N)��");    
                int number=rs.getInt(1);
                System.out.print(number+"\t");
                String name=rs.getString(2);
                System.out.print(name+"\t");
                String sex=rs.getString(3);
                System.out.print(sex+"\t");
                int age=rs.getInt(4);
                System.out.print(age+"\t");
                String dept=rs.getString(5);
                System.out.print(dept+"\t");
                String reward=rs.getString(7);
                System.out.print(reward+"\t");
                System.out.print("\n");
                }
                if(flag==0){
                    System.out.println("ɾ��ʧ�ܣ�");
                    System.out.println("��ѧ��������");
                    return;
                }
                String confirm = temp.nextLine();
                if(confirm.equals("y") || confirm.equals("Y")){
                    sta.execute("delete from reward where Sno="+num+"");
                    sta.execute("delete from students where Sno="+num+"");
                    
                    System.out.println("ɾ���ɹ���");
                }
                else{
		    System.out.println("��ȡ��ɾ����");
		    return;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Si.class.getName()).log(Level.SEVERE, null, ex);
            }
		
	}
 
}

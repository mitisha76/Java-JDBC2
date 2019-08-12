
import java.util.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
class Data
{
	Statement stmt;
	Connection conn;
	Scanner sc;
	int choice,choice1,count=0,useranswer;
	String name,email,password,registerdate,query;
	ArrayList<Integer> answer=new ArrayList<Integer>();
	ArrayList<Integer> answer1=new ArrayList<Integer>();
	Data()
	{

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz","root","");		
			stmt=conn.createStatement();
			sc=new Scanner(System.in);

		}
		catch(Exception  e)
		{
			System.out.println("Connection failed");
		}
	}
	void input()
	{
		try
		{
			System.out.println("---Lets Begin The Quiz!!---");
			System.out.println("Enter Name");
			name=sc.next();
			System.out.println("Enter Email");
			email=sc.next();
			System.out.println("Enter Password");
			password=sc.next();
			System.out.println("Enter Registration Date");
			registerdate=sc.next();
			query="insert into userdata values('"+name+"','"+email+"','"+password+"','"+registerdate+"')";
			stmt.execute(query);
			System.out.println("----Login Successful-----");
			System.out.println("Enter 1 for Taking Quiz...");
			System.out.println("Enter 2 for Exit...");
			
			
			
			choice=sc.nextInt();
			if(choice==1)
				
			{
				query="select * from questions";
				ResultSet pointer=stmt.executeQuery(query);
				System.out.println("Each Question carry 10 marks!!!!");
				while(pointer.next())
				{
					System.out.println("Question "+pointer.getInt(1)+" "+pointer.getString(2));
					System.out.println("Options are");
					System.out.println("1 "+pointer.getString(3));
					System.out.println("2 "+pointer.getString(4));
					System.out.println("3 "+pointer.getString(5));
					System.out.println("4 "+pointer.getString(6));
					
					answer.add(pointer.getInt(7));
					System.out.println("Enter your answer");
					useranswer=sc.nextInt();
					//System.out.println(useranswer);
					answer1.add(useranswer);
				}
				for(int i=0;i<answer.size();i++)
				{
					if(answer1.get(i)==(answer.get(i)))
					{
						count++;
					}
					int score;
					score=count*10;
				}
				int score;
				score=count*10;
				System.out.println("Your Score : "+score);
				System.out.println("******************");
				
				if(score>0 && score<40)
				{
					System.out.println("-------Your score is below Average-------");
				}
				
				else if(score>40 && score<70)
				{
					System.out.println("-------Your score is Average--------");
				}
				
				else
				{
					System.out.println("--------Your score is Excellent!!-------");
				}
				System.out.println("**********************");
				System.out.println("Enter 1 for Displaying Your Submitted Answers...");
				System.out.println("Enter 2 for Displaying Correct Answers...");
				System.out.println("Enter 3 for Exiting...");
				
				
				boolean x=true;
				
				while(x==true)
				{
				choice1=sc.nextInt();
				if(choice1==1)
				{
					System.out.println("Yor answers are : ");
					for(int i=0;i<answer1.size();i++)
					{
						System.out.println("Answer "+(i+1)+" "+answer1.get(i));
					}
				}
				else if(choice1==2)
				{
					System.out.println("Correct answers are : ");
					for(int i=0;i<answer.size();i++)
					{
						System.out.println("Answer "+(i+1)+" "+answer.get(i));
					}					
				}
				
				else
			
				{
					x=false;
					System.out.println("------Exiting------");
					System.exit(0);
				}
			}
	   }
			
			else
			{
				System.out.println("------Exiting-----");
				System.exit(0);
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Error "+e.getMessage());
		}
	}
}
public class Quiz1
{
	public static void main(String[] args)
	{
		Data d=new Data();
		d.input();
	}
}

import java.net.*;
import java.io.*;
 
public class GuessServer {
 
	public final static int DEFAULT_PORT = 1234;
 
	public static void main(String[] args) {
		String s="";
		String A1="";
		String B1="";
		String tmp;
		String a="";
		int g=0;
		String [] arr = new String[4];
		String [] ans = new String[4];
		int level = 1, num_count = 4;
		int port = DEFAULT_PORT;     
		if (args.length > 0) {
		try {
			port = Integer.parseInt(args[0]);
			if (port < 0 || port >= 65536) {
				System.out.println("Port must between 0 and 65535");
			return;      
			}
		}   
		catch (NumberFormatException e) {
			// use default port
		}  
 
	}     
	try {
    
		ServerSocket server = new ServerSocket(port);
		System.out.println("�إ�ServerSocket���\"); 
		Socket connection = null;
		PrintWriter out = null;
		BufferedReader networkIn = null;
		while (true) {
        
		try {
			System.out.println("����Client�s�u...");
			connection = server.accept();
			System.out.println(connection.getInetAddress().getHostName()+"�s�u�i��");
         
			networkIn = new BufferedReader(
				new InputStreamReader(connection.getInputStream()));
			BufferedReader userIn = new BufferedReader(
				new InputStreamReader(System.in));
			out = new PrintWriter(connection.getOutputStream());
      
	        //�L�X�C���W�h
			for(int i = 0; i < 38; ++i) {
				System.out.print("��");
			}
			System.out.print("\n���@\t\t\t\t\t\t�@                        ��\n���@���k�����G                   \t\t\t\t\t�@��");
			System.out.print("\n���@�@�@�q�Ʀr�A�{���H���ͦ��@�ռƦr�A���a�q�X���T���קY�i�i�J�U�@�ŧO�C�@��\n���@\t\t\t\t\t\t�@                        ��\n");
			
			for(int i = 0; i < 38; ++i) {
				System.out.print("��");
			}
		
			System.out.println("\n\n\t\t���@GAME START�@��\n");
			
			s=networkIn.readLine();
			System.out.print("�п�J�����q���Ʀr(1-9999):");
			a = userIn.readLine();  //�����q���Ʀr
			
			out.println("ok");
			out.flush();
		
			System.out.println("�}�l�q�Ʀr:");
			System.out.println("\n\n\t\t���@��e  Level "+level+"�@��\n");
		    tmp = a;
			ans[0] = tmp.substring(0,1);
			ans[1] = tmp.substring(1,2);
			ans[2] = tmp.substring(2,3);
			ans[3] = tmp.substring(3,4);
			while (true) {
				System.out.print("����Client��J...");
				
				s=networkIn.readLine();   //�ĤH�q���Ʀr
				if(s.equals("end"))
				{
					System.out.println("Server�����s�u");
					break;
				}
				System.out.println("\r                  \rClient�q���Ʀr�O:"+s+" �A���ƬO:"+a+"\n");
				arr[0] = s.substring(0,1);
			    arr[1] = s.substring(1,2);
				arr[2] = s.substring(2,3);
				arr[3] = s.substring(3,4);
				
				int A = 0, B = 0;
				for(int i = 0; i < num_count; ++i) {
					for(int j = 0; j < num_count; ++j) {
						if(arr[i].equals(ans[j]) && i == j) {++A;}
						else if(arr[i].equals(ans[j])) {++B;}
					}
				}
				
				if(A == num_count) 
				{
					level+=1;;
				}
				
				//�@10�ŧO
				if(level >= 10) {
					System.out.println("\n\n\t\t���@���ߧA�A�C���w�����I�@��");
				}
				
				if(A==4) s="�A�q��F";
				if(A!=4) s="�A�q���F����";
				A1 = String.valueOf(A);
				B1 = String.valueOf(B);
				out.println( A1 );
				out.println( B1 );
				out.println( s );
				out.flush();
				if(s.equals("�A�q��F"))
				{
					System.out.println("ClientĹ�F�A�A��F�A�C������");
					break;
				}
				System.out.print("��J1-9999���Ʀr:");
				String theLine = userIn.readLine();
				out.println(theLine);
				out.flush();
				if (theLine.equals("end")) break;
				A1=networkIn.readLine();
				B1=networkIn.readLine();
				s=networkIn.readLine();
				System.out.println("�q�����G:"+A1+"A"+B1+"B"+s);
				if(s.equals("�A�q��F"))
				{
					System.out.println("�AĹ�F�AClient��F�A�C������");
					break;
				}
			} 
				 
			System.out.println("Client�����s�u");    
			connection.close();
		}
		catch (IOException e) {}
			finally {
				try {
				   if (connection != null) connection.close();
				}
				catch (IOException e) {}          
			    }
         
		}  // end while
       
	}  // end try
	catch (IOException e) {
		System.err.println( e );
	} // end catch
 
	} // end main
 
} // end DaytimeServer
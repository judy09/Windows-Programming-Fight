import java.net.*;
import java.io.*;
 
public class GuessClient {
 
	public static void main(String[] args) {
 
		String hostname = "localhost";
		String s="";
		String A1="";
		String B1="";
		String tmp;
		String a="";
		int g=0;
		int end = 0, restart = 0;
		String [] arr = new String[4];
		String [] ans = new String[4];
		int level = 1, num_count = 4;

		if (args.length > 0) {
			hostname = args[0];
		}
 
		PrintWriter out = null;
		BufferedReader networkIn = null;
		try {
			Socket theSocket = new Socket(hostname, 1234);
			networkIn = new BufferedReader(
			 new InputStreamReader(theSocket.getInputStream()));
			BufferedReader userIn = new BufferedReader(
			 new InputStreamReader(System.in));
			out = new PrintWriter(theSocket.getOutputStream());
			System.out.println("�w�s�u��GuestServer\n");
			
			 //�L�X�C���W�h
			for(int i = 0; i < 38; ++i) {
				System.out.print("��");
			}
			System.out.print("\n���@\t\t\t\t\t\t�@                        ��\n���@���k�����G                    \t\t\t\t\t�@��");
			System.out.print("\n���@�@�@�q�Ʀr�A�{���H���ͦ��@�ռƦr�A���a�q�X���T���קY�i�i�J�U�@�ŧO�C�@��\n���@\t\t\t\t\t\t�@                        ��\n");
			
			for(int i = 0; i < 38; ++i) {
				System.out.print("��");
			}
		
			System.out.println("\n\n\t\t���@GAME START�@��\n");
			System.out.print("�п�J�����q���Ʀr(1-9999):");
			a = userIn.readLine();  //�����q��
			
			out.println("ok");
			out.flush();
			
			tmp = a;
			
			ans[0] = tmp.substring(0,1);
			ans[1] = tmp.substring(1,2);
			ans[2] = tmp.substring(2,3);
			ans[3] = tmp.substring(3,4);
			
			s=networkIn.readLine(); 
			
			System.out.println("�}�l�q�Ʀr:");
			System.out.println("\n\n\t\t���@��e  Level "+level+"�@��\n");
			while (true) {
				System.out.print("��J1-9999���Ʀr:");
				String theLine = userIn.readLine(); //�ڿ�J���Ʀr
				
				out.println(theLine);
				out.flush();
				if (theLine.equals("end")) break;
					A1=networkIn.readLine();
					B1=networkIn.readLine();
					s=networkIn.readLine();
					System.out.println("�q�����G:"+A1+"A"+B1+"B"+s);
				if(s.equals("�A�q��F"))
				{
					System.out.println("�AĹ�F�AServer��F�A�C������");
					break;
				}
				System.out.print("����Server��J...");
				s=networkIn.readLine();   //�ڭn�q���Ʀr
				arr[0] = s.substring(0,1);
			    arr[1] = s.substring(1,2);
				arr[2] = s.substring(2,3);
				arr[3] = s.substring(3,4);
				if(s.equals("end"))
				{
					System.out.println("Server�����s�u");
					break;
				}
				System.out.println("\r                 \rServer�q���Ʀr�O:"+s+" �A���ƬO:"+a+"\n");
				
				
				//arr���a����->i ans�{���X�D->j
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
					end = 999;
				}
				if(A==4) s="�A�q��F";
				if(A!=4) s="�A�q���F";
				
				A1 = String.valueOf(A);
				B1 = String.valueOf(B);
				out.println( A1 );
				out.println( B1 );
				out.println( s );
				out.flush();
				if(A==4 && B==0)
				{
					System.out.println("ServerĹ�F�A�A��F�A�C������");
					break;
				}
			}
      
		}  // end try
		catch (IOException e) {
			System.err.println( e );
		}
		finally {
		try {
			if (networkIn != null) networkIn.close(); 
			if (out != null) out.close(); 
		}
		catch (IOException e) {}
		}	
	}  // end main
}  // end EchoClient
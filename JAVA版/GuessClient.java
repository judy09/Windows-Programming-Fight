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
			System.out.println("已連線到GuestServer\n");
			
			 //印出遊戲規則
			for(int i = 0; i < 38; ++i) {
				System.out.print("＃");
			}
			System.out.print("\n＃　\t\t\t\t\t\t　                        ＃\n＃　玩法說明：                    \t\t\t\t\t　＃");
			System.out.print("\n＃　　　猜數字，程式隨機生成一組數字，玩家猜出正確答案即可進入下一級別。　＃\n＃　\t\t\t\t\t\t　                        ＃\n");
			
			for(int i = 0; i < 38; ++i) {
				System.out.print("＃");
			}
		
			System.out.println("\n\n\t\t＊　GAME START　＊\n");
			System.out.print("請輸入讓對方猜的數字(1-9999):");
			a = userIn.readLine();  //給對方猜的
			
			out.println("ok");
			out.flush();
			
			tmp = a;
			
			ans[0] = tmp.substring(0,1);
			ans[1] = tmp.substring(1,2);
			ans[2] = tmp.substring(2,3);
			ans[3] = tmp.substring(3,4);
			
			s=networkIn.readLine(); 
			
			System.out.println("開始猜數字:");
			System.out.println("\n\n\t\t★　當前  Level "+level+"　★\n");
			while (true) {
				System.out.print("輸入1-9999的數字:");
				String theLine = userIn.readLine(); //我輸入的數字
				
				out.println(theLine);
				out.flush();
				if (theLine.equals("end")) break;
					A1=networkIn.readLine();
					B1=networkIn.readLine();
					s=networkIn.readLine();
					System.out.println("猜的結果:"+A1+"A"+B1+"B"+s);
				if(s.equals("你猜對了"))
				{
					System.out.println("你贏了，Server輸了，遊戲結束");
					break;
				}
				System.out.print("等待Server輸入...");
				s=networkIn.readLine();   //我要猜的數字
				arr[0] = s.substring(0,1);
			    arr[1] = s.substring(1,2);
				arr[2] = s.substring(2,3);
				arr[3] = s.substring(3,4);
				if(s.equals("end"))
				{
					System.out.println("Server結束連線");
					break;
				}
				System.out.println("\r                 \rServer猜的數字是:"+s+" 你的數是:"+a+"\n");
				
				
				//arr玩家答案->i ans程式出題->j
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
				//共10級別
				
				if(level >= 10) {
					System.out.println("\n\n\t\t▲　恭喜你，遊戲已結束！　▲");
					end = 999;
				}
				if(A==4) s="你猜對了";
				if(A!=4) s="你猜錯了";
				
				A1 = String.valueOf(A);
				B1 = String.valueOf(B);
				out.println( A1 );
				out.println( B1 );
				out.println( s );
				out.flush();
				if(A==4 && B==0)
				{
					System.out.println("Server贏了，你輸了，遊戲結束");
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
//A173586 Shameer Ali
//whole program created by me, Shameer Ali
import java.util.*;
class DitsDahs{
	/*static HashMap<String, String> morse = new HashMap(){{
		code.forEach((k, v) -> {put(v,k); tr.addNode(v,k);});}};*/
	public static void main(String[] args){
		Scanner a= new Scanner(System.in);
		Translate trans = new Translate();
		trans.read(System.getProperty("user.dir")+"\\Code.dat");
		List <String> select=Arrays.asList("Menu:","\n\t1. Send Morse Message","\n\t2. Recieve Morse Message",
												"\n\t3. Print Letters and Morse Code","\n\t4. Exit","\n\nInput code: ");
		while(true){
			select.forEach(System.out::print);
			try {
				int input=Integer.parseInt(a.nextLine());
				if(input==1)
					trans.tomorse();
				else if(input==2)
					trans.totext();
				else if(input==3)
					trans.tree();
				else if(input==4){
					System.out.println("Bye dits-dash..");
					System.exit(0);
				}
				else
					System.out.println("Enter a Valid Input!");
			}
			catch(Exception e){
				System.out.println("Enter a Valid Input!");
			}
		}
	}
}
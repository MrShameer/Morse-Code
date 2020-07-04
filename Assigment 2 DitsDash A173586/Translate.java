import java.util.*;
import java.io.*;
import javax.swing.*;
public class Translate{
	Scanner a= new Scanner(System.in);
//2 hashmap. satu jadikan normal character sebagai key. lagi satu jadikan morse sebagai key
// addNode tu untuk buat tree
	HashMap<String, String> code = new HashMap();
	HashMap<String, String> morse = new HashMap();
	Tree tree = new Tree();
	JFileChooser j = new JFileChooser();

	//untuk baca file dat. 
	public void read(String dir){
		String k, v;
		try{
			Scanner reader = new Scanner(new File(dir));
			if(reader!=null){
				while (reader.hasNext()){
					k= reader.next();
					v= reader.next();
					tree.addNode(k,v);
					code.put(k,v);
					morse.put(v,k);
				}
				reader.close();
			}
		}catch(FileNotFoundException e){
			System.out.println("File Not Found. Please open Translation Script.");
			j.setDialogTitle("Open Morse Data");
			int r = j.showOpenDialog(null);
			if (r == JFileChooser.APPROVE_OPTION)
				read(j.getSelectedFile().getAbsolutePath());
			else{
				System.out.println("This Program Cannot run without Translation Script");
				System.exit(0);
			}
		}
	}

	//nak tukr jadi morse code
	public void tomorse(){
		String data="";
		int lines=0, word=0, chars=0, symbol=0, num=0;
		LinkedList <String> inmorse = new LinkedList();
		Stack <Integer> nums = new Stack <Integer>();
		while(!data.equals("EOM")){
			data=a.nextLine();
			data=data.toUpperCase();
			if(word==0&&!data.equals("VV")){
				System.out.println("Message Must Start With \"VV\"");
				return;
			}
			lines++;
			for(String decode : data.split("\\s+")){
				word++;
				for(String letter: decode.split("")){
					chars++;
					if(code.containsKey(letter)){
						inmorse.add(code.get(letter)+" ");
						if(letter.matches("^.*[^a-zA-Z0-9 ].*$"))
							symbol++;
						if(letter.matches(".*\\d+.*"))
							num++;
					}
					else
						System.out.println("Sorry Can't Decode "+letter);
				}
				inmorse.add("  ");
			}
			inmorse.add("\n");
		}
		nums.add(num);	nums.add(symbol);	nums.add(chars);	nums.add(word);	nums.add(lines);
		while(nums.size()!=0){
			for(String n : String.valueOf(nums.pop()).split("")){
				inmorse.add(code.get(n)+" ");
			}
			inmorse.add("\n");
		}
		inmorse.add(". --- -\n\n");
		System.out.println();
		inmorse.forEach(System.out::print);
	}

	//nak tukr jadi letter
	public void totext(){
		String data="",toword="";
		int lines=0, word=0, chars=0, symbol=0, num=0, consists=0;
		LinkedList <String> inword = new LinkedList(), check= new LinkedList(), real= new LinkedList();
		while(!data.equals("EOM")){
			if(data.equals("EOT")){
				System.out.println("Cannot Provide Detailed Summary!");
				return;
			}
			data=a.nextLine();
			data=data.toUpperCase();
			if(word==0&&!data.equals("...- ...-")){
				System.out.println("Message Must Start With \"...- ...-\"");
				return;
			}
			lines++;
			for(String decode : data.split("\\s{2}")){
				word++;
				data="";
				for(String letter : decode.split("\\s")){
					chars++;
					if(morse.containsKey(letter)){
						toword=morse.get(letter);
						if(toword.matches("^.*[^a-zA-Z0-9 ].*$"))
							symbol++;
						if(toword.matches(".*\\d+.*"))
							num++;
						data=data+toword;
					}
					else{
						System.out.println("Sorry Can't Decode "+letter);
						return;
					}
				}
				inword.add(data+" ");
			}
			inword.add("\n");
		}
		check.add(lines+" ");	check.add(word+" ");	check.add(chars+" ");
		check.add(symbol+" ");	check.add(num+" ");

		while(!data.equals("EOT")){
			data=a.nextLine();
			data=data.toUpperCase();
			String [] words=data.split("\\s+");
			data="";
			for(String letter : words){
				if(morse.containsKey(letter))
					data=data+morse.get(letter);
				else{
					System.out.println("Sorry Can't Decode "+letter);
					return;
				}
			}
			inword.add(data+"\n");
			if(!data.equals("EOT"))
				real.add(data+" ");
		}
		System.out.println();
		inword.forEach(System.out::print);
		System.out.println();
		check.forEach(System.out::print);
		System.out.println();

		if(real.equals(check))
			System.out.println("Result: Consistent Summary\n");
		else
			System.out.println("Result: Inconsistent Summary\n");
	}

	//kluarkan tree
	public void tree(){
		tree.down=0;
		tree.inOrder(tree.root);
		System.out.println("\n");
	}
}
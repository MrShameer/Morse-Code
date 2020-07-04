class Tree{
	Node root = new Node();
	int down;
	//ni dia akan susun morse code dalam tree dgn tgok . atau -
	public void addNode(String key, String value){
		Node Now = root;
		for(String n : value.split("")){
			if(n.equals(".")){
				if (Now.left == null)
					Now.left = new Node();
				Now = Now.left;
			}
			else{
				if (Now.right == null)
					Now.right = new Node();
				Now = Now.right;
			}
		}
		Now.key=key;	Now.value=value;
	}

	//kluarkan tree tu dalam bentuk inorder
	void inOrder(Node now){
		if(now!=null){
			inOrder(now.left);
			if(now.key!=null){
				down++;
				System.out.printf("%-15s",now);
				if(down%5==0)
					System.out.println();
			}
			inOrder(now.right);
		}
	}
}

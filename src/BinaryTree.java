class Node {
	int data;
	Node left;
	Node right;
	
	public Node(int data){
		this.data = data;
		left = null;
		right = null;
	}

	@Override
	public String toString() {
		// Very Interesting feature - You can print the whole tree with this ! - Yes, due to recursion
		return "Node [data=" + data + ", left=" + left + ", right=" + right
				+ "]";
	}
}

public class BinaryTree { 
	Node root;
	
	public BinaryTree(Node root){
		this.root = root;
	}
	
	public BinaryTree() {

	}

	public void printInorder(){
		inOrder(root);
	}
	
	public void printPreorder(){
		preOrder(root);
	}
	
	public void printPostorder(){
		postOrder(root);
	}
	
	private void inOrder(Node root) {
		if(null != root){
			inOrder(root.left);
			System.out.println(root.data);
			inOrder(root.right);
		}
	}

	private void preOrder(Node root) {
		if(null != root){
			System.out.println(root.data);
			preOrder(root.left);
			preOrder(root.right);
		}
	}

	private void postOrder(Node root) {
		if(null != root){
			postOrder(root.left);
			postOrder(root.right);
			System.out.println(root.data);
		}
	}

	@Override
	public String toString() {
		return "BinaryTree [root=" + root + "]";
	}

	public static void main(String[] args) {
		Node root = new Node(10);
		root.left = new Node(5);
		root.right = new Node(13);
		root.left.left = new Node(3);
		root.left.right = new Node(7);
		root.right.left = new Node (11);
		root.right.right = new Node(15);
		BinaryTree tree = new BinaryTree(root);
		tree.printInorder();
	}
}


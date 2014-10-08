public class BinarySearchTree extends BinaryTree {

	public BinarySearchTree(Node root) {
		super(root);
	}
	
	public BinarySearchTree(){
		super();
	}

	public void insert(Node node){
		root = insertNode(root, node);
	}
	
	public void delete(Node node){
		root = deleteNode(root, node);
	}
	
	public boolean search(Node node){
		return searchNode(root, node);
	}
	
	public Node maximum(){
		return maximumNode(root);
	}
	
	public Node minimum(){
		return minimumNode(root);
	}
	

	private Node deleteNode(Node root, Node node){
		// For reference - http://webdocs.cs.ualberta.ca/~holte/T26/del-from-bst.html
		if(null == root){
			return null;
		} else if(node.data == root.data){
			if(null == root.left && null == root.right){
				return null;
			} else if(null == root.left && null != root.right){
				root.data = root.right.data;
				root.left = root.right.left;
				root.right = root.right.right;
			} else if(null == root.right && null != root.left){
				root.data = root.left.data;
				root.right = root.left.right;
				root.left = root.left.left;
			} else {
				Node maximumNode = maximumNode(root.left);
				root.data = maximumNode.data;
				root.left = deleteNode(root.left, maximumNode);
			}
		} else if(node.data < root.data){
			root.left = deleteNode(root.left, node);
		} else {
			root.right = deleteNode(root.right, node);
		}
		return root;
	}
	
	private Node insertNode(Node root, Node node){
		if(null == root){
			root = node;
		} else {
			if(node.data <= root.data){
				root.left = insertNode(root.left, node);
			} else {
				root.right = insertNode(root.right, node);
			}
		}
		return root;
	}
	
	private boolean searchNode(Node root, Node node){
		if(null == root) return false;
		else if(root.data == node.data) return true;
		else if (node.data < root.data) return searchNode(root.left, node);
		else return searchNode(root.right, node);
	}
	
	private Node maximumNode(Node root){
		if(null == root) return null;
		else if(null == root.right) return root;
		else return maximumNode(root.right);
	}
	
	
	private Node minimumNode(Node root){
		if(null == root) return null;
		else if(null == root.left) return root;
		else return minimumNode(root.left);
	}

	@Override
	public String toString() {
		return "BinarySearchTree [root=" + root + "]";
	}

	public static void main(String[] args) {	
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(new Node(20));
		bst.insert(new Node(14));
		bst.insert(new Node(42));
//		bst.insert(new Node(8));
//		bst.insert(new Node(16));
//		bst.insert(new Node(15));
//		System.out.println(bst);
//		bst.insert(new Node(10));
//		bst.insert(new Node(1));
		//bst.printInorder();
//		System.out.println(bst.search(new Node(10)));
//		System.out.println(bst.maximum().data);
//		System.out.println(bst.minimum().data);
		bst.delete(new Node(20));
		bst.printInorder();
	}
}

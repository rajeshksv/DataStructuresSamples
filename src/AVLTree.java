class AVLNode{
	int data;
	AVLNode left;
	AVLNode right;
	int height;

	public AVLNode(int data) {
		this.data = data;
		this.left = this.right = null;
		this.height = 1;
	}

	@Override
	public String toString() {
		return "AVLNode [data=" + data + ", left=" + left + ", right=" + right +  "]";
	}
}


public class AVLTree {

	AVLNode root;

	AVLNode insertNode(AVLNode root, AVLNode node){
		if(null == root){
			return node;
		}

		if(node.data < root.data){
			root.left = insertNode(root.left, node);
		} else {
			root.right = insertNode(root.right, node);
		}

		root.height = max(height(root.left), height(root.right)) + 1;

		int balance = getBalance(root);

		if(balance > 1 && node.data < root.left.data){
			// single right rotate
			root = rightRotate(root);
		} else if(balance > 1 && node.data > root.left.data){
			// two rotations
			root.left = leftRotate(root.left);
			root = rightRotate(root);
		} else if(balance < -1 && node.data > root.right.data){
			// single left rotate
			root = leftRotate(root);
		} else if (balance < -1 && node.data < root.right.data){
			// two rotations
			root.right = rightRotate(root.right);
			root = leftRotate(root);
		}

		return root;
	}


	private AVLNode deleteNode(AVLNode root, AVLNode node){
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
				AVLNode maximumNode = maximumNode(root.left);
				root.data = maximumNode.data;
				root.left = deleteNode(root.left, maximumNode);
			}
		} else if(node.data < root.data){
			root.left = deleteNode(root.left, node);
		} else {
			root.right = deleteNode(root.right, node);
		}
		
		root.height = max(height(root.left), height(root.right)) + 1;

		int balance = getBalance(root);

		if(balance > 1 && node.data < root.left.data){
			// single right rotate
			root = rightRotate(root);
		} else if(balance > 1 && node.data > root.left.data){
			// two rotations
			root.left = leftRotate(root.left);
			root = rightRotate(root);
		} else if(balance < -1 && node.data > root.right.data){
			// single left rotate
			root = leftRotate(root);
		} else if (balance < -1 && node.data < root.right.data){
			// two rotations
			root.right = rightRotate(root.right);
			root = leftRotate(root);
		}
		
		return root;
	}

	private AVLNode maximumNode(AVLNode root){
		if(null == root) return null;
		else if(null == root.right) return root;
		else return maximumNode(root.right);
	}


	private int height(AVLNode node) {
		if(null == node) return 0;
		else return node.height;
	}

	private int max(int a, int b) {
		return a > b ? a : b;
	}

	private int getBalance(AVLNode node){
		if(null == node) return 0;
		return height(node.left) - height(node.right);
	}

	void insert(AVLNode node){
		root = insertNode(root, node);
	}
	
	void delete(AVLNode node){
		root = deleteNode(root, node);
	}

	AVLNode getRoot(){
		return root;
	}

	AVLNode rightRotate(AVLNode y){
		AVLNode x = y.left;
		AVLNode t2 = x.right;

		x.right = y;
		y.left = t2;

		y.height = max(height(y.left), height(y.right)) + 1;
		x.height = max(height(x.left), height(x.right)) + 1;

		return x;
	}

	AVLNode leftRotate(AVLNode x){
		AVLNode y = x.right;
		AVLNode t2 = y.left;

		y.left = x;
		x.right = t2;

		y.height = max(height(y.left), height(y.right)) + 1;
		x.height = max(height(x.left), height(x.right)) + 1;

		return y;
	}

	public static void main(String[] args) {
		AVLTree avlTree = new AVLTree();
		avlTree.insert(new AVLNode(40));
		avlTree.insert(new AVLNode(20));
		avlTree.insert(new AVLNode(25));
		avlTree.delete(new AVLNode(40));
		System.out.println(avlTree.getRoot());
	}

}

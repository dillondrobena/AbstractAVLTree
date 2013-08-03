
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class ModifiedAvlTree<E extends Comparable> extends AbstractAvlTree<E> {

	private BinaryNode<E> root;
	private boolean balance = true;
	private int leftHeight = 0;
	private int rightHeight = 0;
	private Scanner inputFile;
	public ModifiedAvlTree(){
	}
	public int returnLeftHeight() {
		return leftHeight;
	}
	public int returnRightHeight() {
		return rightHeight;
	}
	@SuppressWarnings("unchecked")
	public void treeInput(String f, Class<?> c) throws FileNotFoundException{
		File file = new File(f);
		inputFile = new Scanner(file);
		while (inputFile.hasNext()){
			if (c == Integer.class){
				insert((E)Integer.valueOf(inputFile.next()));
			}
			if (c == Double.class) {
				insert((E)Double.valueOf(inputFile.next()));
			}
		}
	}
	@Override
	public void insert(E e) {
		BinaryNode<E> current = root;
		if (root == null){
			BinaryNode<E> newNode = new BinaryNode<E>(e);
			root = newNode;
			size++;
		}
		else {
			for(int i = 0; i < size; i++){
				if (e.compareTo(current.element) < 0){
					if (current.left == null) {
						current.left = new BinaryNode<E>(e);
						current.l = current.left;
						current.left.parent = current;
						current.left.height = (current.height + 1);
						size++;
						if (current.right != null) {
							current.left.r = current.right;
						}
						else {
							current.left.r = current;
						}
						if (current == root) {
							leftHeight++;
						}
						else if ((current.left.element.compareTo(root.element) < 0) && current.right == null) {
							BinaryNode<E> tempNode1 = current;
							int tempHeight = 0;
							if (tempNode1.parent != root) {
								if (tempNode1.left.element.compareTo(tempNode1.parent.element) > 0) {
									tempNode1 = tempNode1.parent;
									while (tempNode1.left != null) {
										tempNode1 = tempNode1.left;
									}
									tempHeight = tempNode1.height;
								}
								else if (tempNode1.left.element.compareTo(tempNode1.parent.element) < 0) {
									tempNode1 = tempNode1.parent;
									while (tempNode1.right != null) {
										tempNode1 = tempNode1.right;
									}
									tempHeight = tempNode1.height;
								}
							}
							if (tempHeight < current.left.height) {
								leftHeight++;
							}
							if (tempHeight == current.left.height) {
								balance = true;
							}
						}
						else if ((current.left.element.compareTo(root.element) > 0) && current.right == null) {
							BinaryNode<E> tempNode1 = current;
							int tempHeight = 0;
							if (tempNode1.parent != root) {
								if (tempNode1.left.element.compareTo(tempNode1.parent.element) > 0) {
									tempNode1 = tempNode1.parent;
									while (tempNode1.left != null) {
										tempNode1 = tempNode1.left;
									}
									tempHeight = tempNode1.height;
								}
								else if (tempNode1.left.element.compareTo(tempNode1.parent.element) < 0) {
									tempNode1 = tempNode1.parent;
									while (tempNode1.right != null) {
										tempNode1 = tempNode1.right;
									}
									tempHeight = tempNode1.height;
								}
							}
							if (tempHeight < current.left.height) { 
								rightHeight++;
							}
							if (tempHeight == current.left.height) {
								balance = true;
							}
						}
						if (current != root) {
							balance(current.parent, current.left.height);
						}
						break;
					}
					else {
						current = current.left;
					}
				}
				if (e.compareTo(current.element) > 0){
					if (current.right == null){
						current.right = new BinaryNode<E>(e);
						current.right.r = current;
						current.right.parent = current;
						current.right.height = (current.height + 1);
						size++;
						if (current.left == null) {
							current.l = current.right;
						}
						if (current == root) {
							rightHeight++;
						}

						else if ((current.right.element.compareTo(root.element) > 0) && current.left == null) {
							BinaryNode<E> tempNode1 = current;
							int tempHeight = 0;
							if (tempNode1.parent != root) {
								if (tempNode1.right.element.compareTo(tempNode1.parent.element) > 0) {
									tempNode1 = tempNode1.parent;
									while (tempNode1.left != null) {
										tempNode1 = tempNode1.left;
									}
									tempHeight = tempNode1.height;
								}
								else if (tempNode1.right.element.compareTo(tempNode1.parent.element) < 0) {
									tempNode1 = tempNode1.parent;
									while (tempNode1.right != null) {
										tempNode1 = tempNode1.right;
									}
									tempHeight = tempNode1.height;
								}
							}
							if (tempHeight < current.right.height) { 
								rightHeight++;
							}
							if (tempHeight == current.right.height) {
								balance = true;
							}
						}
						else if ((current.right.element.compareTo(root.element) < 0) && current.left == null) {
							BinaryNode<E> tempNode1 = current;
							int tempHeight = 0;
							if (tempNode1.parent != root) {
								if (tempNode1.right.element.compareTo(tempNode1.parent.element) > 0) {
									tempNode1 = tempNode1.parent;
									while (tempNode1.left != null) {
										tempNode1 = tempNode1.left;
									}
									tempHeight = tempNode1.height;
								}
								else if (tempNode1.right.element.compareTo(tempNode1.parent.element) < 0) {
									tempNode1 = tempNode1.parent;
									while (tempNode1.right != null) {
										tempNode1 = tempNode1.right;
									}
									tempHeight = tempNode1.height;
								}
							}
							if (tempHeight < current.right.height) {
								leftHeight++;
							}
							if (tempHeight == current.right.height) {
								balance = true;
							}
						}
						if (current != root) {
							balance(current.parent, current.right.height);
						}
						break;
					}
					else {
						current = current.right;
					}
				}
			}
		}
	}
	@Override
	public void makeEmpty() {
		root = root.left = root.right = null;
		size = 0;
		leftHeight = rightHeight = 0;
	}
	@Override
	public boolean contains(E e){
		boolean contains = false;
		if (size != 0) {
			BinaryNode<E> current = root;
			if (current != null) {
				if (current.element.equals(e)){
					contains = true;
				}
			}
			else {
				for (int i = 0; i < size; i++){
					if (e.compareTo(current.element) < 0 && current.left != null){
						if (current.left.element.equals(e)) {
							contains = true;
							break;
						}
						else {
							current = current.left;
						}
					}
					if (e.compareTo(current.element) > 0 && current.right != null){
						if (current.right.element.equals(e)){
							contains = true;
							break;
						}
						else {
							current = current.right;
						}
					}
				}
			}
		}
		return contains;
	}

	public BinaryNode<E> find(E e){
		BinaryNode<E> current = root;
		if (current.equals(e)){
			return current;
		}
		else {
			for (int i = 0; i < size; i++){
				if (e.compareTo(current.element) < 0){
					if (current.left == e) {
						return current.left;
					}
					else {
						current = current.left;
					}
				}
				if (e.compareTo(current.element) > 0){
					if (current.right == e){
						return current.right;
					}
					else {
						current = current.right;
					}
				}
			}
		}
		return null;
	}
	@Override
	public boolean isEmpty(){
		return root == null;
	}
	public void remove(E e){
		BinaryNode<E> current = root;
		if (current.element.equals(e)){
			if (current.left == null && current.right == null) {
				root = null;
				size--;
			}
			if (current.left != null) {
				BinaryNode<E> tempNode = current.left;
				BinaryNode<E> subTempNode = tempNode;
				while (tempNode.right != null) {
					subTempNode = tempNode;
					tempNode = tempNode.right;
				}
				tempNode.right = current.right;
				tempNode.height = current.height;
				tempNode.parent = current.parent;
				if (tempNode.right != null) {
					tempNode.right.parent = tempNode;
				}
				BinaryNode<E> tempChild = tempNode.left;
				if (current.left != tempNode) {
					tempNode.left = current.left;
					tempNode.left.parent = tempNode;
				}
				if (subTempNode.right == tempNode) {
					subTempNode.right = null;
				}
				current = tempNode;
				root = current;
				if (tempChild != null){
					insert(tempChild.element);
				}
				size--;
			}
			else {
				if (current.right != null) {
					BinaryNode<E> tempNode = current.right;
					BinaryNode<E> subTempNode = tempNode;
					while (tempNode.left != null) {
						subTempNode = tempNode;
						tempNode = tempNode.left;
					}
					tempNode.left = current.left;
					BinaryNode<E> tempChild = tempNode.right;
					if (current.right != tempNode) {
						tempNode.right = current.right;
					}
					if (subTempNode.left == tempNode) {
						subTempNode.left = null;
					}
					current = tempNode;
					root = current;
					if (tempChild != null) {
						insert(tempChild.element);
					}
					size--;
				}
			}
			fixSize();
		}
		else {
			for (int i = 0; i < size; i++) {
				if (e.compareTo(current.element) < 0) {
					if (current.left.element.equals(e)){
						if (current.left.right != null) {
							BinaryNode<E> tempNode = current.left.right;
							BinaryNode<E> subTempNode = tempNode;
							while (tempNode.left != null) {
								subTempNode = tempNode;
								tempNode = tempNode.left;
							}
							tempNode.left = current.left.left;
							BinaryNode<E> tempChild = tempNode.right;
							if (current.left.right != tempNode) {
								tempNode.right = current.left.right;
							}
							current.left = tempNode;
							current.left.parent = current;
							if (subTempNode.left == tempNode) {
								subTempNode.left = null;
							}
							if (tempChild != null) {
								insert(tempChild.element);
							}
							size--;
							fixSize();
							break;
						}
						else {
							current.left = current.left.left;
							if (current.left != null) {
								current.left.parent = current;
							}
							size--;
							fixSize();
							break;
						}
					}
					else {
						current.left.parent = current.parent;
						current = current.left;
					}
				}
				if (e.compareTo(current.element) > 0) {
					if (current.right.element.equals(e)) {
						if (current.right.left != null) {
							BinaryNode<E> tempNode = current.right.left;
							BinaryNode<E> subTempNode = tempNode;
							while (tempNode.right != null) {
								subTempNode = tempNode;
								tempNode = tempNode.right;
							}
							tempNode.right = current.right.right;
							BinaryNode<E> tempChild = tempNode.left;
							if (current.right.left != tempNode) {
								tempNode.left = current.right.left;
							}
							current.right = tempNode;
							current.right.parent = current;
							if (subTempNode.right == tempNode) {
								subTempNode.right = null;
							}
							if (tempChild != null) {
								insert(tempChild.element);
							}
							size--;
							fixSize();
							break;
						}
						else {
							current.right = current.right.right;
							if (current.right != null) {
								current.right.parent = current;
							}
							size--;
							fixSize();
							break;
						}
					}
					else {
						current.right.parent = current.parent;
						current = current.right;
					}
				}
			}
		}
	}
	public void fixSize() {
		if (root != null) {
			BinaryNode<E> current = root;
			leftHeight = 0;
			rightHeight = 0;
			while (current.left != null) {
				current = current.left;
				leftHeight++;
			}
			current = root;
			while (current.right != null) {
				current = current.right;
				rightHeight++;
			}
			//	balance();
		}
	}
	public boolean leftNeedBalance() {
		boolean needBalance = false;
		int lCount = 0;
		int rCount = 0;
		BinaryNode<E> current = root;
		BinaryNode<E> tempCurrent = current;
		if (current.left != null) {
			current = current.left;
			tempCurrent = current;
			while (tempCurrent.right != null) {
				tempCurrent = tempCurrent.right;
				rCount++;
			}
			tempCurrent = current;
			while (tempCurrent.left != null) {
				tempCurrent = tempCurrent.left;
				lCount++;
			}
			if ((rCount - lCount) > 1 || (lCount - rCount) > 1) {
				needBalance = true;
			}
		}
		
		return needBalance;
	}
	public boolean rightNeedBalance() {
		boolean needBalance = false;
		int lCount = 0;
		int rCount = 0;
		BinaryNode<E> current = root;
		BinaryNode<E> tempCurrent = current;
		if (current.right != null) {
			current = current.right;
			tempCurrent = current;
			while (tempCurrent.right != null) {
				tempCurrent = tempCurrent.right;
				rCount++;
			}
			if (tempCurrent.left != null) {
				rCount++;
			}
			tempCurrent = current;
			while (tempCurrent.left != null) {
				tempCurrent = tempCurrent.left;
				lCount++;
			}
			if ((rCount - lCount) > 1 || (lCount - rCount) > 1) {
				needBalance = true;
			}
		}
		
		return needBalance;
	}
	public void balance(BinaryNode<E> node, int h) {
		int height = h;
		int instant = 0;
		BinaryNode<E> current = node;
		if ((leftHeight - rightHeight) > 1 || (rightHeight - leftHeight) > 1 || leftNeedBalance() == true || rightNeedBalance() == true) {
			try {
				if (current.left != null && current.left.left != null && current.right == null && instant == 0) {
					rotateWithLeftChild(current);
					instant = 1;
				}
			}
			catch(NullPointerException ex) {
			}
			try {
				if (current.right != null && current.right.right != null && current.left == null && instant == 0) {
					rotateWithRightChild(current);
					instant = 1;
				}
			}
			catch(NullPointerException ex) {
			}
			try {
				if (current.right != null && current.right.left != null && instant == 0 || current.right != null && current.left != null && current.left.left != null && instant == 0) {
					doubleWithLeftChild(current);
					instant = 1;
				}
			}
			catch(NullPointerException ex) {
			}
			try {
				if (current.left != null && current.left.right != null && instant == 0 || current.left != null && current.right != null && current.right.right != null && instant == 0) {
					doubleWithRightChild(current);
					instant = 1;
				}
			}
			catch(NullPointerException ex){

			}
		}
	}
	public void doubleWithLeftChild(BinaryNode<E> node) {
		int instant = 0;
		BinaryNode<E> current = node;
		try {
			if (current.left == null && instant == 0) {
				current = current.right.left;
				current.left = current.parent.parent;
				current.l = current.left;
				current.right = current.parent;
				current.right.r = current;
				current.l.r = current.right;
				current.parent = null;
				if (current.left != root) {
					current.parent = current.left.parent;
					if (current.left.element.compareTo(current.left.parent.element) > 0) {
						current.left.parent.right = current;
						current.r = current.parent;
						if (current.parent.left != null) {
							current.parent.left.r = current;
						}
						else {
							current.parent.l = current;
						}
					}
					else {
						current.left.parent.left = current;
						if (current.parent.right != null) {
							current.r = current.parent.right;
						}
						else {
							current.r = current.parent;
						}
					}
				}
				current.left.parent = current;
				current.right.parent = current;
				current.height = current.height - 2;
				current.left.height++;
				current.left.right = null;
				current.right.left = null;
				rightHeight--;
				if (current.left == root) {
					root = current;
				}
				instant = 1;
			}
			else if (current.left != null && current.right.left != null && instant == 0) {
				BinaryNode<E> tempNode = current.left;
				current.left = current.parent;
				current.l = current.left;
				if (current.left == root) {
					root = current;
					leftHeight++;
					rightHeight--;
				}
				current.parent = current.left.parent;
				current.left.right = null;
				current.left.parent = current;
				current.height--;
				current.left.height++;
				current.right.height--;
				current.right.left.height--;
				if (current.element.compareTo(current.parent.element) > 0) {
					current.r = current.parent;
					current.parent.right = current;
					if (current.parent.left != null) {
						current.parent.left.r = current;
					}
					else {
						current.parent.l = current;
						current.parent.left = current;
					}
				}
				else {
					if (current.parent.right != null) {
						current.r = current.parent.right;
					}
					else {
						current.r = current.parent;
					}
				}
				insert(tempNode.element);
				instant = 1;
			}
		}
		catch (NullPointerException ex) {
		}
		try {
			if (current.right != null && current.left != null && instant == 0) {
				BinaryNode<E> tempNode = current.right;
				current.right = current.parent;
				if (current.right == root) {
					root = current;
					rightHeight++;
					leftHeight--;
				}
				current.parent = current.right.parent;
				current.right.left = null;
				current.right.parent = current;
				current.height--;
				current.right.height++;
				current.right.right.height++;
				current.left.height--;
				current.left.left.height--;
				insert(tempNode.element);
				instant = 1;
			}
		}
		catch (NullPointerException ex) {
		}
	}
	public void doubleWithRightChild(BinaryNode<E> node) {
		int instant = 0;
		BinaryNode<E> current = node;
		try {
			if (current.right == null && instant == 0) {
				current = current.left.right;
				current.right = current.parent.parent;
				current.left = current.parent;
				current.parent = null;
				current.l = current.left;
				current.l.r = current.right;
				current.right.r = current;
				if (current.right != root) {
					current.parent = current.right.parent;
					if (current.right.element.compareTo(current.right.parent.element) > 0) {
						current.right.parent.right = current;
						current.r = current.parent;
						if (current.parent.left != null) {
							current.parent.left.r = current;
						}
						else {
							current.parent.l = current;
						}
					}
					else {
						current.right.parent.left = current;
						current.parent.l = current;
						if (current.parent.right != null) {
							current.r = current.parent.right;
						}
					}
				}
				current.right.parent = current;
				current.left.parent = current;
				current.height = current.height - 2;
				current.right.height++;
				current.right.left = null;
				current.left.right = null;
				if (current.element.compareTo(root.element) < 0 && current.right != root) {
					leftHeight--;
				}
				else if (current.element.compareTo(root.element) > 0 && current.right != root) {
					rightHeight--;
				}
				if (current.right == root) {
					root = current;
					rightHeight++;
					leftHeight--;
				}
				instant = 1;
			}
			else if (current.right != null && current.left.right != null && instant == 0) {
				BinaryNode<E> tempNode = current.right;
				current.right = current.parent;
				if (current.right == root) {
					root = current;
					rightHeight++;
					leftHeight--;
				}
				if (balance != true) {
				current.parent = current.right.parent;
				current.right.left = null;
				current.right.parent = current;
				current.height--;
				current.right.height++;
				current.left.height--;
				current.left.right.height--;
				insert(tempNode.element);
				instant = 1;
				current.l = current.left;
				}
				if (balance == true) {
					BinaryNode<E> tempNode2 = current.left;
					BinaryNode<E> tempNode3 = current.left.right;
					current.parent = null;
					current.right.left = tempNode;
					current.right.parent = current;
					current.height--;
					current.right.height++;
					current.left.height--;
					current.left.right.height--;
					current.left = root;
					current.left.right = null;
					current.left.parent = current;
					current.left.height++;
					root = current;
					leftHeight++;
					rightHeight--;
					insert(tempNode2.element);
					insert(tempNode3.element);
					balance = false;
					instant = 1;
				}
				if (current.right != null) {
					current.left.r = current.right;
				}
				else {
					current.left.r = current;
				}
				if (current.element.compareTo(current.parent.element) > 0) {
					if (current.parent.left != null) {
						current.parent.left.r = current;
						current.r = current.parent;
					}
					else {
						current.parent.l = current;
						current.r = current.parent;
					}
				}
				else {
					if (current.parent.right != null) {
						current.r = current.parent.right;
						current.parent.l = current;
					}
					else {
						current.parent.l = current;
						current.r = current.parent;
					}
				}
			}
		}
		catch(NullPointerException ex) {
		}
		try {
			if (current.right != null && current.left != null && instant == 0) {
				BinaryNode<E> tempNode = current.left;
				current.left = current.parent;
				if (current.left == root) {
					root = current;
					leftHeight++;
					rightHeight--;
				}
				current.parent = current.left.parent;
				current.left.right = null;
				if (current.element.compareTo(current.left.parent.element) < 0) {
					current.left.parent.left = current;
				}
				else if (current.element.compareTo(current.left.parent.element) > 0){
					current.left.parent.right = current;
				}
				current.left.parent = current;
				current.height--;
				current.left.height++;
				current.left.left.height++;
				current.right.height--;
				current.right.right.height--;
				if (current.element.compareTo(root.element) < 0) {
					leftHeight--;
				}
				else {
					rightHeight--;
				}
				insert(tempNode.element);
				instant = 1;
				current.l = current.left;
				if (current.right != null) {
					current.left.r = current.right;
				}
				else {
					current.left.r = current;
				}
				if (current.element.compareTo(current.parent.element) > 0) {
					if (current.parent.left != null) {
						current.parent.left.r = current;
						current.r = current.parent;
					}
					else {
						current.parent.l = current;
						current.r = current.parent;
					}
				}
				else {
					if (current.parent.right != null) {
						current.r = current.parent.right;
						current.parent.l = current;
					}
					else {
						current.parent.l = current;
						current.r = current.parent;
					}
				}
			}
		}
		catch(NullPointerException ex) {
		}
	}
	public void rotateWithLeftChild(BinaryNode<E> node) {
		BinaryNode<E> current = node;
		current = current.left;
		current.right = current.parent;
		current.parent = current.right.parent;
		if (current.right.parent != null) {
			if (current.element.compareTo(current.right.parent.element) > 0 ) {
				current.right.parent.right = current;
				rightHeight--;
			}
			else {
				current.right.parent.left = current;
				leftHeight--;
			}
		}
		current.right.parent = current;
		current.right.height++;
		current.left.height--;
		current.height--;
		current.right.left = null;
		if (current.right == root) {
			root = current;
			rightHeight++;
		}
		current.l = current.left;
		if (current.right != null) {
			current.left.r = current.right;
		}
		else {
			current.left.r = current;
		}
		if (current.element.compareTo(current.parent.element) > 0) {
			if (current.parent.left != null) {
				current.parent.left.r = current;
				current.r = current.parent;
			}
			else {
				current.parent.l = current;
				current.r = current.parent;
			}
		}
		else {
			if (current.parent.right != null) {
				current.r = current.parent.right;
				current.parent.l = current;
			}
			else {
				current.parent.l = current;
				current.r = current.parent;
			}
		}
	}
	public void rotateWithRightChild(BinaryNode<E> node) {
		BinaryNode<E> current = node;
		current = current.right;
		current.left = current.parent;
		current.parent = current.left.parent;
		if (current.left.parent != null) {
			if (current.element.compareTo(current.left.parent.element) < 0 ) {
				current.left.parent.left = current;
				leftHeight--;
			}
			else {
				current.left.parent.right = current;
				rightHeight--;
			}
		}
		current.left.parent = current;
		current.left.height++;
		current.right.height--;
		current.height--;
		current.left.right = null;
		if (current.left == root) {
			root = current;
			leftHeight++;
		}
		current.l = current.left;
		if (current.right != null) {
			current.left.r = current.right;
		}
		else {
			current.left.r = current;
		}
		if (current.element.compareTo(current.parent.element) > 0) {
			if (current.parent.left != null) {
				current.parent.left.r = current;
				current.r = current.parent;
			}
			else {
				current.parent.l = current;
				current.r = current.parent;
			}
		}
		else {
			if (current.parent.right != null) {
				current.r = current.parent.right;
				current.parent.l = current;
			}
			else {
				current.parent.l = current;
				current.r = current.parent;
			}
		}
	}
	@Override
	public E findMax() {
		if (size != 0) {
			BinaryNode<E> current = root;
			while (current.right != null) {
				current = current.right;
			}
			return current.element;
		}
		return null;
	}
	@Override
	public E findMin() {
		if (size != 0) {
			BinaryNode<E> current = root;
			while (current.left != null) {
				current = current.left;
			}
			return current.element;
		}
		return null;
	}

	private static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;
		BinaryNode<E> parent;
		BinaryNode<E> r;
		BinaryNode<E> l;
		int height = 0;

		BinaryNode(E e) { 
			this(e, null, null, null, null, null);
		}

		BinaryNode(E e, BinaryNode<E> lt, BinaryNode<E> rt, BinaryNode<E> pt, BinaryNode<E> ll, BinaryNode<E> rr) { 
			element = e; left = lt; right = rt; parent = pt; l = ll; r = rr;
		}
	}

}

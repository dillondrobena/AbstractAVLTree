
public abstract class AbstractAvlTree<E> implements AvlTree<E> {
	
	protected int size = 0;

	protected AbstractAvlTree(){
	}

	@Override
	public void insert(E e) {
		insert(e);
	}

	@Override
	public void makeEmpty() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void remove(E e) {
		// TODO Auto-generated method stub

	}

	@Override
	public E findMax() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E findMin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size;
	}
	
	public void balance(E e) {
		
	}
	
	public void rotateWithLeftChild(E e) {
		
	}
	
	public void rotateWithRightChild(E e) {
		
	}
	
	public void doubleWithLeftChild(E e) {
		
	}
	
	public void doubleWithRightChild(E e) {
		
	}

}

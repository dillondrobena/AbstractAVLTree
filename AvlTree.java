
public interface AvlTree<E> {
	
	public void insert(E e);

	public void makeEmpty();

	public boolean contains(E e);

	public boolean isEmpty();

	public void remove(E e);

	public E findMax();

	public E findMin();

	public int size();
	
	public void balance(E e);
	
	public void rotateWithLeftChild(E e);
	
	public void rotateWithRightChild(E e);
	
	public void doubleWithLeftChild(E e);
	
	public void doubleWithRightChild(E e);

}

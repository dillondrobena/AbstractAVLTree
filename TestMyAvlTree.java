
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestMyAvlTree {
	public static void main(String[] args) throws FileNotFoundException{
		
		ModifiedAvlTree<Integer> myTree1 = new ModifiedAvlTree<Integer>();
		myTree1.treeInput("C:\\Users\\Dillon\\Desktop\\DataStructures-ProgrammingAssignment-4\\inputC1.txt", Integer.class);
		System.out.println("Max value: " + myTree1.findMax());
		System.out.println("Min value: " + myTree1.findMin());
		File file = new File("C:\\Users\\Dillon\\Desktop\\DataStructures-ProgrammingAssignment-4\\inputD1.txt");
		Scanner	f = new Scanner(file);
		while (f.hasNext()) {
			Integer y = Integer.valueOf(f.next());
			if (myTree1.contains(y)) {
				myTree1.remove(y);
				System.out.println("key " + y + " is removed.");
			}
			else {
				System.out.println("key " + y + " is not in the tree.");
			}
		}
		ModifiedAvlTree<Double> myTree2 = new ModifiedAvlTree<Double>();
		myTree2.treeInput("C:\\Users\\Dillon\\Desktop\\DataStructures-ProgrammingAssignment-4\\inputC2.txt", Double.class);
		System.out.println("Max value: " + myTree2.findMax());
		System.out.println("Min value: " + myTree2.findMin());
		file = new File("C:\\Users\\Dillon\\Desktop\\DataStructures-ProgrammingAssignment-4\\inputD2.txt");
		f = new Scanner(file);
		while (f.hasNext()) {
			Double z = Double.valueOf(f.next());
			if (myTree2.contains(z)) {
				myTree2.remove(z);
				System.out.println("key " + z + " is removed.");
			}
			else {
				System.out.println("key " + z + " is not in the tree.");
			}
		}
	}
}

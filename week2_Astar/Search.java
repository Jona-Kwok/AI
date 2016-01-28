
public class Search {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] init = {0,0};
		int[] dest = {11,11};
		System.out.println("The Naive one is like this:");
		Astar a = new Astar(init,dest,1);//the basic one 
		System.out.println("\n\nThe Priority Queue is like this:");
		Astar b = new Astar(init,dest,2);//the dynamic programming one
	}

}

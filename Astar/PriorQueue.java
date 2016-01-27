import java.awt.DisplayMode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Jona tree structure needed
 */
public class PriorQueue {
	static int[][] maze = new int[][] { //the grid	
			{ 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1 },
			{ 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0 },
			{ 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0 },
			{ 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
			{ 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
			{ 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0 },
			{ 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 } };
	public static int count =0;//the time of the recursion
	static int[] init = { 0, 0 };
	static int[] dest = { 11, 11 };

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		

		HashMap<String, Integer> on = new HashMap<String, Integer>();//the end notes which haven't been expanded, String as the postion of the note and the Integer as the f cost
		HashMap<String, Integer> visited = new HashMap<String, Integer>();//the expanded notes, String as the position, Integer as the distance from init (g)
		ArrayList<String> route = new ArrayList<String>();
		on.put("[" + init[0] + "," + init[1] + "]", (int) (0+Math.sqrt((dest[0]-init[0])*(dest[0]-init[0]) + (dest[1]-init[1])*(dest[1]-init[1]))));
		myAstar(on, visited,route , dest);
	}
	
	public static void myAstar(HashMap<String, Integer> on,HashMap<String, Integer> visited,List<String> route,int[] dest)
	{
		count ++ ;
		int[] now = {-1,-1};
		int dis = -1;
		
		int min = Collections.min(on.values());
		Iterator<?> iterator = on.entrySet().iterator();
		while(iterator.hasNext())// find the node unexpanded with minimum f cost 
		{
			Map.Entry entry = (Entry<?, ?>) iterator.next();
			if(entry.getValue().equals(min))
			{
				String s = entry.getKey().toString();
				int start = s.lastIndexOf("[");
				int mid = s.lastIndexOf(",");
				int end = s.lastIndexOf("]");
				now[0] = Integer.parseInt(s.substring(start+1, mid));
				now[1] = Integer.parseInt(s.substring(mid+1, end));
				
				dis = min - (int) Math.sqrt(Math.pow(dest[0]-now[0], 2)+Math.pow(dest[1]-now[1], 2));
				on.remove(entry.getKey());
				visited.put(s.substring(start), dis);
				route.add(s);
				break;
			}
		}
		
		if(now[0]==dest[0] && now[1]==dest[1])//if reached the goal
		{
			System.out.println("The shortest path is " + dis+" steps and took "+count+" recursions");
			display(route, "["+now[0]+","+now[1]+"]");
			
			return ;
		}
		
		HashMap<String, Integer> map = new HashMap<String, Integer>(); // map stands for the neighbors around the current node
		map.put("N[" + (now[0] - 1) + "," + now[1]+"]",
				(int) Math.sqrt((Math.pow(dest[0] - now[0] + 1, 2) + Math.pow(dest[1]
						- now[1], 2))));
		map.put("E[" + now[0] + "," + (now[1] + 1)+"]",
				(int) Math.sqrt((Math.pow(dest[0] - now[0], 2) + Math.pow(dest[1]
						- now[1] - 1, 2))));
		map.put("S[" + (now[0] + 1) + "," + now[1]+"]",
				(int) Math.sqrt((Math.pow(dest[0] - now[0] - 1, 2) + Math.pow(dest[1]
						- now[1], 2))));
		map.put("W[" + now[0] + "," + (now[1] - 1)+"]",
				(int) Math.sqrt((Math.pow(dest[0] - now[0], 2) + Math.pow(dest[1]
						- now[1] + 1, 2))));

		if (now[0] - 1 < 0 || maze[now[0] - 1][now[1]] == 1 // delete the neighbors don't exist, marked as wall or has been expanded
				|| visited.containsKey(("[" + (now[0] - 1) + "," + now[1] + "]"))) {
			map.remove("N[" + (now[0] - 1) + "," + now[1] + "]");
		}
		if (now[1] + 1 >= maze[0].length|| maze[now[0]][now[1] + 1] == 1
				|| visited.containsKey(("[" + (now[0]) + "," + (now[1] + 1) + "]"))) {
			map.remove("E[" + now[0] + "," + (now[1] + 1) + "]");
		}
		if (now[0] + 1 >= maze.length || maze[now[0] + 1][now[1]] == 1
				|| visited.containsKey(("[" + (now[0] + 1) + "," + now[1] + "]"))) {
			map.remove("S[" + (now[0] + 1) + "," + now[1] + "]");
		}
		if (now[1] - 1 < 0	|| maze[now[0]][now[1] - 1] == 1
				|| visited.containsKey(("[" + (now[0]) + "," + (now[1] - 1) + "]"))) {
			map.remove("W[" + now[0] + "," + (now[1] - 1) + "]");
		}
		
		if(!map.isEmpty())
		{
			Iterator<?> it = map.entrySet().iterator();//put the available neighbors in the queue
			while(it.hasNext())
			{
				Map.Entry en = (Entry<?, ?>) it.next();
				String string  = en.getKey().toString();
				String string2 = en.getValue().toString();
				string = string.substring(1);
				on.put("["+now[0]+","+now[1]+"]"+string, dis + Integer.parseInt(string2) + 1);
			}
		}
		myAstar(on, visited,route, dest);
	}
	
	public static void display(List<String> route,String now)
	{
		ArrayList<String> r = new ArrayList<String>();
		String destString = "["+init[0]+","+init[1]+"]";
		while(!now.contains(destString))
		{
			r.add(now);
			Iterator<String> iterator = route.iterator();
			while(iterator.hasNext())
			{
				String s = iterator.next();
				if(s.endsWith(now))
				{
					route.remove(s);
					now = s.substring(0,s.lastIndexOf("["));
					break;
				}
			}
		}
		Iterator<String> it = r.iterator();
		System.out.println(destString);
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
	}
}

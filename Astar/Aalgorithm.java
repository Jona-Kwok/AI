import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Jona tree structure needed
 */
public class Aalgorithm {
	static int[][] maze = new int[][] { 
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

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		int[] init = { 0, 0 };
		int[] dest = { 11, 11 };

		HashMap<Integer, String> queue = new HashMap<Integer, String>();

		astar(init, queue, dest, 0);
	}

	public static void astar(int[] now, HashMap<Integer, String> queue,
			int[] dest, int step) throws IOException {

		queue.put(step, "[" + now[0] + "," + now[1] + "]");
		if (now[0] == dest[0] && now[1] == dest[1]) {
			Iterator i = queue.entrySet().iterator();
			while(i.hasNext())
			{
				Map.Entry en = (Entry) i.next();
				System.out.println(en.getKey()+" "+en.getValue());
			}
		}
		

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("N" + (now[0] - 1) + "," + now[1],
				(int) (Math.pow(dest[0] - now[0] + 1, 2) + Math.pow(dest[1]
						- now[1], 2)));
		map.put("E" + now[0] + "," + (now[1] + 1),
				(int) (Math.pow(dest[0] - now[0], 2) + Math.pow(dest[1]
						- now[1] - 1, 2)));
		map.put("S" + (now[0] + 1) + "," + now[1],
				(int) (Math.pow(dest[0] - now[0] - 1, 2) + Math.pow(dest[1]
						- now[1], 2)));
		map.put("W" + now[0] + "," + (now[1] - 1),
				(int) (Math.pow(dest[0] - now[0], 2) + Math.pow(dest[1]
						- now[1] + 1, 2)));

		if (now[0] - 1 < 0 || maze[now[0] - 1][now[1]] == 1
				|| queue.containsValue("[" + (now[0] - 1) + "," + now[1] + "]")) {
			map.remove("N" + (now[0] - 1) + "," + now[1]);
		}
		if (now[1] + 1 >= maze[0].length
				|| maze[now[0]][now[1] + 1] == 1
				|| queue.containsValue("[" + (now[0]) + "," + (now[1] + 1)
						+ "]")) {
			map.remove("E" + now[0] + "," + (now[1] + 1));
		}
		if (now[0] + 1 >= maze.length || maze[now[0] + 1][now[1]] == 1
				|| queue.containsValue("[" + (now[0] + 1) + "," + now[1] + "]")) {
			map.remove("S" + (now[0] + 1) + "," + now[1]);
		}
		if (now[1] - 1 < 0
				|| maze[now[0]][now[1] - 1] == 1
				|| queue.containsValue("[" + (now[0]) + "," + (now[1] - 1)
						+ "]")) {
			map.remove("W" + now[0] + "," + (now[1] - 1));
		}

		if (!map.isEmpty()) {
			int min = Collections.min(map.values());
			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry en = (Entry) it.next();
				if (en.getValue().equals(min)) {
					String s = en.getKey().toString();
					s = s.substring(1);
					int coma = s.indexOf(",");
					int[] next = { Integer.parseInt(s.substring(0, coma)),
							Integer.parseInt(s.substring(coma + 1)) };
					astar(next, queue, dest, step + 1);
				}
			}
		}
	}
}

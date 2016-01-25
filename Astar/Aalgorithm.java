import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.IntPredicate;

/**
 * @author Jona tree structure needed
 */
public class Aalgorithm {
	static int[][] maze = new int[][] { { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
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

		HashMap<String, Integer> queue = new HashMap<String, Integer>();
		queue.put("[" + init[0] + "," + init[1] + "]" + "\n", 0);
		myAstar(queue, dest);

		// HashMap<Integer, String> queue = new HashMap<Integer, String>();
		//
		// astar(init, queue, dest, 0);
	}

	public static void myAstar(HashMap<String, Integer> queue, int[] dest) {
		int min = Collections.min(queue.values());
		Iterator iterator = queue.entrySet().iterator();
		String route = "";
		int dis = -1;
		int[] now = { -1, -1 };

		while (iterator.hasNext()) {
			Map.Entry entry = (Entry) iterator.next();
			if (entry.getValue().equals(min)) {
				route = entry.getKey().toString();
				dis = min;
				int left = route.lastIndexOf("[");
				int mid = route.lastIndexOf(",");
				int end = route.lastIndexOf("]");
				now[0] = Integer.parseInt(route.substring(left + 1, mid));
				now[1] = Integer.parseInt(route.substring(mid+1, end));
				queue.remove(entry.getKey());
				break;
			}
		}
		
		if (now[0] == dest[0] && now[1] == dest[1]) {
			System.out.print(route+"\n"+dis);
			return;
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
				|| route.contains("[" + (now[0] - 1) + "," + now[1] + "]")) {
			map.remove("N" + (now[0] - 1) + "," + now[1]);
		}
		if (now[1] + 1 >= maze[0].length
				|| maze[now[0]][now[1] + 1] == 1
				|| route.contains("[" + (now[0]) + "," + (now[1] + 1)
						+ "]")) {
			map.remove("E" + now[0] + "," + (now[1] + 1));
		}
		if (now[0] + 1 >= maze.length || maze[now[0] + 1][now[1]] == 1
				|| route.contains("[" + (now[0] + 1) + "," + now[1] + "]")) {
			map.remove("S" + (now[0] + 1) + "," + now[1]);
		}
		if (now[1] - 1 < 0
				|| maze[now[0]][now[1] - 1] == 1
				|| route.contains("[" + (now[0]) + "," + (now[1] - 1)
						+ "]")) {
			map.remove("W" + now[0] + "," + (now[1] - 1));
		}

		if (!map.isEmpty()) {
			int min1 = Collections.min(map.values());
			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry en = (Entry) it.next();
				if (en.getValue().equals(min1)) {
					String s = en.getKey().toString();
					s = s.substring(1);
					int coma = s.indexOf(",");
					int[] next = { Integer.parseInt(s.substring(0, coma)),
							Integer.parseInt(s.substring(coma + 1)) };
					s = route + "[" + next[0] + "," + next[1] + "]\n";
					queue.put(s, dis + 1);
				}
			}
		}
		myAstar(queue, dest);

	}

}

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class PriorQueue {

	/**
	 * @param args
	 */
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
	public static int count = 0;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		int[] init = { 0, 0 };
		int[] dest = { 11, 11 };

		HashMap<String, Integer> queue = new HashMap<String, Integer>();
		HashMap<String, Integer> visited = new HashMap<String, Integer>();
		queue.put(
				"[" + init[0] + "," + init[1] + "]",
				(int) (0 + Math.sqrt((dest[0] - init[0]) * (dest[0] - init[0])
						+ (dest[1] - init[1]) * (dest[1] - init[1]))));
		myAstar(queue, visited, dest);
	}

	public static void myAstar(HashMap<String, Integer> on,
			HashMap<String, Integer> visited, int[] dest) {
		count++;
		int[] now = { -1, -1 };
		int dis = -1;

		int min = Collections.min(on.values());
		Iterator iterator = on.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Entry) iterator.next();
			if (entry.getValue().equals(min)) {
				String s = entry.getKey().toString();
				int mid = s.lastIndexOf(",");
				int end = s.lastIndexOf("]");
				now[0] = Integer.parseInt(s.substring(1, mid));
				now[1] = Integer.parseInt(s.substring(mid + 1, end));

				dis = min
						- (int) Math.sqrt(Math.pow(dest[0] - now[0], 2)
								+ Math.pow(dest[1] - now[1], 2));
				on.remove(entry.getKey());
				visited.put(entry.getKey().toString(), dis);
				break;
			}
		}

		if (now[0] == dest[0] && now[1] == dest[1]) {
			System.out.println("The shortest path is " + dis
					+ " steps and took " + count + " recursions");
			return;
		}

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("N[" + (now[0] - 1) + "," + now[1] + "]", (int) Math.sqrt((Math
				.pow(dest[0] - now[0] + 1, 2) + Math.pow(dest[1] - now[1], 2))));
		map.put("E[" + now[0] + "," + (now[1] + 1) + "]", (int) Math.sqrt((Math
				.pow(dest[0] - now[0], 2) + Math.pow(dest[1] - now[1] - 1, 2))));
		map.put("S[" + (now[0] + 1) + "," + now[1] + "]", (int) Math.sqrt((Math
				.pow(dest[0] - now[0] - 1, 2) + Math.pow(dest[1] - now[1], 2))));
		map.put("W[" + now[0] + "," + (now[1] - 1) + "]", (int) Math.sqrt((Math
				.pow(dest[0] - now[0], 2) + Math.pow(dest[1] - now[1] + 1, 2))));

		if (now[0] - 1 < 0
				|| maze[now[0] - 1][now[1]] == 1
				|| visited
						.containsKey(("[" + (now[0] - 1) + "," + now[1] + "]"))) {
			map.remove("N[" + (now[0] - 1) + "," + now[1] + "]");
		}
		if (now[1] + 1 >= maze[0].length
				|| maze[now[0]][now[1] + 1] == 1
				|| visited
						.containsKey(("[" + (now[0]) + "," + (now[1] + 1) + "]"))) {
			map.remove("E[" + now[0] + "," + (now[1] + 1) + "]");
		}
		if (now[0] + 1 >= maze.length
				|| maze[now[0] + 1][now[1]] == 1
				|| visited
						.containsKey(("[" + (now[0] + 1) + "," + now[1] + "]"))) {
			map.remove("S[" + (now[0] + 1) + "," + now[1] + "]");
		}
		if (now[1] - 1 < 0
				|| maze[now[0]][now[1] - 1] == 1
				|| visited
						.containsKey(("[" + (now[0]) + "," + (now[1] - 1) + "]"))) {
			map.remove("W[" + now[0] + "," + (now[1] - 1) + "]");
		}

		if (!map.isEmpty()) {
			int min2 = Collections.min(map.values());
			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry en = (Entry) it.next();
				String string = en.getKey().toString();
				String string2 = en.getValue().toString();
				if (en.getValue().equals(min2)) {
					string = string.substring(1);
					on.put(string, dis + Integer.parseInt(string2) + 1);
				}
			}
		}
		myAstar(on, visited, dest);
	}

}

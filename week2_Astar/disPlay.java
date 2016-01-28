import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class disPlay {
	private List<String> route = null;
	private String now = "";
	private int[] init = {-1,-1};
	
	public disPlay(List<String> route,String now,int[] init)
	{
		this.route = route;
		this.now = now;
		this.init[0] = init[0]; this.init[1] = init[1];
	}
	
	public void dislay()
	{
		ArrayList<String> r = new ArrayList<String>();
		r.add(route.get(route.size()-1));
		String destString = "["+init[0]+","+init[1]+"]";
		while(!now.contains(destString))
		{
			Iterator<String> iterator = route.iterator();
			while(iterator.hasNext())
			{
				String s = iterator.next();
				if(s.endsWith(now))
				{
					route.remove(s);
					now = s.substring(0,s.indexOf("]")+1);
					r.add(s.substring(0,s.indexOf("]")+2));
					break;
				}
			}
		}
		int size = r.size();
		for(int i=size-1;i>=0;i--)
		{
			System.out.println(r.get(i));
		}
		
	}
}

package cs3524.solutions.mud;

import java.rmi.RemoteException;
import java.util.*;

public class ServerImpl implements ServerInterface
{
	
	public List<MUD> serverList = new ArrayList<MUD>();					// a list of MUD object 
	public MUD m = new MUD("mymud.edg","mymud.msg","mymud.thg");		

	public void serverCreate()											//create a new MUD object and then push it into the server list
	{
		MUD mud = new MUD("mymud.edg","mymud.msg","mymud.thg");
		serverList.add(mud);
		return;
	}
	public boolean selectServer(String num)						//given the number of server, find the corresponding MUD in the list
	{	
		int i = Integer.parseInt(num);
		if(i<=serverList.size())
		{
			m = serverList.get(i-1);
			if(m.onlineusers.size()<=5)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	public int getServerList()									//return the total number of MUD object in the list
	{
		return serverList.size();
	} 

	public String getStartLoc()									//get start location of the server
	{
		return m.startLocation();
	}

	public String move(String loc, String dir)					//move user toward direction and return a new vertex name
	{
		Vertex currentLoc = m.getVertex(loc);				//get vertex
		Edge e = currentLoc._routes.get(dir);				//get edge by key
		if(e == null)
		{
			System.out.println("Invalid move,please move toward other direction.");
			return currentLoc._name;
		}
		else
		{
			Vertex newLoc = e._dest;
			System.out.println("Moved to "+newLoc._name);				//get dest(vertex) of the edge
			return newLoc._name;							
		}
	}

	public String pickup(String loc,String item)				//remove given item at current location, and return the item's name back
	{
		Vertex currentLoc = m.getVertex(loc);
		List<String> thing = new ArrayList<>();
		thing = currentLoc._things;
		
		if(thing.contains(item))
		{
			m.delThing(loc,item);
			return item;
		}
		else
		{
			return "Nothing";
		}
	}

	public String view(String loc)								//view current location  items only
	{
		return m.locationInfo(loc);
	}

	public String locInfo(String loc)							//get the location info 
	{
		return m.locationInfo(loc);
	}

	public String getItem(String loc)							//get all items avaiable at the location
	{
		Vertex v = m.getVertex(loc);
		int total = v._things.size() - 1;
		return v._things.get(0);
	}

	public void addPlayer(String name,String loc)				//add a new player to the server
	{
		m.addUser(name,loc);
		return;
	}
	public void updatePlayerLoc(String name,String loc)			//when user move, this method is called and update he's location
	{
		m.changeUserLoc(name,loc);
	}

	public Map getUsers()										//return a hashmap of all users in the current server
	{
		Map<String,String> map = new HashMap<String,String>();
		map = m.onlineusers;
		return map;
	}


}







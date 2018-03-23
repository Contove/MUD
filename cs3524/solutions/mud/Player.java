package cs3524.solutions.mud;
import java.util.*;
class Player
{

	public String _name;
	public int _hp = 100;
	public List<String> _inventory = new ArrayList<String>();
	public String _location;

	public Player(String loc)
	{
		_location = loc;
	}
	public void setName(String s)				//set player name
	{
		_name = s;
		System.out.println("Player name is set as "+s );
		return;
	}
	public String getName()						//get player name
	{
		return _name;
	}

	public void addInventory(String item)		//add item into inventory
	{
		_inventory.add(item);
		return;
	}
	public List getInventory()					//return a list of string that represents inventory
	{
		return _inventory;
	}
	public void changeloc(String loc)			//update players location
	{
		_location = loc;
		return;
	}
	public String getloc()						//get the current location of the player
	{
		return _location;
	}
}
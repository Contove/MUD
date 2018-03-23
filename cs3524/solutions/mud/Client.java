package cs3524.solutions.mud;
import java.io.*;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.*;


public class Client
{
	public static void main(String args[])
	{
		if (args.length<2)
		{	
			System.err.println("please enter host and port");
		}

		String hostname = args[0];
		int registryport = Integer.parseInt(args[1]);

		System.setProperty("java.security.policy","mud.policy");
		System.setSecurityManager(new RMISecurityManager());

		try
		{
			String regURL ="rmi://" + hostname + ":" + registryport +"/mud";
			System.out.println("looking for "+ regURL);

			ServerInterface mudserver = (ServerInterface)Naming.lookup(regURL);
			System.out.println("There are "+mudserver.getServerList()+" server/servers running,please enter number from 1 to "+mudserver.getServerList());
			String serverName = "";
			boolean serverNum = false;
			while(serverNum == false)	
			{
				serverName = System.console().readLine("Server number: ");
				serverNum = mudserver.selectServer(serverName);
				if(serverNum == false)
				{
					System.out.println("Server does not exist or Server is full.");
				}
			}

			String startloc = mudserver.getStartLoc();											//get the start location of the mud server
			System.out.println(startloc);
			Player player = new Player(startloc);												//player spawns 
			System.out.println(player.getloc());
			String playername = System.console().readLine("please enter name: ");
			player.setName(playername);															//set player name
			System.out.println("Player "+player._name+" entered server "+serverName);			
			System.out.println("Player "+ player._name+" spawned at location "+player.getloc());
			mudserver.addPlayer(player.getName(),player.getloc());								//add the player to the list of online user

			
			Scanner in = new Scanner(System.in);
			String input = "";
			//functions
			boolean exit = true;
			while(exit)
			{
				System.out.println("Enter help for the list of commands.");
				System.out.println("Please enter command : ");
				input = in.next();
				if(input.equals("quit"))										// different reference of string object,== does not work here
				{
					exit = false;

				}				
				
				if(input.equals("pickup"))
				{
					String thing = mudserver.getItem(player.getloc());			//fetch item at the current location, and then
					String item = mudserver.pickup(player.getloc(),thing);		//add the item into player's inventory
					player.addInventory(item);
					System.out.println("You acquired "+ item);
				}
				if(input.equals("move"))
				{
					System.out.println("Please enter the direction");
					input = System.console().readLine();

					if(input.equals("north")||input.equals("south")||input.equals("west")||input.equals("east"))
					{
						String currentloc = mudserver.move(player.getloc(),input);	//obtain the destination
						player.changeloc(currentloc);								//move player
						System.out.println("Player moved to "+player.getloc());
					}
					else
					{
						System.out.println(input + " is not a valid direction.");
					}

					mudserver.updatePlayerLoc(player.getName(),player.getloc());
				}
				if(input.equals("view"))
				{
					String m = "";
					m = mudserver.view(player.getloc());			//view current location
					System.out.println(m);
				}

				if(input.equals("onlineplayers"))					//iterate through the list of online user
				{
					Map<String,String> map = new HashMap(); 
					map = mudserver.getUsers();
					Iterator entries = map.entrySet().iterator();
					while(entries.hasNext())
					{
						Map.Entry entry = (Map.Entry) entries.next();
						String name = (String) entry.getKey();
						String location = (String)entry.getValue();
						System.out.println("Player "+name +" is at "+location);
					}
				}
				if(input.equals("help"))							//help
				{
					System.out.println("view : take a look at the current location.");
					System.out.println("move : allow player to move.");
					System.out.println("pickup : pick up the item at current location");
					System.out.println("onlineplayers: all players logged in the current server.");
				}		
			}
			in.close();
				

		}
		catch(java.io.IOException e)
		{
			System.err.println("error: "+ e.getMessage());
		}
		catch(java.rmi.NotBoundException e)
		{
			System.err.println("Server not bound" + e.getMessage());
		}
	}


}
package cs3524.solutions.mud;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RMISecurityManager;
import java.net.InetAddress;

public class ServerMainline
{
	public static void main(String args[])
	{
		if (args.length<2)
		{
			System.err.println("please enter registryPort and serverport");
			return;

		}

		try
		{
			String hostname = (InetAddress.getLocalHost()).getCanonicalHostName();

			int registryport = Integer.parseInt(args[0]);
			int serviceport = Integer.parseInt(args[1]);
			System.setProperty("java.security.policy","mud.policy");
			System.setSecurityManager(new RMISecurityManager());

			ServerImpl service = new ServerImpl();
			ServerInterface mudstub = (ServerInterface)UnicastRemoteObject.exportObject(service, serviceport);

			String regURL ="rmi://" + hostname + ":" + registryport +"/mud";
			System.out.println("REGISTING "+regURL);
			Naming.rebind(regURL,mudstub);
			service.serverCreate();

			String input = "";																			//take input, allow user to create new MUDs until the limit is reached
			while(true)																					//type create to create a new object
			{																							//only 4 server could be created
				System.out.println("Currently "+service.getServerList()+" server/servers are running.");
				int i = service.getServerList();
				if(i<=3)
				{
					input = System.console().readLine("Enter create to create a server.\n");
					if(input.equals("create"))
					{
						service.serverCreate();
					}	
				}
				else
				{
					System.out.println("Only 4 servers could be created at the same time");
					break;
				}
				
			}

		}
		catch(java.net.UnknownHostException e)
		{
			System.out.println("Unknow host :" + e.getMessage());
		}
		catch(java.io.IOException e)
		{
			System.out.println("error: "+ e.getMessage());
		}
	}
}
package cs3524.solutions.mud;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public interface ServerInterface extends Remote
{	
	public String move(String loc, String dir) throws RemoteException;
	public String pickup(String loc,String item) throws RemoteException;
	public String view(String loc)throws RemoteException;
	public String getStartLoc()throws RemoteException;
	public String locInfo(String loc) throws RemoteException;
	public String getItem(String loc) throws RemoteException;
	public void addPlayer(String name,String loc) throws RemoteException;
	public void updatePlayerLoc(String name,String loc) throws RemoteException;
	public Map getUsers() throws RemoteException;
	public void serverCreate() throws RemoteException;
	public boolean selectServer(String num) throws RemoteException;
	public int getServerList() throws RemoteException;

}
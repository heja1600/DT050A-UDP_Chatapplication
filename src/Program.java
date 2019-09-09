import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import se.miun.distsys.GroupCommuncation;
import se.miun.distsys.listeners.ChatMessageListener;
import se.miun.distsys.listeners.LoginListener;
import se.miun.distsys.listeners.LogoutListener;
import se.miun.distsys.messages.ChatMessage;
import se.miun.models.User;

//Skeleton code for Distributed systems 9hp, DT050A

public class Program implements ChatMessageListener, LoginListener, LogoutListener, ActionListener {

	boolean runProgram = true;

	GroupCommuncation gc = null;

	public static void main(String[] args) {
		Program program = new Program();
	}

	public Program() {
		gc = new GroupCommuncation();
		gc.setLogoutListenerListener(this);
		gc.setChatMessageListener(this);
		gc.setLoginListener(this);
		System.out.println("Group Communcation Started");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (runProgram) {
			try {

				System.out.println("Write message to send: ");
				String chat = br.readLine();
				gc.broadcastMessage(new ChatMessage(chat));
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		gc.shutdown();
	}

	@Override
	public void onIncomingChatMessage(ChatMessage chatMessage) {
		System.out.println("Incoming chat message: " + chatMessage.chat);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void onUserLogout(User user) {
		System.out.println("User left the groupchat:" + user.getAddress());

	}

	@Override
	public void onUserLogin(User user) {
		System.out.println("User joined groupchat:" + user.getAddress());
	}


}
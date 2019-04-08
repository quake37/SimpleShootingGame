package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class MainServerStart {
	public static void main(String[] args) {

		System.out.println("[server] starting");

		long t1 = System.currentTimeMillis();
		try {
			ServerSocket server = new ServerSocket(56789); // 서버에서 개방할 포트
			long t2 = System.currentTimeMillis();
			System.out.println("[server] startup in " + (t2 - t1) + " ms");

			while (!server.isClosed()) {
				Socket socket = server.accept(); //
				System.out.println("[server] connected by " + socket.getRemoteSocketAddress());
				Thread p = new PersonalServer(socket);
				p.start();
			}
			server.close();
			
		} catch (IOException e) {
			System.out.println("[server] main error : " + e.toString());
			System.out.println("[server] terminated");
		}
	}
}

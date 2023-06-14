package flight;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        DBManager dbManager = new DBManager();
        dbManager.connect();
        try{
            ServerSocket server = new ServerSocket(1234);
            while(true){
                Socket socket = server.accept();
                System.out.println("Client connected!");
                ClientHandler clientHandler = new ClientHandler(socket, dbManager);
                clientHandler.start();
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }
}

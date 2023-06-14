package flight;

import java.net.Socket;

public class Client {
    public static void main(String []args){
        try{
            Socket socket = new Socket("localhost", 1234);
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
            while(true){
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }
}

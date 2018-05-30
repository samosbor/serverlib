
import java.io.*;
import java.net.*;

import com.sun.net.httpserver.*;

import dao.AuthorizationToken;
import dao.Event;
import dao.Person;
import dao.User;
import handler.ClearHandler;
import handler.EventHandler;
import handler.FillHandler;
import handler.LoadHandler;
import handler.LoginHandler;
import handler.PersonHandler;
import handler.RegisterHandler;

public class Server {
    private static final int MAX_WAITING_CONNECTIONS = 12;
    private HttpServer server;

    private void run(String portNumber) {
        System.out.println("Initializing HTTP Server");

        try {
            dao.AuthorizationToken aDao = new AuthorizationToken();
            dao.Person pDao = new Person();
            dao.User uDao = new User();
            dao.Event eDao = new Event();
            aDao.createTable();
            pDao.createTable();
            uDao.createTable();
            eDao.createTable();





            // Create a new HttpServer object.
            // Rather than calling "new" directly, we instead create
            // the object by calling the HttpServer.create static factory method.
            // Just like "new", this method returns a reference to the new object.
            server = HttpServer.create(new InetSocketAddress(Integer.parseInt(portNumber)), MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }
        server.setExecutor(null);


        server.createContext("/user/register", new RegisterHandler());
        server.createContext("/user/login", new LoginHandler());
        server.createContext("/clear", new ClearHandler());
        server.createContext("/load", new LoadHandler());
        server.createContext("/person", new PersonHandler());
        server.createContext("/event", new EventHandler());
        server.createContext("/fill", new FillHandler());
        server.createContext("/", new handler.DefaultHandler());

        System.out.println("Server started");
        server.start();
    }

    public static void main(String[] args) {
        String portNumber = args[0];
        new Server().run(portNumber);
    }

}

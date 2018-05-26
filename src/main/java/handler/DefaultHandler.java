package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class DefaultHandler implements HttpHandler{

    public DefaultHandler() {
    }

    public  static void main(String[] args){
        DefaultHandler handler = new DefaultHandler();

    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String filePathStr = new File("").getAbsolutePath() + "/serverlib/web/";
        if(exchange.getRequestURI().getPath().equals("/")){
            filePathStr = filePathStr + "index.html";
        }
        else{
            filePathStr = filePathStr + exchange.getRequestURI().getPath();
        }
        Path filePath = FileSystems.getDefault().getPath(filePathStr);
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        Files.copy(filePath, exchange.getResponseBody());

        //exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        // We are not sending a response body, so close the response body
        // output stream, indicating that the response is complete.
        exchange.getResponseBody().close();
    }
}

package handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import result.EventIDResult;
import result.EventResult;
import result.PersonIDResult;
import result.PersonResult;
import service.Event;
import service.EventID;
import service.Person;
import service.PersonID;

public class EventHandler implements HttpHandler {

    public EventHandler() {
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        boolean success = false;

        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {


                // Get the HTTP request headers
                Headers reqHeaders = exchange.getRequestHeaders();
                if (reqHeaders.containsKey("Authorization")) {

                    String authToken = reqHeaders.getFirst("Authorization");

                    String url = exchange.getRequestURI().toString();
                    String[] array = url.split("/");



                    InputStream reqBody = exchange.getRequestBody();
                    // Read JSON string from the input stream
                    String reqData = readString(reqBody);

                    Gson gson = new Gson();
                    EventID eventIDService = new EventID();
                    Event eventService = new Event();
                    String jsonString;
                    if(array.length == 3){
                        String id = array[2];
                        EventIDResult result = eventIDService.getEvent(authToken, id);
                        jsonString = gson.toJson(result);
                    }else{
                        EventResult result = eventService.event(authToken);
                        jsonString = gson.toJson(result);
                    }


                    // Display/log the request JSON data
                    System.out.println(reqData);


                    // Start sending the HTTP response to the client, starting with
                    // the status code and any defined headers.
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    // We are not sending a response body, so close the response body
                    // output stream, indicating that the response is complete.
                    OutputStream respBody = exchange.getResponseBody();
                    writeString(jsonString, respBody);
                    respBody.close();
                    exchange.getResponseBody().close();

                    success = true;
                }


            }

            if (!success) {
                // The HTTP request was invalid somehow, so we return a "bad request"
                // status code to the client.
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                // We are not sending a response body, so close the response body
                // output stream, indicating that the response is complete.
                exchange.getResponseBody().close();
            }
        }
        catch (IOException e) {
            // Some kind of internal error has occurred inside the server (not the
            // client's fault), so we return an "internal server error" status code
            // to the client.
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            // We are not sending a response body, so close the response body
            // output stream, indicating that the response is complete.
            exchange.getResponseBody().close();

            // Display/log the stack trace
            e.printStackTrace();
        }
    }

    /*
        The readString method shows how to read a String from an InputStream.
    */
    private String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }
    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}

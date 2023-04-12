import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.ArrayList;

class SearchHandler implements URLHandler {
    List<String> lst;

    public SearchHandler() {
        lst = new ArrayList<>();
    }

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return "Current String List: " + lst;
        } else if (url.getPath().contains("/add")) {
            if (url.getQuery() == null) {
                return "Query needed!";
            }
            String[] parameters = url.getQuery().split("=");
            if (parameters.length == 2 && parameters[0].equals("s") ) {
                lst.add(parameters[1]);
                return "String added!";
            }
        } else if (url.getPath().contains("/search")) {
            if (url.getQuery() == null) {
                return "Query needed!";
            }
            String[] parameters = url.getQuery().split("=");
            if (parameters.length == 2 && parameters[0].equals("s")) {
                List<String> returnList = new ArrayList<>();
                for (String s : lst) {
                    if (s.contains(parameters[1])) {
                        returnList.add(s);
                    }
                }
                return returnList.toString();
            }
        }
        return "404 Not Found!";
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new SearchHandler());
    }
}

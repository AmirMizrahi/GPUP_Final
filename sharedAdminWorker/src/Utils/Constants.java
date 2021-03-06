package Utils;

import com.google.gson.Gson;

public class Constants {
    public final static String LINE_SEPARATOR = System.getProperty("line.separator");
    public final static int TARGET_REFRESH_RATE = 100;
    public final static int TARGET_REFRESH_RATE2 = 500;
    public final static int DASHBOARD_REFRESH_RATE = 1500;
    public final static String CHAT_LINE_FORMATTING = "%tH:%tM:%tS | %.10s: %s%n";

    public final static String GRAPHVIZ_LOCATION = "JavaFX/src/components/graphviz";
    public final static String XOO_LOCATION = "./engine/src/resources/XOO";
    private final static String SERVER_ADDRESS = "http://localhost:8080/gpup";
    private final static String ENGINE_ADDRESS = "/engine";
    public final static String LOGIN_ADDRESS = SERVER_ADDRESS + "/loginShortResponse";
    public final static String USERS_LIST = SERVER_ADDRESS + "/userslist";
    public final static String GRAPHS_LIST = SERVER_ADDRESS + "/graphslist";
    public final static String LOAD_XML = SERVER_ADDRESS + ENGINE_ADDRESS + "/loadxml?userName=";
    public final static String TASK = SERVER_ADDRESS + ENGINE_ADDRESS + "/task";
    public final static String UPDATE_TASK_STATUS = SERVER_ADDRESS + ENGINE_ADDRESS + "/updatetaskstatus";
    public final static String UPDATE_TASK_SUBSCRIBER = SERVER_ADDRESS + ENGINE_ADDRESS + "/updatetasksubscriber";
    public final static String GET_TARGETS = SERVER_ADDRESS + ENGINE_ADDRESS + "/gettargets";
    public final static String UPDATE_RESULTS = SERVER_ADDRESS + ENGINE_ADDRESS + "/updatetargetsresults";
    public final static String CREATE_TASK_FROM_EXITS = SERVER_ADDRESS + ENGINE_ADDRESS + "/createtaskfromexits";
    public final static String WORKER_TASK_STATUS_CHANGE = SERVER_ADDRESS + ENGINE_ADDRESS + "/workertaskstatuschange";

    //CHAT
    public final static String SEND_CHAT_LINE = SERVER_ADDRESS + "/chatroom/sendChat";
    public final static String CHAT_LINES_LIST = SERVER_ADDRESS + "/chat";



    // GSON instance
    public final static Gson GSON_INSTANCE = new Gson();
}

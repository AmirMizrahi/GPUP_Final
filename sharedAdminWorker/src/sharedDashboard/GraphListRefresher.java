package sharedDashboard;

import DTO.GraphDTO;
import Utils.Constants;
import Utils.HttpClientUtil;
import com.google.gson.reflect.TypeToken;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Consumer;

import static Utils.Constants.GSON_INSTANCE;

public class GraphListRefresher extends TimerTask {

    private final Consumer<List<GraphDTO>> graphsListConsumer;

    public GraphListRefresher(Consumer<List<GraphDTO>> graphsListConsumer) {
        this.graphsListConsumer = graphsListConsumer;
    }

    @Override
    public void run() {
        HttpClientUtil.runAsync(Constants.GRAPHS_LIST, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                List<GraphDTO> failed = new LinkedList<>();
                graphsListConsumer.accept(failed);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                synchronized (this) {
                    String jsonArrayOfUsersNames = response.body().string();
                    //httpRequestLoggerConsumer.accept("Users Request # " + finalRequestNumber + " | Response: " + jsonArrayOfUsersNames);
                    Type type = new TypeToken<List<GraphDTO>>() {
                    }.getType();
                    List<GraphDTO> graphDTOS = GSON_INSTANCE.fromJson(jsonArrayOfUsersNames, type);
                    response.close();
                    graphsListConsumer.accept(graphDTOS);
                }
            }
        });
    }
}

package sharedDashboard;

import DTO.UserDTO;
import Utils.Constants;
import Utils.HttpClientUtil;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Consumer;

import static Utils.Constants.GSON_INSTANCE;

//upload
//timer
//servlets

public class UserListRefresher extends TimerTask {

    private final Consumer<Map<String,UserDTO>> usersListConsumer;
    private int requestNumber;
    private final BooleanProperty isServerOn;

    public UserListRefresher(BooleanProperty isServerOn,Consumer<Map<String, UserDTO>> usersListConsumer) {
        this.usersListConsumer = usersListConsumer;
        requestNumber = 0;
        this.isServerOn = isServerOn;
    }

    @Override
    public void run() {
        final int finalRequestNumber = ++requestNumber;
        //httpRequestLoggerConsumer.accept("About to invoke: " + Utils.Constants.USERS_LIST + " | Users Request # " + finalRequestNumber);
        HttpClientUtil.runAsync(Constants.USERS_LIST, new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                //httpRequestLoggerConsumer.accept("Users Request # " + finalRequestNumber + " | Ended with failure...");
                Platform.runLater(()->isServerOn.set(false));
                Map<String,UserDTO> failed = new HashMap<>();
                failed.put("",new UserDTO("","",-1));
                usersListConsumer.accept(failed);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Platform.runLater(()->isServerOn.set(true));
                String jsonArrayOfUsersNames = response.body().string();
                Type type = new TypeToken<Map<String, UserDTO>>(){}.getType();
                Map<String, UserDTO> usersNames = GSON_INSTANCE.fromJson(jsonArrayOfUsersNames, type);
                response.close();
                usersListConsumer.accept(usersNames);
            }
        });
    }
}
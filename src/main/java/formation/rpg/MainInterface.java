package formation.rpg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainInterface extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainInterface.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);



        stage.setTitle("RPG !");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
/*
        do {
            int index = 0;
            Action[] actions = playerRoom.possibleActions();
            for (Action action : actions) {
                System.out.print(index++);
                System.out.println(" : " + action.toString());
            }
            int choix = Inputs.getInt();
            actions[choix].doo(world);
            System.out.println("-----------------------------------------------");
        } while (true);*/
    }


}
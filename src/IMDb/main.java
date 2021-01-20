package IMDb;

import java.io.IOException;

import IMDb.Classes.database;
import IMDb.Classes.movie;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class main extends Application {

    private static ProgressBar progressBar;
    private static Scene home;
    private static Scene browse;
    private static Stage window;
    private static database data;
    private static TableView<movie> dataTable;
    private static ObservableList<movie> movies;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setMinHeight(667);
        window.setMinWidth(1000);
        Parent root = FXMLLoader.load(getClass().getResource("FXML/loadingScreen.fxml"));
        Scene loadingScreen = new Scene(root, 1000, 667);
        window.setTitle("Jeffrey's Movie Database");
        window.setScene(loadingScreen);
        progressBar = (ProgressBar)loadingScreen.lookup("#progressBar");
        window.show();

        root = FXMLLoader.load(getClass().getResource("FXML/home.fxml"));
        home = new Scene(root, 1000, 667);

        root = FXMLLoader.load(getClass().getResource("FXML/browse.fxml"));
        browse = new Scene(root, 1000, 667);

        loadFile();
    }

    public static void loadHome() {
        window.setScene(home);
        window.show();
    }

    public static void loadBrowse() {
        window.setScene(browse);
        window.show();
    }

    public static Scene getBrowse(){
        return browse;
    }

    public static database getDatabase(){
        return data;
    }

    public static void resetTable(){
        dataTable.setItems(movies);
    }

    private static void loadFile() throws IOException{

        Thread loadDataset = new Thread(){
            public void run() {
                dataTable = (TableView<movie>)browse.lookup("#dataTable");
                movies = FXCollections.observableArrayList();
                try {
                    int num = 0;
                    data = new database("src/IMDb/Resources/dataset_full.csv");
                    final double size = data.getSize() * 1.0;
                    
                    for(String id: data.getMovieList()) {
                        num++;  
                        final double done = num;
                        if(num % (size/10) == 0){
                            Platform.runLater(() -> progressBar.setProgress( done/size ));
                            Thread.sleep(50);
                        }
                        movies.add(data.getMovie(id));
                    }
    
                    dataTable.setItems(movies);
                    Platform.runLater(()-> {
                        loadHome();
                    });

                }catch(Exception e){
                    System.out.println(e);
                }

            }
            
        };
        loadDataset.start();
    }

}

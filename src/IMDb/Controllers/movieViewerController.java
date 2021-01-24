package IMDb.Controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import IMDb.main;
import IMDb.Classes.movie;

public class movieViewerController implements Initializable {

    public TableView<movie> dataTable;
    public TextField searchBar;
    public Button planToWatch;
    public Button completed;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void onEnter(ActionEvent event) {

        dataTable = (TableView<movie>) main.getBrowse().lookup("#dataTable");
        if(searchBar.getText().equals("")){
            main.resetTable();
        }
        else{
            ObservableList<movie> searchResults = main.getDatabase().searchMovies(searchBar.getText().toLowerCase());
            dataTable.setItems(searchResults);
            
        }
        main.loadBrowse();
    }

    public void addToCompleted(){
        if(!main.getList().hasCompleted(main.getCurMovie())){
            main.getList().addCompleted(main.getCurMovie());
            completed.setText("Remove");
            if(main.getList().hasPlanned(main.getCurMovie())){
                addToPlan();
            }
        }
        else {
            main.getList().removeCompleted(main.getCurMovie());
            completed.setText("Completed");
        }
    }

    public void addToPlan(){
        if(!main.getList().hasPlanned(main.getCurMovie())){
            main.getList().addPlanToWatch(main.getCurMovie());
            planToWatch.setText("Remove");
            if(main.getList().hasCompleted(main.getCurMovie())){
                addToCompleted();
            }
        }
        else {
            main.getList().removePlanToWatch(main.getCurMovie());
            planToWatch.setText("Plan To Watch");
        }
    }

    public void loadBrowse(){
        main.loadBrowse();
    }

    public void loadHome(){
        main.loadHome();
    }

    public void loadList(){
        main.loadList();
    }

}

     
     
     
     
     
     
     
     
     
     
     
     
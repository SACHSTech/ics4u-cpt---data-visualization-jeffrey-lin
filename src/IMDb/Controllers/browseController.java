package IMDb.Controllers;

import IMDb.main;
import IMDb.Classes.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

public class browseController implements Initializable {

   public TableView<movie> dataTable;
   public TextField searchBar;
   public ChoiceBox<String> genreFilter;
   //public ChoiceBox<String> langaugeFilter;
   //public ChoiceBox<String> countryFilter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        TableColumn<movie, String> titleColumn = new TableColumn<>("Title"); 
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setSortable(false);
        
        TableColumn<movie, String> genreColumn = new TableColumn<>("Genre"); 
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        genreColumn.setSortable(false);
        
        TableColumn<movie, String> countryColumn = new TableColumn<>("Country"); 
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        countryColumn.setSortable(false);
        
        TableColumn<movie, String> languageColumn = new TableColumn<>("Language"); 
        languageColumn.setCellValueFactory(new PropertyValueFactory<>("language"));
        languageColumn.setSortable(false);
        
        TableColumn<movie, String> directorColumn = new TableColumn<>("Director"); 
        directorColumn.setCellValueFactory(new PropertyValueFactory<>("director"));
        directorColumn.setSortable(false);
        
        TableColumn<movie, Integer> yearColumn = new TableColumn<>("Year"); 
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        yearColumn.setSortable(false);
        
        TableColumn<movie, Double> scoreColumn = new TableColumn<>("Score"); 
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreColumn.setSortable(false);

        dataTable.getColumns().addAll(titleColumn, genreColumn, countryColumn, languageColumn, directorColumn, yearColumn, scoreColumn);

        dataTable.setRowFactory( tableView -> {
            TableRow<movie> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2 && (!row.isEmpty()) ) {
                    movie rowData = row.getItem();
                    main.setMovie(rowData);     
                    main.loadMovieInfo();       
                }
            });
            return row;
         });

         genreFilter.getItems().add("Action");
         genreFilter.getItems().add("Adult");
         genreFilter.getItems().add("Adventure");
         genreFilter.getItems().add("Animation");
         genreFilter.getItems().add("Biography");
         genreFilter.getItems().add("Comedy");
         genreFilter.getItems().add("Crime");
         genreFilter.getItems().add("Drama");
         genreFilter.getItems().add("Documentary");
         genreFilter.getItems().add("Family");
         genreFilter.getItems().add("Fantasy");
         genreFilter.getItems().add("Film-Noir");
         genreFilter.getItems().add("History");
         genreFilter.getItems().add("Horror");
         genreFilter.getItems().add("Music");
         genreFilter.getItems().add("Musical");
         genreFilter.getItems().add("Mystery");
         genreFilter.getItems().add("News");
         genreFilter.getItems().add("Romance");
         genreFilter.getItems().add("Sci-Fi");
         genreFilter.getItems().add("Sport");
         genreFilter.getItems().add("Thriller");
         genreFilter.getItems().add("War");
         genreFilter.getItems().add("Western");

    }

    public void addGenre(){
        int selectedIndex = genreFilter.getSelectionModel().getSelectedIndex();
        String selectedGenre = genreFilter.getSelectionModel().getSelectedItem();
        if(selectedGenre.charAt(0) != 'X'){
            main.getDatabase().removeFilter(selectedGenre.substring(2));
            genreFilter.getItems().remove(selectedIndex);
            genreFilter.getItems().add(selectedIndex, "X " + selectedGenre);            
        }
        else {
            main.getDatabase().addFilter(selectedGenre);
            genreFilter.getItems().remove(selectedIndex);
            genreFilter.getItems().add(selectedIndex, selectedGenre.substring(2));
        }
    }
    /*
    public void addLanguage(){
        int selectedIndex = langaugeFilter.getSelectionModel().getSelectedIndex();
        String selectedGenre = langaugeFilter.getSelectionModel().getSelectedItem();
        if(selectedGenre.charAt(0) == 'X'){
            main.getDatabase().removeFilter(selectedGenre.substring(2));
            langaugeFilter.getItems().remove(selectedIndex);
            langaugeFilter.getItems().add(selectedIndex, selectedGenre.substring(2));                
        }
        else {
            main.getDatabase().addFilter(selectedGenre);
            langaugeFilter.getItems().remove(selectedIndex);
            langaugeFilter.getItems().add(selectedIndex, "X " + selectedGenre);
        }
    }

    public void addCountry(){
        int selectedIndex = countryFilter.getSelectionModel().getSelectedIndex();
        String selectedGenre = countryFilter.getSelectionModel().getSelectedItem();
        if(selectedGenre.charAt(0) == 'X'){
            main.getDatabase().removeFilter(selectedGenre.substring(2));
            countryFilter.getItems().remove(selectedIndex);
            countryFilter.getItems().add(selectedIndex, selectedGenre.substring(2));                
        }
        else {
            main.getDatabase().addFilter(selectedGenre);
            countryFilter.getItems().remove(selectedIndex);
            countryFilter.getItems().add(selectedIndex, "X " + selectedGenre);
        }
    }
    */

    public void loadHome(){
        main.loadHome();
    }

    public void onEnter(ActionEvent event) {
        
        if(searchBar.getText().equals("")){
            main.resetTable();
        }
        else{
            ObservableList<movie> searchResults = main.getDatabase().searchMovies(searchBar.getText().toLowerCase());
            dataTable.setItems(searchResults);
        }

    }

}

     
     
     
     
     
     
     
     
     
     
     
     
package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {
	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> lastNameColumn;

	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label postalCodeLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label birthdayLabel;

	// Reference to the main application.
	// 主なアプリケーションへの参照。
	private MainApp mainApp;

	/**
	 * コンストラクタはinitialize（）メソッドの前に呼び出されます。
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public PersonOverviewController() {
	}

	/**
	 * コントローラクラスを初期化します。 このメソッドは、fxmlファイルがロードされた後に自動的に呼び出されます。
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	    // Initialize the person table with the two columns.
		// 人のテーブルを2つの列で初期化します。
	    firstNameColumn.setCellValueFactory(
	            cellData -> cellData.getValue().firstNameProperty());
	    lastNameColumn.setCellValueFactory(
	            cellData -> cellData.getValue().lastNameProperty());

	    // Clear person details.
	    // Personの詳細をクリア
	    showPersonDetails(null);

	    // Listen for selection changes and show the person details when changed.
	    // 選択の変更を聞き、変更されたときにその人の詳細を表示します。
	    personTable.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> showPersonDetails(newValue));
	}
	
	/**
	 * メインアプリケーションによって呼び出され、自身に参照を戻します。
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// Add observable list data to the table
		// 観察可能なリストデータをテーブルに追加する
		personTable.setItems(mainApp.getPersonData());
	}

	/**
	 * すべてのテキストフィールドを塗りつぶして、その人の詳細を表示します。指定されたpersonがnullの場合、すべてのテキストフィールドがクリアされます。
	 * Fills all text fields to show details about the person.
	 * If the specified person is null, all text fields are cleared.
	 * 
	 * @param person the person or null
	 */
	private void showPersonDetails(Person person) {
		if (person != null) {
			// Fill the labels with info from the person object.
			// personオブジェクトからの情報でラベルを埋めます。
			firstNameLabel.setText(person.getFirstName());
			lastNameLabel.setText(person.getLastName());
			streetLabel.setText(person.getStreet());
			postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
			cityLabel.setText(person.getCity());

			// TODO: We need a way to convert the birthday into a String! 
			// TODO：誕生日をStringに変換する方法が必要です。
			// birthdayLabel.setText(...);
			birthdayLabel.setText(DateUtil.format(person.getBirthday()));
		} else {
			// Person is null, remove all the text.
			// Personがnullの場合は、すべてのテキストを削除します。
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			streetLabel.setText("");
			postalCodeLabel.setText("");
			cityLabel.setText("");
			birthdayLabel.setText("");
		}
	}

	/**
	 * ユーザーが削除ボタンをクリックすると呼び出されます。
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeletePerson() {
	    int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
	    if (selectedIndex >= 0) {
	        personTable.getItems().remove(selectedIndex);
	    } else {
	        // Nothing selected.
	    	// 何も選択されていないとき
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainApp.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Person Selected");
	        alert.setContentText("Please select a person in the table.");

	        alert.showAndWait();
	    }
	}
	
	/**
	 * ユーザーが新しいボタンをクリックすると呼び出されます。 新しい人物の詳細を編集するためのダイアログを開きます。
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new person.
	 */
	@FXML
	private void handleNewPerson() {
	    Person tempPerson = new Person();
	    boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
	    if (okClicked) {
	        mainApp.getPersonData().add(tempPerson);
	    }
	}

	/**
	 * ユーザーが編集ボタンをクリックすると呼び出されます。 選択した人物の詳細を編集するためのダイアログを開きます。
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected person.
	 */
	@FXML
	private void handleEditPerson() {
	    Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
	    if (selectedPerson != null) {
	        boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
	        if (okClicked) {
	            showPersonDetails(selectedPerson);
	        }

	    } else {
	        // Nothing selected.
	    	// 何も選択されていないとき
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainApp.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Person Selected");
	        alert.setContentText("Please select a person in the table.");

	        alert.showAndWait();
	    }
	}
	
}

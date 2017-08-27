package com.jdc.student;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

public class StudentRegistration implements Initializable {
	
	@FXML
	private TableView<Student> table;
	
	private StudentRepo repo;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		repo = new StudentRepo();
		loadData();
	}
	
	private void loadData() {
		
		SelectService service = new SelectService();
		
		service.setOnSucceeded(event -> {
			table.getItems().clear();
			table.getItems().addAll(service.getValue());
		});
		
		service.start();
		
	}
	
	public void upload() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Select Student Data");
		File file = fc.showOpenDialog(table.getScene().getWindow());
	
		if(null != file) {
			try {
				
				List<Student> list = Files.lines(file.toPath()).map(line -> {
					String [] array = line.split("\t");
					return new Student(array[0], array[1]);
				}).collect(Collectors.toList());
				
				UploadService service = new UploadService(list);
				service.setOnSucceeded(event -> {
					loadData();
				});
				service.start();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	class SelectService extends Service<List<Student>> {

		@Override
		protected Task<List<Student>> createTask() {
			return new Task<List<Student>>() {

				@Override
				protected List<Student> call() throws Exception {
					return repo.getAll();
				}
			};
		}
		
	}
	
	
	class UploadService extends Service<Void> {
		
		private List<Student> list;
		
		public UploadService(List<Student> list) {
			super();
			this.list = list;
		}

		@Override
		protected Task<Void> createTask() {
			return new Task<Void>() {
				
				@Override
				protected Void call() throws Exception {
					repo.create(list);
					return null;
				}
			};
		}
		
	}
}

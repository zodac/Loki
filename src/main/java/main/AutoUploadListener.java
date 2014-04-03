package main;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.commons.io.FilenameUtils;

import services.AutoImportService;

@SuppressWarnings("rawtypes")
@Stateless
@LocalBean
public class AutoUploadListener extends Thread {
	@EJB
//	ImportService iEJB;
	AutoImportService iEJB;

	final int DELAY_IN_MINUTES = 2;
	final Path DIR = Paths.get("C:\\Users\\D12128007\\Dropbox\\Upload");
	
	int[] results = new int[6];
	static AutoUploadListener instance = null;

	protected AutoUploadListener() {
	}

	public static AutoUploadListener getInstance() {
		if (instance == null) {
			instance = new AutoUploadListener();
		}
		return instance;
	}

	public void run() {
		while (true) {
			System.out.println("Listening for files...");
			try {
				WatchService watcher = DIR.getFileSystem().newWatchService();
				DIR.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);

				WatchKey watckKey = watcher.take();

				List<WatchEvent<?>> events = watckKey.pollEvents();

				for (WatchEvent event : events) {
					if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
						String fileName = DIR.toString() + File.separator + event.context().toString();
						String fileExtension = FilenameUtils.getExtension(fileName);
						if(fileExtension.equals("xls") || fileExtension.equals("xlsx")){
							System.out.println("Starting import");
							iEJB.addToDatabase(new File(fileName), fileExtension);
						} else{
							System.out.println("File uploaded - not dataset: " + fileName);
						}
					}
				}
			} catch (Exception e) {
			}
			try {
				Thread.sleep(DELAY_IN_MINUTES * 60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
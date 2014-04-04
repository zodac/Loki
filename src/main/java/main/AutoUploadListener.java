package main;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import services.AutoUploadService;

//@Stateless
//@LocalBean
//@SuppressWarnings("rawtypes")
public class AutoUploadListener extends Thread {
	//@EJB
	//ImportService iEJB;

	final int DELAY_IN_MINUTES = 2;
	final Path DIR = Paths.get("/home/zodac/Dropbox/Upload/");

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

	@SuppressWarnings("rawtypes")
	public void run() {
		boolean found = false;
		String fileName = "";


		while (true) {
			System.out.println("Listening for files...");
			try {
				WatchService watcher = DIR.getFileSystem().newWatchService();
				DIR.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);

				WatchKey watckKey = watcher.take();

				List<WatchEvent<?>> events = watckKey.pollEvents();

				for (WatchEvent event : events) {
					if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
						fileName = DIR.toString() + File.separator
								+ event.context().toString();
						System.out.println("Created: " + fileName);
						found = true;
					}
				}

			} catch (Exception e) {
			}
			try {
				if(found){
					System.out.println("found starting execute");
					AutoUploadService.executePost(fileName);
					found = false;
				}
				Thread.sleep(DELAY_IN_MINUTES * 60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}

	}
}
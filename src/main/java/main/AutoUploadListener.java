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

import services.ImportService;

@SuppressWarnings("rawtypes")
public class AutoUploadListener extends Thread {
	@EJB
	ImportService iEJB;

	static int[] results = new int[6];
	static Path myDir = Paths.get("C:\\Users\\D12128007\\Dropbox\\Upload");
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
		System.out.println("Listening for files...");
		while (true) {
			try {
				WatchService watcher = myDir.getFileSystem().newWatchService();
				myDir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE,
						StandardWatchEventKinds.ENTRY_DELETE,
						StandardWatchEventKinds.ENTRY_MODIFY);

				WatchKey watckKey = watcher.take();

				List<WatchEvent<?>> events = watckKey.pollEvents();

				for (WatchEvent event : events) {
					if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE){
						String fileName = myDir.toString() + File.separator + event.context().toString();
						System.out.println("Created: " + fileName);
					}
				}
			} catch (Exception e) {
			}
		}
	}

}

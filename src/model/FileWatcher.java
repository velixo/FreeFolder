package model;

import java.io.File;

public class FileWatcher extends Thread {
	private ModelClass model;
	private long timeStamp;
	private long updateTime;
	private File file;
	private boolean stopThread = false;

	public FileWatcher(ModelClass model, File file, long updateTime) {
		this.model = model;
		this.file = file;
		this.timeStamp = file.lastModified();
		this.updateTime = updateTime;
	}

	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted() && !stopThread) {
			long timeStamp = file.lastModified();
			if (this.timeStamp != timeStamp) {
				this.timeStamp = timeStamp;
				onChange(file);
			}
			try {
				Thread.sleep(updateTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void stopThread() {
		stopThread = true;
	}
	
	private synchronized void onChange(File f) {
		model.fileChanged(f);
	}
}

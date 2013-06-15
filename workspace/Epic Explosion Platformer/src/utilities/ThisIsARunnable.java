package utilities;

import javax.swing.SwingUtilities;

public class ThisIsARunnable implements Runnable {
	private volatile Thread gameThread;
	int i = 0;
	
	public ThisIsARunnable() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		while(gameThread == Thread.currentThread()) {
			loop();
		}
	}
	
	public void loop() {
		System.out.println(i);
		i++;
		if(i >= 500000) terminate();
	}
	
	public void terminate() {
		gameThread = null;
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ThisIsARunnable();
			}
		});
	}
}

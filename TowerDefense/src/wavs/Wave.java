package wavs;

import java.util.Timer;
import java.util.TimerTask;

import UI.CanvaObject;
import core.domain.maps.Map;
import critters.BasicCritter;
import critters.Critter;
import critters.CritterMovingStrategy;
import critters.HigherLevelCritter;

public class Wave {

	int numberOfCritters = 1;
	Map map;
	CanvaObject canva;
	
	public Wave(CanvaObject _canva,Map _map) {
		canva = _canva;
		map = _map;
	}

	public void timerValue(){
		long start = System.currentTimeMillis();

		// some code executed 

		long end = System.currentTimeMillis();
		long elapsed = end - start;
		long seconds = elapsed / 1000;
	}
	
	Timer timer = null;
	boolean shouldRun = true;
	public void Start()
	{
		// current interval, for each critter this interval is less by intervalSeconds
		for(int i = 0; i < numberOfCritters; i++)
		{
			Critter critter = new BasicCritter();
			critter = new HigherLevelCritter(critter);
			// the time interval seconds when the critter should appear on the map
			double intervalSeconds = (map.getUnitSize() / critter.getSpeed()) *(-i);
			critter.setAppearTime((long)(intervalSeconds * 1000));
			critter.setPath(map.getPath());
			map.critters.add(critter);			
		}		

		currentMillis = System.currentTimeMillis();
		shouldRun = true;
		
		timer = new Timer();
		timer.schedule(new TimerTask() {			
			@Override
			public void run() {
				update();
			}
		}, 200, 200);
		
		/*
		while(shouldRun)
		{
			update();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
	}
	
	public void Stop()
	{
		//shouldRun = false;
		timer.cancel();
	}

	long currentMillis = 0;

	void update() {
		long newTime = System.currentTimeMillis();
		long timePassed = newTime - currentMillis;
		currentMillis = newTime;
		
		for(Critter critter : map.critters)
		{
			critter.updatePosition(timePassed);
		}
		
		canva.repaint(); 
		System.out.println("hit");
	}
}

package eg.edu.alexu.csd.oop.cs19.logic;

import java.util.ArrayList;
import java.util.List;

import eg.edu.alexu.csd.oop.cs19.world.IMemento;

public class CareTaker {

	private List<Memento> mementoList = new ArrayList<>();
	private final int size = 1000;
	public CareTaker () {
		mementoList.add(new Memento(0, 0,0,0));
	}

	public void add(Memento iMemento) {
		if(size == mementoList.size()) {
			mementoList.remove(0);
		}
		mementoList.add(iMemento);
	}

	public Memento get() {
		Memento x = mementoList.remove(mementoList.size() - 1);
		if(mementoList.size() == 0) {
			mementoList.add(new Memento(0, 0,0,0));
		}
		return x;	
	}

}

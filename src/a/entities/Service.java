package a.entities;

public class Service {

	double cOverI;
	double sF;
	double blerTarget;

	public synchronized double getcOverI() {
		return cOverI;
	}

	public synchronized void setcOverI(double d) {
		this.cOverI = d;
	}

	public synchronized double getsF() {
		return sF;
	}

	public synchronized void setsF(double sF) {
		this.sF = sF;
	}

	/**
	 * @return the blerTarget
	 */
	public synchronized double getBlerTarget() {
		return blerTarget;
	}

	/**
	 * @param blerTarget the blerTarget to set
	 */
	public synchronized void setBlerTarget(double blerTarget) {
		this.blerTarget = blerTarget;
	}
}

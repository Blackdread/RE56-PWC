package a.entities;

public class Service {

	double cOverI;
	double sF;
	double blerTarget;

	
	public Service() {
		cOverI = 0.0f;
		sF = 0.0f;
		blerTarget = 0.0f;
	}
	/**
	 * @param service
	 */
	public Service(Service service) {
		cOverI = service.cOverI;
		sF = service.sF;
		blerTarget = service.blerTarget;
	}

	public double getcOverI() {
		return cOverI;
	}

	public  void setcOverI(double d) {
		this.cOverI = d;
	}

	public  double getsF() {
		return sF;
	}

	public  void setsF(double sF) {
		this.sF = sF;
	}

	/**
	 * @return the blerTarget
	 */
	public  double getBlerTarget() {
		return blerTarget;
	}

	/**
	 * @param blerTarget the blerTarget to set
	 */
	public  void setBlerTarget(double blerTarget) {
		this.blerTarget = blerTarget;
	}
}

package math4D;

public class Point4D {
	
	public double x, y, z, w;
	
	/** Constructeur */
	public Point4D(double x, double y, double z, double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	/** Constructeur par copie */
	public Point4D(Point4D point) {
		this(point.getX(), point.getY(), point.getZ(), point.getW());
	}
	
	/** Constructeur par defaut */
	public Point4D() {
		this(0, 0, 0, 0);
	}
	
	/** Clone et retourne le point */
	public Point4D clone() {
		return new Point4D(getX(), getY(), getZ(), getW());
	}
	
	/** Retourne la composante x du point */
	public double getX() {
		return x;
	}
	
	/** Retourne la composante y du point */
	public double getY() {
		return y;
	}
	
	/** Retourne la composante z du point */
	public double getZ() {
		return z;
	}
	
	/** Retourne la composante w du point */
	public double getW() {
		return w;
	}
	
	/** Set la composante x du point */
	public void setX(double x) {
		this.x = x;
	}
	
	/** Set la composante y du point */
	public void setY(double y) {
		this.y = y;
	}
	
	/** Set la composante z du point */
	public void setZ(double z) {
		this.z = z;
	}
	
	/** Set la composante z du point */
	public void setW(double w) {
		this.w = w;
	}
	
	/** Set les composantes du point */
	public void set(double x, double y, double z, double w) {
		setX(x);
		setY(y);
		setZ(z);
		setW(w);
	}
	
	/** Retourne la distance a l'origine */
	public double getModule() {
		return Math.sqrt(getX()*getX() + getY()*getY() + getZ()*getZ() + getW()*getW());
	}
	
	/** Translate le point */
	public void translation(double dx, double dy, double dz, double dw) {
		setX(getX() + dx);
		setY(getY() + dy);
		setZ(getZ() + dz);
		setZ(getW() + dw);
	}
	
	/** Translate le point */
	public void translation(Vecteur4D t) {
		translation(t.getDx(), t.getDy(), t.getDz(), t.getDw());
	}
	
	/** Representation textuelle d'un Point3D */
	public String toString() {
		return "(" + getX() + ", " + getY() + ", " + getZ() + ", " + getW() + ")";
	}
	
	/** Retourne la distance entre deux points */
	public static double distance(Point4D p1, Point4D p2) {
		Vecteur4D vect = new Vecteur4D(p1, p2);
		return vect.getNorme();
	}
}

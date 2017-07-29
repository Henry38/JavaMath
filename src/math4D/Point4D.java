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
	public Point4D(Point4D p) {
		this(p.getX(), p.getY(), p.getZ(), p.getW());
	}
	
	/** Constructeur */
	public Point4D(Vecteur4D vect) {
		this(vect.getDx(), vect.getDy(), vect.getDz(), vect.getDw());
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
	
	/** Set les 4 composantes du point */
	public void set(double x, double y, double z, double w) {
		setX(x);
		setY(y);
		setZ(z);
		setW(w);
	}
	
	/** Set les 4 composantes du point */
	public void set(Point4D p) {
		set(p.getX(), p.getY(), p.getZ(), p.getW());
	}
	
	/** Retourne la distance a l'origine */
	public double getModule() {
		return Math.sqrt(getX()*getX() + getY()*getY() + getZ()*getZ() + getW()*getW());
	}
	
	/** Translate le point */
	public Point4D translation(double dx, double dy, double dz, double dw) {
		Point4D p = new Point4D();
		p.setX(getX() + dx);
		p.setY(getY() + dy);
		p.setZ(getZ() + dz);
		p.setZ(getW() + dw);
		return p;
	}
	
	/** Translate le point */
	public Point4D translation(Vecteur4D vect) {
		return translation(vect.getDx(), vect.getDy(), vect.getDz(), vect.getDw());
	}
	
	/** Retourne la distance entre les deux points */
	public double distance(Point4D p) {
		return Point4D.distance(this, p);
	}
	
	/** Representation textuelle d'un Point3D */
	public String toString() {
		return "(" + getX() + ", " + getY() + ", " + getZ() + ", " + getW() + ")";
	}
	
	/** Retourne la distance entre deux points */
	public static double distance(Point4D p1, Point4D p2) {
		Vecteur4D vect = new Vecteur4D(p1, p2);
		return vect.getNorm();
	}
}

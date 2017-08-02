package math3D;

public class Point3D {
	
	public double x, y, z;
	
	/** Constructeur */
	public Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/** Constructeur par copie */
	public Point3D(Point3D point) {
		this(point.getX(), point.getY(), point.getZ());
	}
	
	/** Constructeur */
	public Point3D(Vecteur3D point) {
		this(point.getDx(), point.getDy(), point.getDz());
	}
	
	/** Constructeur par defaut */
	public Point3D() {
		this(0, 0, 0);
	}
	
	/** Clone et retourne le point */
	@Override
	public Object clone() {
		return new Point3D(getX(), getY(), getZ());
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
	
	/** Set les 3 composantes du point */
	public void set(double x, double y, double z) {
		setX(x);
		setY(y);
		setZ(z);
	}
	
	/** Set les 3 composantes du point */
	public void set(Point3D p) {
		set(p.getX(), p.getY(), p.getZ());
	}
	
	/** Retourne la distance a l'origine */
	public double getModule() {
		return Math.sqrt(getX()*getX() + getY()*getY() + getZ()*getZ());
	}
	
	/** Translate le point */
	public Point3D translation(double dx, double dy, double dz) {
		Point3D p = new Point3D();
		p.setX(getX() + dx);
		p.setY(getY() + dy);
		p.setZ(getZ() + dz);
		return p;
	}
	
	/** Translate le point */
	public Point3D translation(Vecteur3D vect) {
		return translation(vect.getDx(), vect.getDy(), vect.getDz());
	}
	
	/** Rotation du point autour d'un axe unitaire (ux, uy, uz) avec un angle donne en radian */
	public Point3D rotation(double ux, double uy, double uz, double radian) {
		Point3D p = new Point3D();
		double x = getX();
		double y = getY();
		double z = getZ();
		double cos = Math.cos(radian);
		double sin = Math.sin(radian);
		p.setX(	x * (Math.pow(ux, 2) + (1 - Math.pow(ux, 2)) * cos) +
				y * (ux*uy * (1 - cos) - uz*sin) +
				z * (ux*uz * (1 - cos) + uy*sin) );
		p.setY(	x * (ux*uy * (1 - cos) + uz*sin) +
				y * (Math.pow(uy, 2) + (1 - Math.pow(uy, 2)) * cos) +
				z * (uy*uz * (1 - cos) - ux*sin) );
		p.setZ(	x * (ux*uz * (1 - cos) - uy*sin) +
				y * (uy*uz * (1 - cos) + ux*sin) +
				z * (Math.pow(uz, 2) + (1 - Math.pow(uz, 2)) * cos) );
		return p;
	}
	
	/** Rotation du point autour d'un axe avec un angle donne en radian */
	public Point3D rotation(Vecteur3D axis, double radian) {
		Vecteur3D unit = axis.getUnitVector();
		double ux = unit.getDx();
		double uy = unit.getDy();
		double uz = unit.getDz();
		return rotation(ux, uy, uz, radian);
	}
	
	/** Homothetie du point par rapport a l'origine */
	public Point3D scale(double sx, double sy, double sz) {
		Point3D p = new Point3D();
		p.setX(sx * getX());
		p.setY(sy * getY());
		p.setZ(sz * getZ());
		return p;
	}
	
	/** Retourne la distance entre les deux points */
	public double distance(Point3D p) {
		return Point3D.distance(this, p);
	}
	
	/** Representation textuelle d'un Point3D */
	@Override
	public String toString() {
		return "(" + getX() + ", " + getY() + ", " + getZ() + ")";
	}
	
	/** Retourne la distance entre deux points */
	public static double distance(Point3D p1, Point3D p2) {
		Vecteur3D vect = new Vecteur3D(p1, p2);
		return vect.getNorm();
	}
}

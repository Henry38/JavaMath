package math2D;

public class Point2D {
	
	public double x;
	public double y;
	
	/** Constructeur */
	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/** Constructeur par copie */
	public Point2D(Point2D point) {
		this(point.getX(), point.getY());
	}
	
	/** Constructeur */
	public Point2D(Vecteur2D vect) {
		this(vect.getDx(), vect.getDy());
	}
	
	/** Constructeur par defaut */
	public Point2D() {
		this(0, 0);
	}
	
	/** Clone et retourne le point */
	@Override
	public Object clone() {
		return new Point2D(getX(), getY());
	}
	
	/** Retourne la composante x du point */
	public double getX() {
		return x;
	}
	
	/** Retourne la composante y  du point */
	public double getY() {
		return y;
	}
	
	/** Set la composante x  du point */
	public void setX(double x) {
		this.x = x;
	}
	
	/** Set la composante y du point */
	public void setY(double y) {
		this.y = y;
	}
	
	/** Set les 2 composantes du point */
	public void set(double x, double y) {
		setX(x);
		setY(y);
	}
	
	/** Set les 2 composantes du point */
	public void set(Point2D p) {
		set(p.getX(), p.getY());
	}
	
	/** Retourne la distance a l'origine */
	public double getModule() {
		return Math.sqrt(getX()*getX() + getY()*getY());
	}
	
	/** Translate le point */
	public Point2D translation(double dx, double dy) {
		Point2D p = new Point2D();
		p.setX(getX() + dx);
		p.setY(getY() + dy);
		return p;
	}
	
	/** Translate le point */
	public Point2D translation(Vecteur2D vect) {
		return translation(vect.getDx(), vect.getDy());
	}
	
	/** Rotation autour de l'origine dans le sens trigonometrique avec l'angle donne en radian */
	public Point2D rotation(double radian) {
		Point2D p = new Point2D();
		double x = getX();
		double y = getY();
		double cos = Math.cos(radian);
		double sin = Math.sin(radian);
		p.setX((x * cos) - (y * sin));
		p.setY((x * sin) + (y * cos));
		return p;
	}
	
	/** Homothetie du point par rapport a l'origine */
	public Point2D scale(double sx, double sy) {
		Point2D p = new Point2D();
		p.setX(sx * getX());
		p.setY(sy * getY());
		return p;
	}
	
	/** Retourne la distance entre les deux points */
	public double distance(Point2D p) {
		return Point2D.distance(this, p);
	}
	
	/** Representation textuelle d'un Point2D */
	@Override
	public String toString() {
		return "(" + getX() + ", " + getY() + ")";
	}
	
	/** Retourne la distance entre deux points */
	public static double distance(Point2D p1, Point2D p2) {
		Vecteur2D vect = new Vecteur2D(p1, p2);
		return vect.getNorm();
	}
}

package math2D;

public class Vecteur2D {
	
	private double dist_x;
	private double dist_y;
	
	/** Constructeur */
	public Vecteur2D(double dist_x, double dist_y) {
		this.dist_x = dist_x;
		this.dist_y = dist_y;
	}
	
	/** Constructeur par copie */
	public Vecteur2D(Vecteur2D vect) {
		this(vect.getDx(), vect.getDy());
	}
	
	/** Constructeur du vecteur AB */
	public Vecteur2D(Point2D a, Point2D b) {
		this(b.getX() - a.getX(), b.getY() - a.getY());
	}
	
	/** Constructeur du vecteur OA */
	public Vecteur2D(Point2D a) {
		this(a.getX(), a.getY());
	}
	
	/** Constructeur par defaut */
	public Vecteur2D() {
		this(0, 0);
	}
	
	/** Retourne la composante x du vecteur */
	public double getDx() {
		return dist_x;
	}
	
	/** Retourne la composante y du vecteur */
	public double getDy() {
		return dist_y;
	}
	
	/** Set la composante x du vecteur */
	public void setDx(double dist_x) {
		this.dist_x = dist_x;
	}
	
	/** Set la composante y du vecteur */
	public void setDy(double dist_y) {
		this.dist_y = dist_y;
	}
	
	/** Set les 2 composantes du vecteur */
	public void set(double dist_x, double dist_y) {
		setDx(dist_x);
		setDy(dist_y);
	}
	
	/** Set les 2 composantes du vecteur */
	public void set(Vecteur2D vect) {
		set(vect.getDx(), vect.getDy());
	}
	
	/** Retourne la norme du vecteur */
	public double getNorm() {
		return Math.sqrt(Math.pow(getDx(), 2) + Math.pow(getDy(), 2));
	}
	
	/** Retourne le vecteur unitaire */
	public Vecteur2D getUnitVector() {
		Vecteur2D vect = new Vecteur2D(this);
		vect.normalize();
		return vect;
	}
	
	/** Retourne l'oppose du vecteur */
	public Vecteur2D getInverse() {
		Vecteur2D vect = new Vecteur2D(this);
		vect.inverse();
		return vect;
	}
	
	/** Retourne la projection du vecteur passe en parametre sur le vecteur courant */
	public Vecteur2D getProjected(Vecteur2D vect) {
		double norm = (Vecteur2D.scalar_product(this, vect)) / Math.pow(vect.getNorm(), 2);
		double dx, dy;
		dx = getDx() * norm;
		dy = getDy() * norm;
		return new Vecteur2D(dx, dy);
	}
	
	/** Normalise le vecteur */
	public void normalize() {
		double norm = getNorm();
		setDx(getDx() / norm);
		setDy(getDy() / norm);
	}
	
	/** Inverse le vecteur */
	public void inverse() {
		setDx(-getDx());
		setDy(-getDy());
	}
	
	/** Ajoute le vecteur passe en parametre */
	public Vecteur2D add(double dx, double dy) {
		setDx(getDx() + dx);
		setDy(getDy() + dy);
		return this;
	}
	
	/** Ajoute le vecteur passe en parametre */
	public Vecteur2D add(Vecteur2D vect) {
		return add(vect.getDx(), vect.getDy());
	}
	
	/** Soustrait le vecteur passe en parametre */
	public Vecteur2D remove(double dx, double dy) {
		setDx(getDx() - dx);
		setDy(getDy() - dy);
		return this;
	}
	
	/** Soustrait le vecteur passe en parametre */
	public Vecteur2D remove(Vecteur2D vect) {
		return remove(vect.getDx(), vect.getDy());
	}
	
	/** Multiplie par le coefficent passe en parametre */
	public Vecteur2D mult(double k) {
		setDx(k * getDx());
		setDy(k * getDy());
		return this;
	}
	
	/** Rotation dans le sens trigonometrique avec un angle donne en radian */
	public Vecteur2D rotation(double radian) {
		double x = getDx();
		double y = getDy();
		double cos = Math.cos(radian);
		double sin = Math.sin(radian);
		setDx((x * cos) - (y * sin));
		setDy((x * sin) + (y * cos));
		return this;
	}
	
	/** Representation textuelle d'un Vecteur2D */
	public String toString() {
		return "(" + ((int)(getDx()*100))/100.0 + " , " + ((int)(getDy()*100))/100.0 + ")";
	}
	
	/** Retourne le produit scalaire de deux vecteurs */
	public static double scalar_product(Vecteur2D vect1, Vecteur2D vect2) {
		return (vect1.getDx() * vect2.getDx() + vect1.getDy() * vect2.getDy());
	}
	
	/** Retourne le produit vectoriel de deux vecteurs */
	public static double cross_product(Vecteur2D vect1, Vecteur2D vect2) {
		return (vect1.getDx() * vect2.getDy() - vect1.getDy() * vect2.getDx());
	}
	
	/** Retourne le cosinus de l'angle forme par deux vecteurs */
	public static double cosinus_angle(Vecteur2D vect1, Vecteur2D vect2) {
		return (scalar_product(vect1, vect2) / (vect1.getNorm() * vect2.getNorm()));
	}
	
	/** Retourne le sinus de l'angle forme par deux vecteurs */
	public static double sinus_angle(Vecteur2D vect1, Vecteur2D vect2) {
		return (cross_product(vect1, vect2) / (vect1.getNorm() * vect2.getNorm()));
	}
	
	/** Retourne le vecteur reflechi de vect par la normale */
	public static Vecteur2D reflect(Vecteur2D vect, Vecteur2D normal) {
		double dot = Vecteur2D.scalar_product(vect, normal);
		double dx = vect.getDx() - 2 * dot * normal.getDx();
		double dy = vect.getDy() - 2 * dot * normal.getDy();
		return new Vecteur2D(dx, dy);
	}
	
//	/** Retourne vrai si les deux vecteurs sont colinaires */
//	public static boolean colineaire(Vecteur2D vect1, Vecteur2D vect2) {
//		return (Math.abs(vect1.getDx()/vect2.getDx() - vect1.getDy()/vect2.getDy()) < 0.01);
//	}
}

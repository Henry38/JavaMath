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
	
	/** Constructeur */
	public Vecteur2D(Point2D p1, Point2D p2) {
		this(p2.getX() - p1.getX(), p2.getY() - p1.getY());
	}
	
	/** Constructeur */
	public Vecteur2D(Point2D p) {
		this(p.getX(), p.getY());
	}
	
	/** Constructeur par defaut */
	public Vecteur2D() {
		this(0, 0);
	}
	
	/** Clone et retourne le vecteur */
	public Vecteur2D clone() {
		return new Vecteur2D(getDx(), getDy());
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
	public Vecteur2D getOpposite() {
		Vecteur2D vect = new Vecteur2D(this);
		vect.opposite();
		return vect;
	}
	
	/** Retourne la projection du vecteur passe en parametre sur le vecteur courant */
	public Vecteur2D getProjectedOn(Vecteur2D vect) {
		Vecteur2D proj = new Vecteur2D(this);
		proj.projectOn(vect);
		return proj;
	}
	
	/** Normalise le vecteur */
	public void normalize() {
		double norm = getNorm();
		setDx(getDx() / norm);
		setDy(getDy() / norm);
	}
	
	/** Inverse le vecteur de sens */
	public void opposite() {
		setDx(-getDx());
		setDy(-getDy());
	}
	
	/** Projete le vecteur courant sur le vecteur passe en parametre */
	public void projectOn(Vecteur2D vect) {
		double norm = (Vecteur2D.scalar_product(this, vect)) / Math.pow(vect.getNorm(), 2);
		setDx(vect.getDx() * norm);
		setDy(vect.getDy() * norm);
	}
	
	/** Ajoute le vecteur passe en parametre */
	public Vecteur2D add(double dx, double dy) {
		Vecteur2D vect = new Vecteur2D(); 
		vect.setDx(getDx() + dx);
		vect.setDy(getDy() + dy);
		return vect;
	}
	
	/** Ajoute le vecteur passe en parametre */
	public Vecteur2D add(Vecteur2D vect) {
		return add(vect.getDx(), vect.getDy());
	}
	
	/** Soustrait le vecteur passe en parametre */
	public Vecteur2D subsract(double dx, double dy) {
		Vecteur2D vect = new Vecteur2D();
		vect.setDx(getDx() - dx);
		vect.setDy(getDy() - dy);
		return vect;
	}
	
	/** Soustrait le vecteur passe en parametre */
	public Vecteur2D subsract(Vecteur2D vect) {
		return subsract(vect.getDx(), vect.getDy());
	}
	
	/** Multiplie par le coefficent passe en parametre */
	public Vecteur2D multiply(double k) {
		Vecteur2D vect = new Vecteur2D();
		vect.setDx(k * getDx());
		vect.setDy(k * getDy());
		return vect;
	}
	
	/** Divise par le coefficent passe en parametre */
	public Vecteur2D divide(double k) {
		Vecteur2D vect = new Vecteur2D();
		vect.setDx(getDx() / k);
		vect.setDy(getDy() / k);
		return vect;
	}
	
	/** Rotation dans le sens trigonometrique avec un angle donne en radian */
	public Vecteur2D rotation(double radian) {
		Vecteur2D vect = new Vecteur2D();
		double x = getDx();
		double y = getDy();
		double cos = Math.cos(radian);
		double sin = Math.sin(radian);
		vect.setDx((x * cos) - (y * sin));
		vect.setDy((x * sin) + (y * cos));
		return vect;
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

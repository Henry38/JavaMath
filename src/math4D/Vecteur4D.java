package math4D;

public class Vecteur4D {
	
	private double dist_x;
	private double dist_y;
	private double dist_z;
	private double dist_w;
	
	/** Constructeur */
	public Vecteur4D(double dist_x, double dist_y, double dist_z, double dist_w) {
		this.dist_x = dist_x;
		this.dist_y = dist_y;
		this.dist_z = dist_z;
		this.dist_w = dist_w;
	}
	
	/** Constructeur par copie */
	public Vecteur4D(Vecteur4D vect) {
		this(vect.getDx(), vect.getDy(), vect.getDz(), vect.getDw());
	}
	
	/** Constructeur du vecteur AB */
	public Vecteur4D(Point4D a, Point4D b) {
		this(b.getX() - a.getX(), b.getY() - a.getY(), b.getZ() - a.getZ(), b.getW() - a.getW());
	}
	
	/** Constructeur du vecteur OA */
	public Vecteur4D(Point4D a) {
		this(a.getX(), a.getY(), a.getZ(), a.getW());
	}
	
	/** Constructeur par defaut */
	public Vecteur4D() {
		this(0, 0, 0, 0);
	}
	
	/** Retourne la composante x du vecteur */
	public double getDx() {
		return dist_x;
	}
	
	/** Retourne la composante y du vecteur */
	public double getDy() {
		return dist_y;
	}
	
	/** Retourne la composante z du vecteur */
	public double getDz() {
		return dist_z;
	}
	
	/** Retourne la composante w du vecteur */
	public double getDw() {
		return dist_w;
	}
	
	/** Set la composante x du vecteur */
	public void setDx(double dist_x) {
		this.dist_x = dist_x;
	}
	
	/** Set la composante y du vecteur */
	public void setDy(double dist_y) {
		this.dist_y = dist_y;
	}
	
	/** Set la composante z du vecteur */
	public void setDz(double dist_z) {
		this.dist_z = dist_z;
	}
	
	/** Set la composante z du vecteur */
	public void setDw(double dist_w) {
		this.dist_w = dist_w;
	}
	
	/** Set les 4 composantes du vecteur */
	public void set(double dist_x, double dist_y, double dist_z, double dist_w) {
		setDx(dist_x);
		setDy(dist_y);
		setDz(dist_z);
		setDw(dist_w);
	}
	
	/** Set les 4 composantes du vecteur */
	public void set(Vecteur4D vect) {
		set(vect.getDx(), vect.getDy(), vect.getDz(), vect.getDw());
	}
	
	/** Retourne la norme du vecteur */
	public double getNorm() {
		return Math.sqrt(Math.pow(getDx(), 2) + Math.pow(getDy(), 2) + Math.pow(getDz(), 2) + Math.pow(getDw(), 2));
	}
	
	/** Retourne le vecteur unitaire */
	public Vecteur4D getUnitVector() {
		Vecteur4D vect = new Vecteur4D(this);
		vect.normalize();
		return vect;
	}
	
	/** Retourne l'oppose du vecteur */
	public Vecteur4D getOpposite() {
		Vecteur4D vect = new Vecteur4D(this);
		vect.opposite();
		return vect;
	}
	
	/** Retourne la projection du vecteur passe en parametre sur le vecteur courant */
	public Vecteur4D getProjectedOn(Vecteur4D vect) {
		Vecteur4D proj = new Vecteur4D(this);
		proj.projectOn(vect);
		return proj;
	}
	
	/** Normalise le vecteur */
	public void normalize() {
		double norm = getNorm();
		setDx(getDx() / norm);
		setDy(getDy() / norm);
		setDz(getDz() / norm);
		setDw(getDw() / norm);
	}
	
	/** Inverse le vecteur */
	public void opposite() {
		setDx(-getDx());
		setDy(-getDy());
		setDz(-getDz());
		setDw(-getDw());
	}
	
	/** Projete le vecteur courant sur le vecteur passe en parametre */
	public void projectOn(Vecteur4D vect) {
		double norm = (Vecteur4D.scalar_product(this, vect)) / Math.pow(vect.getNorm(), 2);
		setDx(vect.getDx() * norm);
		setDy(vect.getDy() * norm);
		setDz(vect.getDz() * norm);
		setDw(vect.getDw() * norm);
	}
	
	/** Ajoute le vecteur passe en parametre */
	public void add(double dx, double dy, double dz, double dw) {
		setDx(getDx() + dx);
		setDy(getDy() + dy);
		setDz(getDz() + dz);
		setDz(getDw() + dw);
	}
	
	/** Ajoute le vecteur passe en parametre */
	public void add(Vecteur4D vect) {
		add(getDx(), vect.getDy(), vect.getDz(), vect.getDw());
	}
	
	/** Soustrait le vecteur passe en parametre */
	public void subsract(double dx, double dy, double dz, double dw) {
		setDx(getDx() - dx);
		setDy(getDy() - dy);
		setDz(getDz() - dz);
		setDz(getDw() - dw);
	}
	
	/** Soustrait le vecteur passe en parametre */
	public void subsract(Vecteur4D vect) {
		subsract(getDx(), vect.getDy(), vect.getDz(), vect.getDw());
	}
	
	/** Multiplie par le coefficent passe en parametre */
	public void multiply(double k) {
		setDx(k * getDx());
		setDy(k * getDy());
		setDz(k * getDz());
		setDw(k * getDw());
	}
	
	/** Divise par le coefficent passe en parametre */
	public void divide(double k) {
		setDx(getDx() / k);
		setDy(getDy() / k);
		setDz(getDz() / k);
		setDw(getDw() / k);
	}
	
	/** Representation textuelle d'un Vecteur2D */
	public String toString() {
		return "(" + ((int)(getDx()*100))/100.0 + " , " + ((int)(getDy()*100))/100.0 + " , " + ((int)(getDz()*100))/100.0 + " , " + ((int)(getDw()*100))/100.0 + ")";
	}
	
	/** Retourne le produit scalaire de deux vecteurs */
	public static double scalar_product(Vecteur4D vect1, Vecteur4D vect2) {
		return (vect1.getDx() * vect2.getDx() + vect1.getDy() * vect2.getDy() + vect1.getDz() * vect2.getDz() + vect1.getDw() * vect2.getDw());
	}
}

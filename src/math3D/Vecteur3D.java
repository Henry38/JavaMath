package math3D;

public class Vecteur3D {
	
	private double dist_x;
	private double dist_y;
	private double dist_z;
	
	/** Constructeur */
	public Vecteur3D(double dist_x, double dist_y, double dist_z) {
		this.dist_x = dist_x;
		this.dist_y = dist_y;
		this.dist_z = dist_z;
	}
	
	/** Constructeur par copie */
	public Vecteur3D(Vecteur3D vect) {
		this(vect.getDx(), vect.getDy(), vect.getDz());
	}
	
	/** Constructeur du vecteur AB */
	public Vecteur3D(Point3D a, Point3D b) {
		this(b.getX() - a.getX(), b.getY() - a.getY(), b.getZ() - a.getZ());
	}
	
	/** Constructeur du vecteur OA */
	public Vecteur3D(Point3D a) {
		this(a.getX(), a.getY(), a.getZ());
	}
	
	/** Constructeur par defaut */
	public Vecteur3D() {
		this(0, 0, 0);
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
	
	/** Set les composantes du vecteur */
	public void set(double dist_x, double dist_y, double dist_z) {
		setDx(dist_x);
		setDy(dist_y);
		setDz(dist_z);
	}
	
	/** Retourne la norme du vecteur */
	public double getNorme() {
		return Math.sqrt(Math.pow(getDx(), 2) + Math.pow(getDy(), 2) + Math.pow(getDz(), 2));
	}
	
	/** Retourne le vecteur unitaire */
	public Vecteur3D getVecteurUnitaire() {
		double norme = getNorme();
		double dx, dy, dz;
		dx = getDx() / norme;
		dy = getDy() / norme;
		dz = getDz() / norme;
		return new Vecteur3D(dx, dy, dz);
	}
	
	/** Retourne la projection du vecteur courant sur le vecteur passe en parametre */
	public Vecteur3D getProjectionOn(Vecteur3D vect) {
		double norme = (Vecteur3D.produit_scalaire(this, vect)) / Math.pow(vect.getNorme(), 2);
		double dx, dy, dz;
		dx = getDx() * norme;
		dy = getDy() * norme;
		dz = getDz() * norme;
		return new Vecteur3D(dx, dy, dz);
	}
	
	/** Normalise le vecteur */
	public void normalized() {
		double norme = getNorme();
		setDx(getDx() / norme);
		setDy(getDy() / norme);
		setDz(getDz() / norme);
	}
	
	/** Ajoute le vecteur passe en parametre */
	public void add(double dx, double dy, double dz) {
		setDx(getDx() + dx);
		setDy(getDy() + dy);
		setDz(getDz() + dz);
	}
	
	/** Ajoute le vecteur passe en parametre */
	public void add(Vecteur3D vect) {
		add(vect.getDx(), vect.getDy(), vect.getDz());
	}
	
	/** Soustrait le vecteur passe en parametre */
	public void remove(double dx, double dy, double dz) {
		setDx(getDx() - dx);
		setDy(getDy() - dy);
		setDz(getDz() - dz);
	}
	
	/** Soustrait le vecteur passe en parametre */
	public void remove(Vecteur3D vect) {
		remove(vect.getDx(), vect.getDy(), vect.getDz());
	}
	
	/** Multiplie par le coefficent passe en parametre */
	public void mult(double k) {
		setDx(k * getDx());
		setDy(k * getDy());
		setDz(k * getDz());
	}
	
	/** Rotation du vecteur autour d'un axe (dx, dy, dz) avec un angle donne en radian */
	public void rotation(double dx, double dy, double dz, double radian) {
		double x = getDx();
		double y = getDy();
		double z = getDz();
		double cos = Math.cos(radian);
		double sin = Math.sin(radian);
		setDx(  x * (Math.pow(dx, 2) + (1 - Math.pow(dx, 2)) * cos) +
				y * (dx*dy * (1 - cos) - dz*sin) +
				z * (dx*dz * (1 - cos) + dy*sin) );
		setDy(  x * (dx*dy * (1 - cos) + dz*sin) +
				y * (Math.pow(dy, 2) + (1 - Math.pow(dy, 2)) * cos) +
				z * (dy*dz * (1 - cos) - dx*sin) );
		setDz(  x * (dx*dz * (1 - cos) - dy*sin) +
				y * (dy*dz * (1 - cos) + dx*sin) +
				z * (Math.pow(dz, 2) + (1 - Math.pow(dz, 2)) * cos) );
	}
	
	/** Rotation du vecteur autour d'un axe avec un angle donne en radian */
	public void rotation(Vecteur3D axis, double radian) {
		Vecteur3D unit = axis.getVecteurUnitaire();
		double dx = unit.getDx();
		double dy = unit.getDy();
		double dz = unit.getDz();
		rotation(dx, dy, dz, radian);
	}
	
	/** Representation textuelle d'un Vecteur2D */
	public String toString() {
		return "(" + ((int)(getDx()*100))/100.0 + " , " + ((int)(getDy()*100))/100.0 + ((int)(getDz()*100))/100.0 + ")";
	}
	
	/** Retourne le produit scalaire de deux vecteurs */
	public static double produit_scalaire(Vecteur3D vect1, Vecteur3D vect2) {
		return (vect1.getDx() * vect2.getDx() + vect1.getDy() * vect2.getDy() + vect1.getDz() * vect2.getDz());
	}
	
	/** Retourne le produit vectoriel de deux vecteurs en 3 dimensions */
	public static Vecteur3D produit_vectoriel(Vecteur3D vect1, Vecteur3D vect2) {
		double dx, dy, dz;
		dx = (vect1.getDy() * vect2.getDz()) - (vect1.getDz() * vect2.getDy());
		dy = (vect1.getDz() * vect2.getDx()) - (vect1.getDx() * vect2.getDz());
		dz = (vect1.getDx() * vect2.getDy()) - (vect1.getDy() * vect2.getDx());
		return new Vecteur3D(dx, dy, dz);
	}
	
	/** Retourne le cosinus de l'angle forme par les deux vecteurs */
	public static double cosinus_angle(Vecteur3D vect1, Vecteur3D vect2) {
		return produit_scalaire(vect1, vect2) / (vect1.getNorme() * vect2.getNorme());
	}
	
	/** Retourne le sinus de l'angle forme par les deux vecteurs */
	public static double sinus_angle(Vecteur3D vect1, Vecteur3D vect2) {
		return produit_vectoriel(vect1, vect2).getNorme() / (vect1.getNorme() * vect2.getNorme());
	}
	
	/** Retourne le vecteur reflechi de vect par la normale */
	public static Vecteur3D reflect(Vecteur3D vect, Vecteur3D normal) {
		double dot = Vecteur3D.produit_scalaire(vect, normal);
		double dx = vect.getDx() - 2 * dot * normal.getDx();
		double dy = vect.getDy() - 2 * dot * normal.getDy();
		double dz = vect.getDz() - 2 * dot * normal.getDz();
		return new Vecteur3D(dx, dy, dz);
	}
	
//	/** Retourne vrai si les deux vecteurs sont colinaires */
//	public static boolean colineaire(Vecteur3D vect1, Vecteur3D vect2) {
//		return (Math.abs(vect1.getDx()/vect2.getDx() - vect1.getDy()/vect2.getDy()) < 0.01);
//	}
}

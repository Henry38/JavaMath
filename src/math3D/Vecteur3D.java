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
	
	/** Set les 3 composantes du vecteur */
	public void set(double dist_x, double dist_y, double dist_z) {
		setDx(dist_x);
		setDy(dist_y);
		setDz(dist_z);
	}
	
	/** Set les 3 composantes du vecteur */
	public void set(Vecteur3D vect) {
		set(vect.getDx(), vect.getDy(), vect.getDz());
	}
	
	/** Retourne la norme du vecteur */
	public double getNorm() {
		return Math.sqrt(Math.pow(getDx(), 2) + Math.pow(getDy(), 2) + Math.pow(getDz(), 2));
	}
	
	/** Retourne le vecteur unitaire */
	public Vecteur3D getUnitVector() {
		Vecteur3D vect = new Vecteur3D(this);
		vect.normalize();
		return vect;
	}
	
	/** Retourne l'oppose du vecteur */
	public Vecteur3D getOpposite() {
		Vecteur3D vect = new Vecteur3D(this);
		vect.opposite();
		return vect;
	}
	
	/** Retourne la projection du vecteur passe en parametre sur le vecteur courant */
	public Vecteur3D getProjectedOn(Vecteur3D vect) {
		Vecteur3D proj = new Vecteur3D(this);
		proj.projectOn(vect);
		return proj;
	}
	
	/** Normalise le vecteur */
	public void normalize() {
		double norm = getNorm();
		setDx(getDx() / norm);
		setDy(getDy() / norm);
		setDz(getDz() / norm);
	}
	
	/** Inverse le vecteur de sens */
	public void opposite() {
		setDx(-getDx());
		setDy(-getDy());
		setDz(-getDz());
	}
	
	/** Projete le vecteur courant sur le vecteur passe en parametre */
	public void projectOn(Vecteur3D vect) {
		double norm = (Vecteur3D.scalar_product(this, vect)) / Math.pow(vect.getNorm(), 2);
		setDx(vect.getDx() * norm);
		setDy(vect.getDy() * norm);
		setDz(vect.getDz() * norm);
	}
	
	/** Ajoute le vecteur passe en parametre */
	public Vecteur3D add(double dx, double dy, double dz) {
		setDx(getDx() + dx);
		setDy(getDy() + dy);
		setDz(getDz() + dz);
		return this;
	}
	
	/** Ajoute le vecteur passe en parametre */
	public Vecteur3D add(Vecteur3D vect) {
		return add(vect.getDx(), vect.getDy(), vect.getDz());
	}
	
	/** Soustrait le vecteur passe en parametre */
	public Vecteur3D subsract(double dx, double dy, double dz) {
		setDx(getDx() - dx);
		setDy(getDy() - dy);
		setDz(getDz() - dz);
		return this;
	}
	
	/** Soustrait le vecteur passe en parametre */
	public Vecteur3D remove(Vecteur3D vect) {
		return subsract(vect.getDx(), vect.getDy(), vect.getDz());
	}
	
	/** Multiplie par le coefficent passe en parametre */
	public Vecteur3D multiply(double k) {
		setDx(k * getDx());
		setDy(k * getDy());
		setDz(k * getDz());
		return this;
	}
	
	/** Divise par le coefficent passe en parametre */
	public Vecteur3D divide(double k) {
		setDx(k / getDx());
		setDy(k / getDy());
		setDz(k / getDz());
		return this;
	}
	
	/** Rotation du vecteur autour d'un axe unitaire (ux, uy, uz) avec un angle donne en radian */
	public Vecteur3D rotation(double ux, double uy, double uz, double radian) {
		double x = getDx();
		double y = getDy();
		double z = getDz();
		double cos = Math.cos(radian);
		double sin = Math.sin(radian);
		setDx(  x * (Math.pow(ux, 2) + (1 - Math.pow(ux, 2)) * cos) +
				y * (ux*uy * (1 - cos) - uz*sin) +
				z * (ux*uz * (1 - cos) + uy*sin) );
		setDy(  x * (ux*uy * (1 - cos) + uz*sin) +
				y * (Math.pow(uy, 2) + (1 - Math.pow(uy, 2)) * cos) +
				z * (uy*uz * (1 - cos) - ux*sin) );
		setDz(  x * (ux*uz * (1 - cos) - uy*sin) +
				y * (uy*uz * (1 - cos) + ux*sin) +
				z * (Math.pow(uz, 2) + (1 - Math.pow(uz, 2)) * cos) );
		return this;
	}
	
	/** Rotation du vecteur autour d'un axe quelconque avec un angle donne en radian */
	public Vecteur3D rotation(Vecteur3D axis, double radian) {
		Vecteur3D unit = axis.getUnitVector();
		double ux = unit.getDx();
		double uy = unit.getDy();
		double uz = unit.getDz();
		return rotation(ux, uy, uz, radian);
	}
	
	/** Representation textuelle d'un Vecteur2D */
	public String toString() {
		return "(" + ((int)(getDx()*100))/100.0 + " , " + ((int)(getDy()*100))/100.0 + " , " + ((int)(getDz()*100))/100.0 + ")";
	}
	
	/** Retourne le produit scalaire de deux vecteurs */
	public static double scalar_product(Vecteur3D vect1, Vecteur3D vect2) {
		return (vect1.getDx() * vect2.getDx() + vect1.getDy() * vect2.getDy() + vect1.getDz() * vect2.getDz());
	}
	
	/** Retourne le produit vectoriel de deux vecteurs en 3 dimensions */
	public static Vecteur3D cross_product(Vecteur3D vect1, Vecteur3D vect2) {
		double dx, dy, dz;
		dx = (vect1.getDy() * vect2.getDz()) - (vect1.getDz() * vect2.getDy());
		dy = (vect1.getDz() * vect2.getDx()) - (vect1.getDx() * vect2.getDz());
		dz = (vect1.getDx() * vect2.getDy()) - (vect1.getDy() * vect2.getDx());
		return new Vecteur3D(dx, dy, dz);
	}
	
	/** Retourne le cosinus de l'angle forme par les deux vecteurs */
	public static double cosinus_angle(Vecteur3D vect1, Vecteur3D vect2) {
		return scalar_product(vect1, vect2) / (vect1.getNorm() * vect2.getNorm());
	}
	
	/** Retourne le sinus de l'angle forme par les deux vecteurs */
	public static double sinus_angle(Vecteur3D vect1, Vecteur3D vect2) {
		return cross_product(vect1, vect2).getNorm() / (vect1.getNorm() * vect2.getNorm());
	}
	
	/** Retourne le vecteur reflechi de vect par la normale */
	public static Vecteur3D reflect(Vecteur3D vect, Vecteur3D normal) {
		double dot = Vecteur3D.scalar_product(vect, normal);
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

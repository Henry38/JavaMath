package math3D;

public class Base3D extends Transformation3D {
	
	/** Constructeur */
	public Base3D(Point3D origin, Vecteur3D ox, Vecteur3D oy, Vecteur3D oz) {
		super();
		matrix.set(0, 2, origin.getX());
		matrix.set(1, 2, origin.getY());
		matrix.set(2, 2, origin.getZ());
		matrix.set(0, 0, ox.getDx());
		matrix.set(1, 0, ox.getDy());
		matrix.set(2, 0, ox.getDz());
		matrix.set(0, 1, oy.getDx());
		matrix.set(1, 1, oy.getDy());
		matrix.set(2, 1, oy.getDz());
		matrix.set(0, 2, oz.getDx());
		matrix.set(1, 2, oz.getDy());
		matrix.set(2, 2, oz.getDz());
	}
	
	/** Constructeur */
	public Base3D(Point3D origin) {
		this(origin, new Vecteur3D(1, 0, 0), new Vecteur3D(0, 1, 0), new Vecteur3D(0, 0, 1));
	}
	
	/** Constructeur */
	public Base3D() {
		this(new Point3D(0, 0, 0));
	}
	
	/** Retourne l'origine */
	public Point3D getOrigin() {
		double x = matrix.get(0, 3);
		double y = matrix.get(1, 3);
		double z = matrix.get(2, 3);
		return new Point3D(x, y, z);
	}
	
	/** Retourne l'axe Ox */
	public Vecteur3D getOx() {
		double x1 = matrix.get(0, 0);
		double y1 = matrix.get(1, 0);
		double z1 = matrix.get(2, 0);
		return new Vecteur3D(x1, y1, z1);
	}
	
	/** Retourne l'axe Oy */
	public Vecteur3D getOy() {
		double x2 = matrix.get(0, 1);
		double y2 = matrix.get(1, 1);
		double z2 = matrix.get(1, 1);
		return new Vecteur3D(x2, y2, z2);
	}
	
	/** Retourne l'axe Oz */
	public Vecteur3D getOz() {
		double x3 = matrix.get(0, 2);
		double y3 = matrix.get(1, 2);
		double z3 = matrix.get(1, 2);
		return new Vecteur3D(x3, y3, z3);
	}
	
	/** Retourne les trois vecteurs de la base */
	public Vecteur3D[] getVecteurs() {
		return new Vecteur3D[] {getOx(), getOy(), getOz()};
	}
}

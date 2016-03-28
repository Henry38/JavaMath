package math3D;

public class Base3D {
	
	private Point3D origine;
	private Vecteur3D ox, oy, oz;
	
	/** Constructeur */
	public Base3D(Point3D origine, Vecteur3D ox, Vecteur3D oy, Vecteur3D oz) {
		this.origine = new Point3D(origine);
		this.ox = ox;
		this.oy = oy;
		this.oz = oz;
	}
	
	/** Constructeur */
	public Base3D(Point3D origine) {
		this.origine = new Point3D(origine);
		this.ox = new Vecteur3D(1, 0, 0);
		this.oy = new Vecteur3D(0, 1, 0);
		this.oz = new Vecteur3D(0, 0, 1);
	}
	
	/** Constructeur */
	public Base3D() {
		this(new Point3D(0, 0, 0));
	}
	
	/** Retourne l'origine */
	public final Point3D getOrigine() {
		return origine;
	}
	
	/** Retourne l'axe Ox */
	public final Vecteur3D getOx() {
		return ox;
	}
	
	/** Retourne l'axe Oy */
	public final Vecteur3D getOy() {
		return oy;
	}
	
	/** Retourne l'axe Oz */
	public final Vecteur3D getOz() {
		return oz;
	}
	
	/** Retourne les trois vecteurs de la base */
	public final Vecteur3D[] getVecteurs() {
		return new Vecteur3D[] {ox, oy, oz};
	}
	
	/** Translate la base */
	public void translation(double dx, double dy, double dz) {
		origine.translation(dx, dy, dz);
	}
	
	/** Translate la base */
	public void translation(Vecteur3D t) {
		translation(t.getDx(), t.getDy(), t.getDz());
	}
	
	/** Augmente le degre de rotation autour de Ox */
	public void rotationOx(double radian) {
		oy.rotation(ox, radian);
		oz.rotation(ox, radian);
	}
	
	/** Augmente le degre de rotation autour de Oy */
	public void rotationOy(double radian) {
		ox.rotation(oy, radian);
		oz.rotation(oy, radian);
	}
	
	/** Augmente le degre de rotation autour de Oz */
	public void rotationOz(double radian) {
		ox.rotation(oz, radian);
		oy.rotation(oz, radian);
	}
	
	/** Change l'echelle en Ox */
	public void scaleX(double sx) {
		ox.mult(sx);
	}
	
	/** Change l'echelle en Oy */
	public void scaleY(double sy) {
		oy.mult(sy);
	}
	
	/** Change l'echelle en Oz */
	public void scaleZ(double sz) {
		oz.mult(sz);
	}
	
	/** Change l'echelle sur les trois axes */
	public void scale(double r) {
		scaleX(r);
		scaleY(r);
		scaleZ(r);
	}
}

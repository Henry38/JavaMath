package math3D;

public class Base3D {
	
	private Point3D origine;
	private Vecteur3D oi, oj, ok;
	
	/** Constructeur */
	public Base3D(Point3D origine, Vecteur3D oi, Vecteur3D oj, Vecteur3D ok) {
		this.origine = new Point3D(origine);
		this.oi = oi;
		this.oj = oj;
		this.ok = ok;
	}
	
	/** Constructeur */
	public Base3D(Point3D origine) {
		this.origine = new Point3D(origine);
		this.oi = new Vecteur3D(1, 0, 0);
		this.oj = new Vecteur3D(0, 1, 0);
		this.ok = new Vecteur3D(0, 0, 1);
	}
	
	/** Retourne l'origine */
	public final Point3D getOrigine() {
		return origine;
	}
	
	/** Retourne les trois vecteurs de la base */
	public final Vecteur3D[] getVecteurs() {
		return new Vecteur3D[] {oi, oj, ok};
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
		oj.rotation(oi, radian);
		ok.rotation(oi, radian);
	}
	
	/** Augmente le degre de rotation autour de Oy */
	public void rotationOy(double radian) {
		oi.rotation(oj, radian);
		ok.rotation(oj, radian);
	}
	
	/** Augmente le degre de rotation autour de Oz */
	public void rotationOz(double radian) {
		oi.rotation(ok, radian);
		oj.rotation(ok, radian);
	}
	
	/** Change l'echelle en Ox */
	public void scaleX(double sx) {
		oi.mult(sx);
	}
	
	/** Change l'echelle en Oy */
	public void scaleY(double sy) {
		oj.mult(sy);
	}
	
	/** Change l'echelle en Oz */
	public void scaleZ(double sz) {
		ok.mult(sz);
	}
	
	/** Change l'echelle sur les trois axes */
	public void scale(double r) {
		scaleX(r);
		scaleY(r);
		scaleZ(r);
	}
}

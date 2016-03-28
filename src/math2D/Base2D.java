package math2D;

public class Base2D {
	
	private Point2D origine;
	private Vecteur2D ox, oy;
	
	/** Constructeur */
	public Base2D(Point2D origine, Vecteur2D oi, Vecteur2D oj) {
		this.origine = new Point2D(origine);
		this.ox = oi;
		this.oy = oj;
	}
	
	/** Constructeur */
	public Base2D(Point2D origine) {
		this(origine, new Vecteur2D(1, 0), new Vecteur2D(0, 1));
	}
	
	/** Constructeur */
	public Base2D() {
		this(new Point2D(0, 0));
	}
	
	/** Retourne l'origine */
	public final Point2D getOrigine() {
		return origine;
	}
	
	/** Retourne l'axe Ox */
	public final Vecteur2D getOx() {
		return ox;
	}
	
	/** Retourne l'axe Oy */
	public final Vecteur2D getOy() {
		return oy;
	}
	
	/** Retourne les deux vecteurs de la base */
	public final Vecteur2D[] getVecteurs() {
		return new Vecteur2D[] {ox, oy};
	}
	
	/** Translate la base */
	public void translation(double dx, double dy) {
		origine.translation(dx, dy);
	}
	
	/** Translate la base */
	public void translation(Vecteur2D t) {
		translation(t.getDx(), t.getDy());
	}
	
	/** Rotation dans le sens trigonometrique autour de l'origine avec l'angle donne en radian */
	public void rotation(double radian) {
		ox.rotation(radian);
		oy.rotation(radian);
	}
	
	/** Change l'echelle en Ox */
	public void scaleX(double sx) {
		ox.mult(sx);
	}
	
	/** Change l'echelle en Oy */
	public void scaleY(double sy) {
		oy.mult(sy);
	}
	
	/** Change l'echelle sur les axes du repere */
	public void scale(double r) {
		scaleX(r);
		scaleY(r);
	}
}

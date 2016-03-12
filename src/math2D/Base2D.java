package math2D;

public class Base2D {
	
	private Point2D origine;
	private Vecteur2D oi, oj;
	
	/** Constructeur */
	public Base2D(Point2D origine, Vecteur2D oi, Vecteur2D oj) {
		this.origine = new Point2D(origine);
		this.oi = oi;
		this.oj = oj;
	}
	
	/** Constructeur */
	public Base2D(Point2D origine) {
		this(origine, new Vecteur2D(1, 0), new Vecteur2D(0, 1));
	}
	
	/** Retourne l'origine */
	public final Point2D getOrigine() {
		return origine;
	}
	
	/** Retourne les deux vecteurs de la base */
	public final Vecteur2D[] getVecteurs() {
		return new Vecteur2D[] {oi, oj};
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
		oi.rotation(radian);
		oj.rotation(radian);
	}
	
	/** Change l'echelle en Ox */
	public void scaleX(double sx) {
		oi.mult(sx);
	}
	
	/** Change l'echelle en Oy */
	public void scaleY(double sy) {
		oj.mult(sy);
	}
	
	/** Change l'echelle sur les axes du repere */
	public void scale(double r) {
		scaleX(r);
		scaleY(r);
	}
}

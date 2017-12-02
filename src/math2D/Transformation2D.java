package math2D;

import math3D.Matrix3D;
import math3D.Vecteur3D;

public class Transformation2D {
	
	protected Matrix3D matrix;
	
	/** Constructeur */
	public Transformation2D(Matrix3D matrix) {
		this.matrix = new Matrix3D(matrix);
	}
	
	/** Constructeur par copie */
	public Transformation2D(Transformation2D t) {
		this(t.matrix);
	}
	
	/** Constructeur par defaut */
	public Transformation2D() {
		this(new Matrix3D.Identity());
	}
	
	/** Retourne la transformation inverse */
	public Transformation2D getInverseTransformation() {
		return new Transformation2D(matrix.getInverse());
	}
	
	/** Calcule le resultat de la transformation du point (x,y) */
	public Point2D transform(double x, double y) {
		Vecteur3D hp = new Vecteur3D(x, y, 1);
		Vecteur3D r = matrix.multiply(hp);
		return new Point2D(r.getDx()/r.getDz(), r.getDy()/r.getDz());
	}
	
	/** Calcule le resultat de la transformation du Point2D p */
	public Point2D transform(Point2D p) {
		return transform(p.getX(), p.getY());
	}
	
	/** Revient sur la transformation identite */
	public void clear() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				double d = (i == j ? 1 : 0);
				matrix.set(i, j, d);
			}
		}
	}
	
	/** Ajoute une translation a la transformation */
	public void addTranslation(double dx, double dy) {
		matrix.set(0, 2, matrix.get(0, 2) + dx);
		matrix.set(1, 2, matrix.get(1, 2) + dy);
	}
	
	/** Ajoute une translation a la transformation */
	public void addTranslation(Vecteur2D vect) {
		addTranslation(vect.getDx(), vect.getDy());
	}
	
	/** Ajoute une translation a la transformation */
	public void addTranslation(Point2D p) {
		addTranslation(p.getX(), p.getY());
	}
	
	/** Ajoute une rotation autour de l'origine */
	public void addRotation(double radian) {
		double cos = Math.cos(radian);
		double sin = Math.sin(radian);
		double x = matrix.get(0, 2);
		double y = matrix.get(1, 2);
		// Matrice de rotation
		double r[][] = new double[][] {
				{cos	, -sin	},
				{sin	, cos	}
		};
		// Rotation de l'origine
		double m02 = r[0][0] * x + r[0][1] * y;
		double m12 = r[1][0] * x + r[1][1] * y;
		matrix.set(0, 2, m02);
		matrix.set(1, 2, m12);
		// Rotation des axes
		double m00 = r[0][0] * matrix.get(0, 0) + r[0][1] * matrix.get(1, 0);
		double m01 = r[0][0] * matrix.get(0, 1) + r[0][1] * matrix.get(1, 1);
		double m10 = r[1][0] * matrix.get(0, 0) + r[1][1] * matrix.get(1, 0);
		double m11 = r[1][0] * matrix.get(0, 1) + r[1][1] * matrix.get(1, 1);
		matrix.set(0, 0, m00);
		matrix.set(0, 1, m01);
		matrix.set(1, 0, m10);
		matrix.set(1, 1, m11);
	}
	
	/** Ajoute une rotation autour du point (x,y) */
	public void addRotation(double x, double y, double radian) {
		addTranslation(-x, -y);
		addRotation(radian);
		addTranslation(x, y);		
	}
	
	/** Ajoute une rotation autour du point p */
	public void addRotation(Point2D p, double radian) {
		addRotation(p.getX(), p.getY(), radian);
	}
	
	/** Ajoute une homothetie a la transformation */
	public void addScale(double sx, double sy) {
		matrix.set(0, 0, sx * matrix.get(0, 0));
		matrix.set(1, 1, sy * matrix.get(1, 1));
	}
	
	/** Ajoute un cisaillement a la transformation */
	public void addShear(double shx, double shy) {
		matrix.set(0, 1, shx + matrix.get(0, 1));
		matrix.set(1, 0, shy + matrix.get(1, 0));
	}
	
	/** Combine la transformation courante avec la transformation t */
	public void compose(Transformation2D t) {
		Matrix3D m = matrix.multiply(t.matrix);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				matrix.set(i, j, m.get(i, j));
			}
		}
	}
	
	/** Combine les deux transformations t1 et t2 */
	public static Transformation2D compose(Transformation2D t1, Transformation2D t2) {
		Transformation2D t = new Transformation2D(t1);
		t.compose(t2);
		return t;
	}
	
	/** Representation textuelle d'une transformation */
	@Override
	public String toString() {
		return matrix.toString();
	}
}

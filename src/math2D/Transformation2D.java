package math2D;

import math3D.Matrix3D;

public class Transformation2D {
	
	private Matrix3D matrix;
	
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
		this(new Matrix3D());
	}
	
	public static class Translation extends Transformation2D {
		
		/** Constructeur */
		public Translation(double dx, double dy) {
			super();
			addTranslation(dx, dy);
		}
	}
	
	public static class Rotation extends Transformation2D {
		
		/** Constructeur */
		public Rotation(double radian) {
			super();
			addRotation(radian);
		}
	}
	
	public static class Scale extends Transformation2D {
		
		/** Constructeur */
		public Scale(double sx, double sy) {
			super();
			addScale(sx, sy);
		}
	}
	
	/** Calcule et retourne la Base2D associee a la transformation */
	public Base2D toBase2D() {
		double x1 = matrix.get(0, 0);
		double y1 = matrix.get(1, 0);
		double x2 = matrix.get(0, 1);
		double y2 = matrix.get(1, 1);
		Vecteur2D ox = new Vecteur2D(x1, y1);
		Vecteur2D oy = new Vecteur2D(x2, y2);
		Point2D origine = new Point2D(matrix.get(0, 2), matrix.get(1, 2));
		return new Base2D(origine, ox, oy);
	}
	
	/** Retourne la transformation inverse */
	public Transformation2D getInverseTransformation() {
		return new Transformation2D(matrix.getInverse());
	}
	
	/** Calcul le resultat de la transformation par le Point2D p */
	public Point2D transform(Point2D p) {
		double[] v = new double[] {p.getX(), p.getY(), 1};
		double[] r = multiplication(v);
		return new Point2D(r[0]/r[2], r[1]/r[2]);
	}
	
	/** Effectue la multiplication matrix/point */
	private double[] multiplication(double[] v) {
		double[] r = new double[3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				r[i] += matrix.get(i, j) * v[j];
			}
		}
		return r;
	}
	
	/** Revient sur la transformation identite */
	public void clear() {
		double d;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				d = (i == j ? 1 : 0);
				matrix.set(i, j, d);
			}
		}
	}
	
	/** Ajoute une translation a la transformation */
	public void addTranslation(double dx, double dy) {
		matrix.set(0, 2, matrix.get(0, 2) + dx);
		matrix.set(1, 2, matrix.get(1, 2) + dy);
	}
	
	/** Ajoute une rotation a la transformation */
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
	
	/** Ajoute une homothetie a la transformation */
	public void addScale(double sx, double sy) {
		matrix.set(0, 0, sx * matrix.get(0, 0));
		matrix.set(1, 1, sy * matrix.get(1, 1));
	}
	
	/** Ajoute une transformation a la transformation */
	public void addTransformation(Transformation2D m) {
		Matrix3D r = matrix.mult(m.matrix);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				matrix.set(i, j, r.get(i, j));
			}
		}
	}
	
	/** Combine les deux transformations m1 et m2 */
	public static Transformation2D addTransformation(Transformation2D m1, Transformation2D m2) {
		Transformation2D r = new Transformation2D(m1);
		r.addTransformation(m2);
		return r;
	}
	
	/** Representation textuelle d'une transformation */
	public String toString() {
		return matrix.toString();
	}
}

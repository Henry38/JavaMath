package math3D;

import math4D.Matrix4D;

public class Transformation3D {
	
	private Matrix4D matrix;
	
	/** Constructeur */
	public Transformation3D(Matrix4D matrix) {
		this.matrix = new Matrix4D(matrix);
	}
	
	/** Constructeur par copie */
	public Transformation3D(Transformation3D t) {
		this(t.matrix);
	}
	
	/** Constructeur par defaut */
	public Transformation3D() {
		this(new Matrix4D.Identity());
	}
	
	/** Calcule et retourne la Base2D associee a la transformation */
	public Base3D toBase3D() {
		double x1 = matrix.get(0, 0);
		double y1 = matrix.get(1, 0);
		double z1 = matrix.get(2, 0);
		double x2 = matrix.get(0, 1);
		double y2 = matrix.get(1, 1);
		double z2 = matrix.get(2, 1);
		double x3 = matrix.get(0, 2);
		double y3 = matrix.get(1, 2);
		double z3 = matrix.get(2, 2);
		Vecteur3D ox = new Vecteur3D(x1, y1, z1);
		Vecteur3D oy = new Vecteur3D(x2, y2, z2);
		Vecteur3D oz = new Vecteur3D(x3, y3, z3);
		Point3D origine = new Point3D(matrix.get(0, 2), matrix.get(1, 2), matrix.get(2, 2));
		return new Base3D(origine, ox, oy, oz);
	}
	
	/** Retourne la transformation inverse */
	public Transformation3D getInverseTransformation() {
		return new Transformation3D(matrix.getInverse());
	}
	
	/** Calcul le resultat de la transformation par le Point3D p */
	public Point3D transform(Point3D p) {
		double[] v = new double[] {p.getX(), p.getY(), p.getZ(), 1};
		double[] r = multiply(v);
		return new Point3D(r[0]/r[3], r[1]/r[3], r[2]/r[3]);
	}
	
	/** Effectue la multiplication matrix/point */
	private double[] multiply(double[] v) {
		double[] r = new double[4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				r[i] += matrix.get(i, j) * v[j];
			}
		}
		return r;
	}
	
	/** Revient sur la transformation identite */
	public void clear() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				double d = (i == j ? 1 : 0);
				matrix.set(i, j, d);
			}
		}
	}
	
	/** Ajoute une translation a la transformation */
	public void addTranslation(double dx, double dy, double dz) {
		matrix.set(0, 3, matrix.get(0, 3) + dx);
		matrix.set(1, 3, matrix.get(1, 3) + dy);
		matrix.set(2, 3, matrix.get(2, 3) + dz);
	}
	
	/** Ajoute une translation a la transformation */
	public void addTranslation(Vecteur3D vect) {
		addTranslation(vect.getDx(), vect.getDy(), vect.getDz());
	}
	
	/** Ajoute une rotation a la transformation */
	public void addRotation(Vecteur3D axe, double radian) {
		double cos = Math.cos(radian);
		double sin = Math.sin(radian);
		double x = matrix.get(0, 3);
		double y = matrix.get(1, 3);
		double z = matrix.get(2, 3);
		double d = axe.getNorm();
		double dx = axe.getDx() / d;
		double dy = axe.getDy() / d;
		double dz = axe.getDz() / d;
		double mc = 1 - cos;
		// Matrice de rotation
		double[][] r = new double[][] {
				{dx*dx*mc + cos		, dx*dy*mc - dz*sin	, dx*dz*mc + dy*sin	},
				{dx*dy*mc + dz*sin	, dy*dy*mc + cos	, dy*dz*mc - dx*sin	},
				{dx*dz*mc - dy*sin	, dy*dz*mc + dx*sin	, dz*dz*mc + cos	}
		};
		// Rotation de l'origine
		double m03 = r[0][0] * x + r[0][1] * y + r[0][2] * z;
		double m13 = r[1][0] * x + r[1][1] * y + r[1][2] * z;
		double m23 = r[2][0] * x + r[2][1] * y + r[2][2] * z;
		matrix.set(0, 3, m03);
		matrix.set(1, 3, m13);
		matrix.set(2, 3, m23);
		// Rotation des axes
		double m00 = r[0][0] * matrix.get(0, 0) + r[0][1] * matrix.get(1, 0) + r[0][2] * matrix.get(2, 0);
		double m01 = r[0][0] * matrix.get(0, 1) + r[0][1] * matrix.get(1, 1) + r[0][2] * matrix.get(2, 1);
		double m02 = r[0][0] * matrix.get(0, 2) + r[0][1] * matrix.get(1, 2) + r[0][2] * matrix.get(2, 2);
		double m10 = r[1][0] * matrix.get(0, 0) + r[1][1] * matrix.get(1, 0) + r[1][2] * matrix.get(2, 0);
		double m11 = r[1][0] * matrix.get(0, 1) + r[1][1] * matrix.get(1, 1) + r[1][2] * matrix.get(2, 1);
		double m12 = r[1][0] * matrix.get(0, 2) + r[1][1] * matrix.get(1, 2) + r[1][2] * matrix.get(2, 2);
		double m20 = r[2][0] * matrix.get(0, 0) + r[2][1] * matrix.get(1, 0) + r[2][2] * matrix.get(2, 0);
		double m21 = r[2][0] * matrix.get(0, 1) + r[2][1] * matrix.get(1, 1) + r[2][2] * matrix.get(2, 1);
		double m22 = r[2][0] * matrix.get(0, 2) + r[2][1] * matrix.get(1, 2) + r[2][2] * matrix.get(2, 2);
		matrix.set(0, 0, m00);
		matrix.set(0, 1, m01);
		matrix.set(0, 2, m02);
		matrix.set(1, 0, m10);
		matrix.set(1, 1, m11);
		matrix.set(1, 2, m12);
		matrix.set(2, 0, m20);
		matrix.set(2, 1, m21);
		matrix.set(2, 2, m22);
	}
	
	/** Ajoute une homothetie a la transformation */
	public void addScale(double sx, double sy, double sz) {
		matrix.set(0, 0, sx * matrix.get(0, 0));
		matrix.set(1, 1, sy * matrix.get(1, 1));
		matrix.set(2, 2, sz * matrix.get(2, 2));
	}
	
	/** Combine la transformation courante avec transformation t */
	public void compose(Transformation3D t) {
		Matrix4D m = matrix.mult(t.matrix);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				matrix.set(i, j, m.get(i, j));
			}
		}
	}
	
	/** Combine les deux transformations t1 et t2 */
	public static Transformation3D compose(Transformation3D t1, Transformation3D t2) {
		Transformation3D t = new Transformation3D(t1);
		t.compose(t2);
		return t;
	}
	
	/** Representation textuelle d'une transformation */
	@Override
	public String toString() {
		return matrix.toString();
	}
}

package math4D;

import math3D.Matrix3D;

public class Matrix4D {
	
	private double[][] matrix;
	
	/** Constructeur */
	public Matrix4D() {
		this.matrix = new double[][] {
				{0, 0, 0, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 0}
		};
	}
	
	/** Constructeur par copie */
	public Matrix4D(Matrix4D matrix) {
		this();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				this.matrix[i][j] = matrix.get(i, j);
			}
		}
	}
	
	/** Retourne l'element sur la ligne i, colonne j */
	public double get(int i, int j) {
		return matrix[i][j];
	}
	
	/** Set l'element ligne i, colonne j */
	public void set(int i, int j, double d) {
		matrix[i][j] = d;
	}
	
	/** Retourne la matrice privee de la ligne u et de la colonne v */
	public Matrix3D getSubMatrix(int u, int v) {
		Matrix3D m = new Matrix3D();
		for (int i = 0; i < 4; i++) {
			int idest = (i > u ? i-1 : i);
			for (int j = 0; j < 4; j++) {
				int jdest = (j > v ? j-1 : j);
				if (i != u && j != v) {
					m.set(idest, jdest, get(i, j));
				}
			}
		}
		return m;
	}
	
	/** Retourne le determinant de la matrice */
	public double getDeterminant() {
		double det = 0.0;
		int i = 1;
		for (int n = 0; n < 4; n++, i *= -1) {
			Matrix3D subMat = getSubMatrix(0, n);
			det += get(0, n) * subMat.getDeterminant() * i;
		}
		return det;
	}
	
	/** Retourne la matrice inverse */
	public Matrix4D getInverse() {
		Matrix4D m = new Matrix4D();
		double idet = 1.0 / getDeterminant();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int sign = 1 - ((i + j) % 2 ) * 2;
				Matrix3D subMat = getSubMatrix(j, i);
				m.set(i, j, idet * subMat.getDeterminant() * sign);
			}
		}
		return m;
	}
	
	/** Retourne la multiplication matricielle par la matrice matrix */
	public Matrix4D mult(Matrix4D matrix) {
		Matrix4D m = new Matrix4D();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				double d = 0;
				for (int k = 0; k < 4; k++) {
					d += get(i, k) * matrix.get(k, j);
				}
				m.set(i, j, d);
			}
		}
		return m;
	}
	
	/** Retourne la multiplication matricielle par le vecteur vect */
	public Vecteur4D mult(Vecteur4D vect) {
		double x = (get(0, 0) * vect.getDx()) + (get(0, 1) * vect.getDy()) + (get(0, 2) * vect.getDz()) + (get(0, 3) * vect.getDw());
		double y = (get(1, 0) * vect.getDx()) + (get(1, 1) * vect.getDy()) + (get(1, 2) * vect.getDz()) + (get(1, 3) * vect.getDw());
		double z = (get(2, 0) * vect.getDx()) + (get(2, 1) * vect.getDy()) + (get(2, 2) * vect.getDz()) + (get(2, 3) * vect.getDw());
		double w = (get(3, 0) * vect.getDx()) + (get(3, 1) * vect.getDy()) + (get(3, 2) * vect.getDz()) + (get(3, 3) * vect.getDw());
		return new Vecteur4D(x, y, z, w);
	}
	
	/** Representrion textuelle d'une matrice */
	@Override
	public String toString() {
		String s = "";
		for (int i = 0 ; i < 4; i++) {
			s += "[";
			for (int j = 0 ; j < 4; j++) {
				double rounded = Math.round(100 * get(i, j)) / 100.0;
				String digit = String.valueOf(rounded);
				if (rounded >= 0) {
					digit = " " + digit;
				}
				if (j < 3) {
					digit += ", ";
				}
				s += digit;
			}
			s += "]";
			if (i < 3) {
				s += "\n";
			}
		}
		return s;
	}
	
	public static class Identity extends Matrix4D {
		
		/** Constructeur */
		public Identity() {
			super();
			set(0, 0, 1);
			set(1, 1, 1);
			set(2, 2, 1);
			set(3, 3, 1);
		}
	}
}

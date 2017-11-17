package math3D;

import math2D.Matrix2D;

public class Matrix3D {
	
	private double[][] matrix;
	
	/** Constructeur */
	public Matrix3D() {
		this.matrix = new double[][] {
				{0, 0, 0},
				{0, 0, 0},
				{0, 0, 0}
		};
	}
	
	/** Constructeur par copie */
	public Matrix3D(Matrix3D matrix) {
		this();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
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
	
	/** Set la matrice m */
	public void set(Matrix3D m) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				set(i, j, m.get(i, j));
			}
		}
	}
	
	/** Retourne la matrice privee de la ligne u et de la colonne v */
	public Matrix2D getSubMatrix(int u, int v) {
		Matrix2D m = new Matrix2D();
		for (int i = 0; i < 3; i++) {
			int idest = (i > u ? i-1 : i);
			for (int j = 0; j < 3; j++) {
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
		return	+ get(0, 0) * (get(1, 1)*get(2, 2) - get(2, 1)*get(1, 2))
				- get(0, 1) * (get(1, 0)*get(2, 2) - get(2, 0)*get(1, 2))
				+ get(0, 2) * (get(1, 0)*get(2, 1) - get(2, 0)*get(1, 1));
	}
	
	/** Retourne la matrice inverse */
	public Matrix3D getInverse() {
		Matrix3D m = new Matrix3D();
		double idet = 1.0 / getDeterminant();
		m.set(0, 0,  idet * (get(1, 1)*get(2, 2) - get(2, 1)*get(1, 2)));
		m.set(0, 1, -idet * (get(0, 1)*get(2, 2) - get(2, 1)*get(0, 2)));
		m.set(0, 2,  idet * (get(0, 1)*get(1, 2) - get(1, 1)*get(0, 2)));
		m.set(1, 0, -idet * (get(1, 0)*get(2, 2) - get(2, 0)*get(1, 2)));
		m.set(1, 1,  idet * (get(0, 0)*get(2, 2) - get(2, 0)*get(0, 2)));
		m.set(1, 2, -idet * (get(0, 0)*get(1, 2) - get(1, 0)*get(0, 2)));
		m.set(2, 0,  idet * (get(1, 0)*get(2, 1) - get(2, 0)*get(1, 1)));
		m.set(2, 1, -idet * (get(0, 0)*get(2, 1) - get(2, 0)*get(0, 1)));
		m.set(2, 2,  idet * (get(0, 0)*get(1, 1) - get(1, 0)*get(0, 1)));
		return m;
	}
	
	/** Retourne la multiplication matricielle par la matrice matrix */
	public Matrix3D multiply(Matrix3D matrix) {
		Matrix3D m = new Matrix3D();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				double d = 0;
				for (int k = 0; k < 3; k++) {
					d += get(i, k) * matrix.get(k, j);
				}
				m.set(i, j, d);
			}
		}
		return m;
	}
	
	/** Retourne la multiplication matricielle par le vecteur vect */
	public Vecteur3D multiply(Vecteur3D vect) {
		double x = (get(0, 0) * vect.getDx()) + (get(0, 1) * vect.getDy()) + (get(0, 2) * vect.getDz());
		double y = (get(1, 0) * vect.getDx()) + (get(1, 1) * vect.getDy()) + (get(1, 2) * vect.getDz());
		double z = (get(2, 0) * vect.getDx()) + (get(2, 1) * vect.getDy()) + (get(2, 2) * vect.getDz());
		return new Vecteur3D(x, y, z);
	}
	
	/** Representrion textuelle d'une matrice */
	@Override
	public String toString() {
		String s = "[";
		for (int i = 0 ; i < 3; i++) {
			if (i > 0) {
				s += " ";
			}
			s += "[";
			for (int j = 0 ; j < 3; j++) {
				s += Math.round(1000*get(i, j))/1000.0 + " ";
				if (j < 2) {
					s += ",";
				}
			}
			s += "]";
			if (i < 2) {
				s += "\n";
			}
		}
		s += "]";
		return s;
	}
	
	public static class Identity extends Matrix3D {
		
		/** Constructeur */
		public Identity() {
			super();
			set(0, 0, 1);
			set(1, 1, 1);
			set(2, 2, 1);
		}
	}
}

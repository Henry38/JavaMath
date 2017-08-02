package math2D;

public class Matrix2D {
	
	private double[][] matrix;
	
	/** Constructeur */
	public Matrix2D() {
		this.matrix = new double[][] {
				{0, 0},
				{0, 0}
		};
	}
	
	/** Constructeur par copie */
	public Matrix2D(Matrix2D matrix) {
		this();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
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
	
	/** Retourne le determinant de la matrice */
	public double getDeterminant() {
		return (get(0, 0)*get(1, 1) - get(0, 1)*get(1, 0));
	}
	
	/** Retourne la matrice inverse */
	public Matrix2D getInverse() {
		Matrix2D m = new Matrix2D();
		double idet = 1.0 / getDeterminant();
		m.set(0, 0,  idet * get(1, 1));
		m.set(0, 1, -idet * get(0, 1));
		m.set(1, 0, -idet * get(1, 0));
		m.set(1, 1,  idet * get(0, 0));
		return m;
	}
	
	/** Retourne la multiplication matricielle par la matrice matrix */
	public Matrix2D multiply(Matrix2D matrix) {
		Matrix2D m = new Matrix2D();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				double d = 0;
				for (int k = 0; k < 2; k++) {
					d += get(i, k) * matrix.get(k, j);
				}
				m.set(i, j, d);
			}
		}
		return m;
	}
	
	/** Retourne la multiplication matricielle par le vecteur vect */
	public Vecteur2D multiply(Vecteur2D vect) {
		double x = (get(0, 0) * vect.getDx()) + (get(0, 1) * vect.getDy());
		double y = (get(1, 0) * vect.getDx()) + (get(1, 1) * vect.getDy());
		return new Vecteur2D(x, y);
	}
	
	/** Representrion textuelle d'une matrice */
	@Override
	public String toString() {
		String s = "[";
		for (int i = 0 ; i < 2; i++) {
			if (i > 0) {
				s += " ";
			}
			s += "[";
			for (int j = 0 ; j < 2; j++) {
				s += Math.round(1000*get(i, j))/1000.0 + " ";
				if (j < 1) {
					s += ",";
				}
			}
			s += "]";
			if (i < 1) {
				s += "\n";
			}
		}
		s += "]";
		return s;
	}
	
	public static class Identity extends Matrix2D {
		
		/** Constructeur */
		public Identity() {
			super();
			set(0, 0, 1);
			set(1, 1, 1);
		}
	}
}

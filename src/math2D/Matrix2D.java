package math2D;

public class Matrix2D {
	
	private double[][] matrix;
	
	/** Constructeur */
	public Matrix2D() {
		this.matrix = new double[][] {
				{1, 0},
				{0, 1}
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
	
	/** Retourne la multiplication matricielle par la matrice matrix */
	public Matrix2D mult(Matrix2D matrix) {
		Matrix2D res = new Matrix2D();
		double d;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				d = 0;
				for (int k = 0; k < 2; k++) {
					d += get(i, k) * matrix.get(k, j);
				}
				res.set(i, j, d);
			}
		}
		return res;
	}
	
	/** Retourne la multiplication matricielle par le vecteur vect */
	public Vecteur2D mult(Vecteur2D vect) {
		double x = (get(0, 0) * vect.getDx()) + (get(0, 1) * vect.getDy());
		double y = (get(1, 0) * vect.getDx()) + (get(1, 1) * vect.getDy());
		return new Vecteur2D(x, y);
	}
	
	/** Representrion textuelle d'une matrice */
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
}

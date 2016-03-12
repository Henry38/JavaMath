package math4D;

public class Matrix4D {
	
	private double[][] matrix;
	
	/** Constructeur */
	public Matrix4D() {
		this.matrix = new double[][] {
				{1, 0, 0, 0},
				{0, 1, 0, 0},
				{0, 0, 1, 0},
				{0, 0, 0, 1}
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
	
	/** Retourne la multiplication matricielle par la matrice matrix */
	public Matrix4D mult(Matrix4D matrix) {
		Matrix4D res = new Matrix4D();
		double d;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				d = 0;
				for (int k = 0; k < 4; k++) {
					d += get(i, k) * matrix.get(k, j);
				}
				res.set(i, j, d);
			}
		}
		return res;
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
	public String toString() {
		String s = "[";
		for (int i = 0 ; i < 4; i++) {
			if (i > 0) {
				s += " ";
			}
			s += "[";
			for (int j = 0 ; j < 4; j++) {
				s += Math.round(1000*get(i, j))/1000.0 + " ";
				if (j < 3) {
					s += ",";
				}
			}
			s += "]";
			if (i < 3) {
				s += "\n";
			}
		}
		s += "]";
		return s;
	}
}

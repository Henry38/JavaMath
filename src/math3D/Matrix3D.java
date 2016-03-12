package math3D;

public class Matrix3D {
	
	private double[][] matrix;
	
	/** Constructeur */
	public Matrix3D() {
		this.matrix = new double[][] {
				{1, 0, 0},
				{0, 1, 0},
				{0, 0, 1}
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
	
	/** Retourne la multiplication matricielle par la matrice matrix */
	public Matrix3D mult(Matrix3D matrix) {
		Matrix3D res = new Matrix3D();
		double d;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				d = 0;
				for (int k = 0; k < 3; k++) {
					d += get(i, k) * matrix.get(k, j);
				}
				res.set(i, j, d);
			}
		}
		return res;
	}
	
	/** Retourne la multiplication matricielle par le vecteur vect */
	public Vecteur3D mult(Vecteur3D vect) {
		double x = (get(0, 0) * vect.getDx()) + (get(0, 1) * vect.getDy()) + (get(0, 2) * vect.getDz());
		double y = (get(1, 0) * vect.getDx()) + (get(1, 1) * vect.getDy()) + (get(1, 2) * vect.getDz());
		double z = (get(2, 0) * vect.getDx()) + (get(2, 1) * vect.getDy()) + (get(2, 2) * vect.getDz());
		return new Vecteur3D(x, y, z);
	}
	
	/** Representrion textuelle d'une matrice */
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
}

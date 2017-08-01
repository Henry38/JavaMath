package math4D;

import math3D.Point3D;
import math3D.Vecteur3D;
import utils.Tools;

public class Quaternion {
	
	private double real;
	private Vecteur3D imag;
	
	/** Constructeur */
	public Quaternion(double q1, double q2, double q3, double q4) {
		this.real = q1;
		this.imag = new Vecteur3D(q2, q3, q4);
	}
	
	/** Constructeur */
	public Quaternion(Vecteur3D axis, double radian) {
		double half_cos = Math.cos(radian / 2.0);
		double half_sin = Math.sin(radian / 2.0);
		double norm = axis.getNorm();
		if (norm < Tools.epsilon) {
			// Quaternion unite
			this.real = 1.0;
			this.imag = new Vecteur3D(0, 0, 0);
		} else {
			double ux = axis.getDx() / norm;
			double uy = axis.getDy() / norm;
			double uz = axis.getDz() / norm;
			this.real = half_cos;
			this.imag = new Vecteur3D(ux*half_sin, uy*half_sin, uz*half_sin);
		}
	}
	
	/** Constructeur */
	public Quaternion(Vecteur3D vect) {
		this(0, vect.getDx(), vect.getDy(), vect.getDz());
	}
	
	/** Constructeur */
	public Quaternion() {
		this(1,0,0,0);
	}
	
	/** TODO 
	 * constructeur via EulerAngle ou static fonction ?
	 * constructeur via Matrix4D ou static fonction ?
	 * */
	
	/** Constructeur par copie */
	public Quaternion(Quaternion quaternion) {
		this.real = quaternion.getReal();
		this.imag = new Vecteur3D(quaternion.getImag());
	}
	
	/** Retourne la parie reel du quaternion */
	public double getReal() {
		return this.real;
	}
	
	/** Retourne la parie imaginaire du quaternion */
	public final Vecteur3D getImag() {
		return this.imag;
	}
	
	/** Set la partie reel */
	public void setReal(double real) {
		this.real = real;
	}
	
	/** Set la partie imaginaire */
	public void setImag(double x, double y, double z) {
		this.imag.set(x, y, z);
	}
	
	/** Set la partie imaginaire */
	public void setImag(Vecteur3D imag) {
		this.imag.set(imag);
	}
	
	/** Retourne la norme du quaternion */
	public double getNorm() {
		return Math.pow(getReal(), 2) + Math.pow(getImag().getNorm(), 2);
	}
	
	/** Retourne le quaternion unitaire */
	public Quaternion getUnitQuaternion() {
		Quaternion q = new Quaternion(this);
		q.normalize();
		return this;
	}
	
	/** Retourne le quaternion inverse */
	public Quaternion getInverse() {
		Quaternion q = new Quaternion(this);
		q.inverse();
		return q;
	}
	
	/** Retourne le quaternion conjuge */
	public Quaternion getConjugate() {
		Quaternion q = new Quaternion(this);
		q.conjugate();
		return q;
	}
	
	/** Normalise le quaternion */
	public void normalize() {
		double norm = getNorm();
		setReal(getReal() / norm);
		getImag().divide(norm);
	}
	
	/** Inverse le quaternion */
	public void inverse() {
		double norm = getNorm();
		conjugate();
		setReal(getReal() / norm);
		getImag().divide(norm);
	}
	
	/** Conjugue le quaternion */
	public void conjugate() {
		getImag().opposite();
	}
	
	/** Ajoute le quaternion passe en parametre */
	public Quaternion add(Quaternion q) {
		Quaternion quat = new Quaternion();
		quat.setReal(getReal() + q.getReal());
		quat.setImag(getImag().add(q.getImag()));
		return quat;
	}
	
	/** Calcul le produit de deux quaternion */
	public Quaternion multiply(Quaternion q) {
		Vecteur3D imag = new Vecteur3D(getImag());
		Vecteur3D qimag = new Vecteur3D(q.getImag());
		
		double a1 = getReal();
		double b1 = imag.getDx();
		double c1 = imag.getDy();
		double d1 = imag.getDz();
		double a2 = q.getReal();
		double b2 = qimag.getDx();
		double c2 = qimag.getDy();
		double d2 = qimag.getDz();
		
		double q1 = a1*a2 - b1*b2 - c1*c2 - d1*d2;
		double q2 = a1*b2 + b1*a2 + c1*d2 - d1*c2;
		double q3 = a1*c2 + c1*a2 + d1*b2 - b1*d2;
		double q4 = a1*d2 + d1*a2 + b1*c2 - c1*b2;
		
		return new Quaternion(q1, q2, q3, q4);
	}
	
	/** Applique la rotation au vecteur passe en parametre */
	public Vecteur3D multiply(Vecteur3D vect) {
		Quaternion v = new Quaternion(vect);
		Quaternion quat = getUnitQuaternion();
		Quaternion conjugate = quat.getConjugate();
		Quaternion rotated = quat.multiply(v).multiply(conjugate);
		return rotated.getImag();
	}
	
	/** Applique la rotation au vecteur passe en parametre */
	public Point3D multiply(Point3D p) {
		Vecteur3D rotated = multiply(new Vecteur3D(p));
		return new Point3D(rotated.getDx(), rotated.getDy(), rotated.getDz());
	}
	
	/** Representation textuelle d'un Quaternion */
	public String toString() {
		Vecteur3D vect = getImag();
		return "(" + ((int)(getReal()*100))/100.0 + " , " + ((int)(vect.getDx()*100))/100.0 + " , " + ((int)(vect.getDy()*100))/100.0 + " , " + ((int)(vect.getDz()*100))/100.0 + ")";
	}
	
	public Matrix4D toMatrix() {
		double a = getReal();
		double b = imag.getDx();
		double c = imag.getDy();
		double d = imag.getDz();
//		{
//			{ a, -b, -c, -d},
//			{ b,  a, -d,  c},
//			{ c,  d,  a, -b},
//			{ d, -c,  b,  a}
//		};
		Matrix4D m = new Matrix4D();
		m.set(0, 0,  a); m.set(0, 1, -b); m.set(0, 2, -c); m.set(0, 3, -d);
		m.set(1, 0,  b); m.set(1, 1,  a); m.set(1, 2, -d); m.set(1, 3,  c);
		m.set(2, 0,  c); m.set(2, 1,  d); m.set(2, 2,  a); m.set(2, 3, -b);
		m.set(3, 0,  d); m.set(3, 1, -c); m.set(3, 2,  b); m.set(3, 3,  a);
		return m;
	}
	
	public static Quaternion fromEulerAngle(double heading, double attitude, double bank) {
		double PI2 = 2 * Math.PI;
		heading = heading % PI2;
		attitude = attitude % PI2;
		bank = bank % PI2;
		return null;
	}
}

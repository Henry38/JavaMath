package math4D;

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
	
//	/** Constructeur */
//	public Quaternion(double ux, double uy, double uz, double radian) {
//		double half_cos = Math.cos(radian / 2.0);
//		double half_sin = Math.sin(radian / 2.0);
//		this.real = half_cos;
//		this.imag = new Vecteur3D(ux*half_sin, uy*half_sin, uz*half_sin);
//	}
	
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
	public Quaternion() {
		this(0,0,0,1);
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
		this.imag.divide(norm);
	}
	
	/** Inverse le quaternion */
	public void inverse() {
		double norm = getNorm();
		Math.pow(norm, 2)
		conjugate();
		//this.imag.opposite();
	}
	
	/** Conjugue le quaternion */
	public void conjugate() {
		this.imag.opposite();
	}
	
	/** Calcul le produit de deux quaternion */
	public Quaternion multiply(Quaternion q) {
		double real = getReal();
		double qreal = q.getReal();
		Vecteur3D imag = new Vecteur3D(getImag());
//		double imag1 = imag.getDx();
//		double imag2 = imag.getDy();
//		double imag3 = imag.getDz();
		Vecteur3D qimag = new Vecteur3D(q.getImag());
//		double qimag1 = qimag.getDx();
//		double qimag2 = qimag.getDy();
//		double qimag3 = qimag.getDz();
		Vecteur3D cross = Vecteur3D.cross_product(imag, qimag);
		setReal( (real * qreal) - Vecteur3D.scalar_product(imag, qimag) );
		setImag( qimag.multiply(real).add(imag.multiply(qreal)).add(cross) );
		return this;
	}
	
	/** Multiplie le quaternion par le scalaire k */
	public Quaternion multiply(double k) {
		setReal(k * getReal());
		this.imag.multiply(k);
		return this;
	}
	
	/** Divise le quaternion par le scalaire k */
	public Quaternion divide(double k) {
		setReal(getReal() / k);
		this.imag.divide(k);
		return this;
	}
	
//	public static Quaternion fromEulerAngle(double heading, double attitude, double bank) {
//		
//		return null;
//	}
}

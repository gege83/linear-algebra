package com.gergo.takacs.linearAlgebra

case class Vector(coordinates: List[Double]) {

  if (coordinates.isEmpty) throw EmptyCollectionNotPermitted()

  def dimension(): Int = coordinates.length

  def add(vector: Vector): Vector = {
    val newCoordinates = (coordinates, vector.coordinates).zipped.map(_ + _)
    Vector(newCoordinates)
  }

  def sub(vector: Vector): Vector = {
    val newCoordinates = (coordinates, vector.coordinates).zipped.map(_ - _)
    Vector(newCoordinates)
  }

  def +(vector: Vector): Vector = add(vector)

  def -(vector: Vector): Vector = sub(vector)

  def *(i: Double) = Vector(coordinates.map(_ * i))

  def *:(i: Double): Vector = this * i

  def *(vector: Vector): Double = {
    (coordinates, vector.coordinates).zipped.map(_ * _).sum
  }

  def magnitude(): Double = {
    val squareSum = coordinates.map(a => a * a).sum
    math.sqrt(squareSum)
  }

  def normalize(): Vector = this * (1 / this.magnitude())

  override def equals(that: scala.Any): Boolean = {
    that match {
      case that: Vector => (this - that).magnitude() < 10e-10
      case _ => false
    }
  }

  def angle(vector: Vector): Double = {
    val cosAngle = this * vector / (magnitude() * vector.magnitude())
    math.acos(cosAngle)
  }

  def angleDeg(vector: Vector): Double = math.toDegrees(this.angle(vector))

  def isOrthogonal(vector: Vector, tolerance: Double = 10e-10): Boolean = isZero(this * vector, 10e-10)

  def isParallel(vector: Vector, tolerance: Double = 10e-10): Boolean = {
    val angles = angle(vector)
    isPi(angles, tolerance) || isZero(angles, tolerance)
  }

  private def isZero(angles: Double, tolerance: Double): Boolean = math.abs(angles) < tolerance

  private def isPi(angles: Double, tolerance: Double): Boolean = isZero(angles - math.Pi, tolerance)

  def isZero(tolerance: Double = 10e-10): Boolean = isZero(magnitude(), tolerance)

  def projectTo(vector: Vector): Vector = {
    val unit = vector.normalize()
    unit * (this * unit)
  }

  def projectToOrthogonal(vector: Vector): Vector = this - projectTo(vector)

  def crossProduct(vector: Vector): Vector = {
    val (x1, y1, z1) = createTuple(this)
    val (x2, y2, z2) = createTuple(vector)
    Vector(y1 * z2 - y2 * z1, x2 * z1 - x1 * z2, x1 * y2 - x2 * y1)
  }

  private def createTuple(vector: Vector) = {
    vector.coordinates match {
      case List(a, b, c) => (a, b, c)
      case List(a, b) => (a, b, 0d)
      case _ => throw new NotImplementedError()
    }
  }

  def parallelogramArea(vector: Vector): Double = this.crossProduct(vector).magnitude()

  def triangleArea(vector: Vector): Double = parallelogramArea(vector) / 2
}

object Vector {
  def apply(first: Double, coordinates: Double*): Vector = Vector(first :: List[Double](coordinates: _*))
}
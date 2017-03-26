package com.gergo.takacs.linearAlgebra

import org.scalatest.{FlatSpec, Matchers}


class VectorTest extends FlatSpec with Matchers {

  "Vector dimension" should "be equal with the length of the creator collection" in {
    val underTest = Vector(2, 2, 3)
    underTest.dimension() should be(3)
  }

  it should "throw exception in case of empty Collection" in {
    a[EmptyCollectionNotPermitted] should be thrownBy {
      Vector(List())
    }
  }

  "Add vector to this" should "create a new vector with coordinates added each" in {
    val actual = Vector(2, 3) + Vector(2, 3)
    actual should be(Vector(4, 6))
  }

  "Subtract vector to this" should "create a new vector with coordinates subtracted each" in {
    val actual = Vector(6, 3) - Vector(2, 3)
    actual should be(Vector(4, 0))
  }

  "Vector multiply by scalar" should "have scalar times coordinates" in {
    val actual = Vector(3, 4) * 2
    actual should be(Vector(6, 8))
  }

  "Scalar multiply by Vector" should "have scalar times coordinates" in {
    val actual = 2 *: Vector(3, 4)
    actual should be(Vector(6, 8))
  }

  "Vector multiply by Vector" should "return with inner product" in {
    val actual = Vector(2, 3) * Vector(3, 4)
    actual should be(18)
  }

  "Vector magnitude" should "return with vector length" in {
    val actual = Vector(3, 4).magnitude()
    actual should be(5)
  }

  "Vector normalization" should "return a vector with length of 1" in {
    val actual = Vector(3, 4).normalize()
    actual should be(Vector(.6, .8))
  }

  "Angle of two vectors" should "return a radian value" in {
    val actual = Vector(2, 3).angle(Vector(3, -2))
    actual should be(math.Pi / 2)
  }

  "Angle of two vectors in degrees" should "return a radian value" in {
    val actual = Vector(2, 3).angleDeg(Vector(3, -2))
    actual should be(90)
  }

  "If two Vector is orthogonal" should "return true" in {
    val actual = Vector(2, 3).isOrthogonal(Vector(3, -2))
    actual should be(true)
  }

  "If two Vector is parallel" should "return true" in {
    val actual = Vector(2, 3).isParallel(Vector(6, 9))
    actual should be(true)
  }

  "If two Vector is parallel but different direction" should "return true" in {
    val actual = Vector(2, 3).isParallel(Vector(-6, -9))
    actual should be(true)
  }

  "If null vector isZero" should "return true" in {
    val actual = Vector(0, 0).isZero()
    actual should be(true)
  }

  "Project a vector to an other one" should "return a parallel vector with the base" in {
    val actual = Vector(2, 20).projectTo(Vector(3, 0))
    actual should be(Vector(2, 0))
  }

  "Project a vector to the orthogonal of an other vector" should "return a orthogonal vector" in {
    val actual = Vector(2, 20).projectToOrthogonal(Vector(3, 0))
    actual should be(Vector(0, 20))
  }

  "Two Vector cross product" should "return a vector" in {
    val actual = Vector(5, 0, 0).crossProduct(Vector(0, 4, 0))
    actual should be(Vector(0, 0, 20))
  }

  "Two Vector cross product for 2D" should "return a vector" in {
    val actual = Vector(5, 0).crossProduct(Vector(0, 4))
    actual should be(Vector(0, 0, 20))
  }

  "Two Vector parallelogram area" should "be the length of cross product vector" in {
    val actual = Vector(5, 0, 0).parallelogramArea(Vector(0, 4, 0))
    actual should be(20)
  }

  "Two Vector triangle area" should "be half of the length of cross product vector" in {
    val actual = Vector(5, 0, 0).triangleArea(Vector(0, 4, 0))
    actual should be(10)
  }
}

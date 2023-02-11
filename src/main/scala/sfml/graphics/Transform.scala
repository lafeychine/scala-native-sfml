package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.Transform.*
import system.Vector2

//format: off
final case class Transform private[sfml] (val matrix: Array[Float]):

    private[sfml] inline def toNativeTransform(using Zone): sfTransform =
        val transform = alloc[sfTransform]()

        for i <- 0 until 16 do transform(i) = matrix(i)
        transform

    def combine(rhs: Transform): Transform =
        val a = matrix
        val b = rhs.matrix

        val result = Transform(
            a(0) * b(0)  + a(4) * b(1)  + a(12) * b(3),
            a(0) * b(4)  + a(4) * b(5)  + a(12) * b(7),
            a(0) * b(12) + a(4) * b(13) + a(12) * b(15),
            a(1) * b(0)  + a(5) * b(1)  + a(13) * b(3),
            a(1) * b(4)  + a(5) * b(5)  + a(13) * b(7),
            a(1) * b(12) + a(5) * b(13) + a(13) * b(15),
            a(3) * b(0)  + a(7) * b(1)  + a(15) * b(3),
            a(3) * b(4)  + a(7) * b(5)  + a(15) * b(7),
            a(3) * b(12) + a(7) * b(13) + a(15) * b(15)
        )

        for i <- 0 until 16 do matrix(i) = result.matrix(i)
        result

    def rotate(angle: Float): Transform =
        val cos = math.cos(angle).toFloat
        val sin = math.sin(angle).toFloat

        combine(Transform(
             cos, sin, 0,
            -sin, cos, 0,
             0,   0,   1
        ))

    def rotate(angle: Float, centerX: Float, centerY: Float): Transform =
        val rad = angle * math.Pi.toFloat / 180
        val cos = math.cos(angle).toFloat
        val sin = math.sin(angle).toFloat

        combine(Transform(
             cos, -sin, centerX * (1 - cos) + centerY * sin,
            -sin,  cos, centerY * (1 - cos) - centerX * sin,
             0,    0,   1
        ))

    def rotate(angle: Float, center: Vector2[Float]): Transform =
        rotate(angle, center.x, center.y)

    def scale(scaleX: Float, scaleY: Float): Transform =
        combine(Transform(
            scaleX, 0,      0,
            0,      scaleY, 0,
            0,      0,      1
        ))

    def scale(factors: Vector2[Float]): Transform =
        scale(factors.x, factors.y)

    def scale(scaleX: Float, scaleY: Float, centerX: Float, centerY: Float): Transform =
        combine(Transform(
            scaleX, 0,      centerX * (1 - scaleX),
            0,      scaleY, centerY * (1 - scaleY),
            0,      0,      1
        ))

    def scale(factors: Vector2[Float], center: Vector2[Float]): Transform =
        scale(factors.x, factors.y, center.x, center.y)

    def translate(x: Float, y: Float): Transform =
        combine(Transform(
            1, 0, x,
            0, 1, y,
            0, 0, 1
        ))

    def translate(offset: Vector2[Float]): Transform =
        translate(offset.x, offset.y)

    def transformPoint(x: Float, y: Float): Vector2[Float] =
        val transformedX = matrix(0) * x + matrix(4) * y + matrix(12)
        val transformedY = matrix(1) * x + matrix(5) * y + matrix(13)

        Vector2(transformedX, transformedY)

    def transformPoint(point: Vector2[Float]): Vector2[Float] =
        transformPoint(point.x, point.y)

    @SuppressWarnings(Array("org.wartremover.warts.Var"))
    def transformRect(rectangle: Rect[Float]): Rect[Float] =
        // Transform the 4 corners of the rectangle
        val points = Array(
            transformPoint(rectangle.left,                   rectangle.top),
            transformPoint(rectangle.left,                   rectangle.top + rectangle.height),
            transformPoint(rectangle.left + rectangle.width, rectangle.top),
            transformPoint(rectangle.left + rectangle.width, rectangle.top + rectangle.height)
        )

        // Compute the bounding rectangle of the transformed points
        var left   = points(0).x
        var top    = points(0).y
        var right  = points(0).x
        var bottom = points(0).y

        for i <- 1 until 4 do
            if points(i).x < left   then left = points(i).x
            if points(i).x > right  then right = points(i).x
            if points(i).y < top    then top = points(i).y
            if points(i).y > bottom then bottom = points(i).y

        Rect(left, top, right - left, bottom - top)

    def *(rhs: Transform): Transform =
        Transform(matrix.clone()).combine(rhs)

    def *=(rhs: Transform): Unit =
        combine(rhs)

object Transform:
    extension (transform: sfTransform)
        private[sfml] def toTransform(): Transform =
            val array = new Array[Float](16)

            for i <- 0 until 16 do array(i) = transform(i)
            Transform(array)

    def apply(): Transform = Transform.Identity()

    def apply(a00: Float, a01: Float, a02: Float, a10: Float, a11: Float, a12: Float, a20: Float, a21: Float, a22: Float): Transform =
        Transform(Array(
            a00, a10, 0, a20,
            a01, a11, 0, a21,
              0,   0, 1,   0,
            a02, a12, 0, a22
        ))

    def Identity(): Transform =
        Transform(Array(
            1, 0, 0, 0,
            0, 1, 0, 0,
            0, 0, 1, 0,
            0, 0, 0, 1
        ))

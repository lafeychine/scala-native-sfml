import org.junit.Assert.*
import org.junit.Test

import sfml.graphics.*
import sfml.window.*

/* Tests based on: https://github.com/SFML/SFML/blob/master/test/Graphics/BlendMode.test.cpp */
class ConstructorBlendMode:

    @Test def default(): Unit =
        val blendMode = BlendMode()

        assertEquals(BlendMode.Factor.SrcAlpha, blendMode.colorSrcFactor)
        assertEquals(BlendMode.Factor.OneMinusSrcAlpha, blendMode.colorDstFactor)
        assertEquals(BlendMode.Equation.Add, blendMode.colorEquation)
        assertEquals(BlendMode.Factor.One, blendMode.alphaSrcFactor)
        assertEquals(BlendMode.Factor.OneMinusSrcAlpha, blendMode.alphaDstFactor)
        assertEquals(BlendMode.Equation.Add, blendMode.alphaEquation)

    @Test def combinedColorAlphaDefaultParameter(): Unit =
        val blendMode = BlendMode(BlendMode.Factor.Zero, BlendMode.Factor.SrcColor)

        assertEquals(BlendMode.Factor.Zero, blendMode.colorSrcFactor)
        assertEquals(BlendMode.Factor.SrcColor, blendMode.colorDstFactor)
        assertEquals(BlendMode.Equation.Add, blendMode.colorEquation)
        assertEquals(BlendMode.Factor.Zero, blendMode.alphaSrcFactor)
        assertEquals(BlendMode.Factor.SrcColor, blendMode.alphaDstFactor)
        assertEquals(BlendMode.Equation.Add, blendMode.alphaEquation)

    @Test def combinedColorAlpha(): Unit =
        val blendMode = BlendMode(BlendMode.Factor.Zero, BlendMode.Factor.SrcColor, BlendMode.Equation.ReverseSubtract)

        assertEquals(BlendMode.Factor.Zero, blendMode.colorSrcFactor)
        assertEquals(BlendMode.Factor.SrcColor, blendMode.colorDstFactor)
        assertEquals(BlendMode.Equation.ReverseSubtract, blendMode.colorEquation)
        assertEquals(BlendMode.Factor.Zero, blendMode.alphaSrcFactor)
        assertEquals(BlendMode.Factor.SrcColor, blendMode.alphaDstFactor)
        assertEquals(BlendMode.Equation.ReverseSubtract, blendMode.alphaEquation)

    @Test def separateColorAlpha(): Unit =
        val blendMode = BlendMode(
            BlendMode.Factor.Zero,
            BlendMode.Factor.SrcColor,
            BlendMode.Equation.ReverseSubtract,
            BlendMode.Factor.OneMinusDstAlpha,
            BlendMode.Factor.DstAlpha,
            BlendMode.Equation.Add
        )

        assertEquals(BlendMode.Factor.Zero, blendMode.colorSrcFactor)
        assertEquals(BlendMode.Factor.SrcColor, blendMode.colorDstFactor)
        assertEquals(BlendMode.Equation.ReverseSubtract, blendMode.colorEquation)
        assertEquals(BlendMode.Factor.OneMinusDstAlpha, blendMode.alphaSrcFactor)
        assertEquals(BlendMode.Factor.DstAlpha, blendMode.alphaDstFactor)
        assertEquals(BlendMode.Equation.Add, blendMode.alphaEquation)

class ConstantsBlendMode:

    @Test def blendAlpha(): Unit =
        assertEquals(BlendMode.Factor.SrcAlpha, BlendMode.Alpha().colorSrcFactor)
        assertEquals(BlendMode.Factor.OneMinusSrcAlpha, BlendMode.Alpha().colorDstFactor)
        assertEquals(BlendMode.Equation.Add, BlendMode.Alpha().colorEquation)
        assertEquals(BlendMode.Factor.One, BlendMode.Alpha().alphaSrcFactor)
        assertEquals(BlendMode.Factor.OneMinusSrcAlpha, BlendMode.Alpha().alphaDstFactor)
        assertEquals(BlendMode.Equation.Add, BlendMode.Alpha().alphaEquation)

    @Test def blendAdd(): Unit =
        assertEquals(BlendMode.Factor.SrcAlpha, BlendMode.Add().colorSrcFactor)
        assertEquals(BlendMode.Factor.One, BlendMode.Add().colorDstFactor)
        assertEquals(BlendMode.Equation.Add, BlendMode.Add().colorEquation)
        assertEquals(BlendMode.Factor.One, BlendMode.Add().alphaSrcFactor)
        assertEquals(BlendMode.Factor.One, BlendMode.Add().alphaDstFactor)
        assertEquals(BlendMode.Equation.Add, BlendMode.Add().alphaEquation)

    @Test def blendMultiply(): Unit =
        assertEquals(BlendMode.Factor.DstColor, BlendMode.Multiply().colorSrcFactor)
        assertEquals(BlendMode.Factor.Zero, BlendMode.Multiply().colorDstFactor)
        assertEquals(BlendMode.Equation.Add, BlendMode.Multiply().colorEquation)
        assertEquals(BlendMode.Factor.DstColor, BlendMode.Multiply().alphaSrcFactor)
        assertEquals(BlendMode.Factor.Zero, BlendMode.Multiply().alphaDstFactor)
        assertEquals(BlendMode.Equation.Add, BlendMode.Multiply().alphaEquation)

    @Test def blendNone(): Unit =
        assertEquals(BlendMode.Factor.One, BlendMode.None().colorSrcFactor)
        assertEquals(BlendMode.Factor.Zero, BlendMode.None().colorDstFactor)
        assertEquals(BlendMode.Equation.Add, BlendMode.None().colorEquation)
        assertEquals(BlendMode.Factor.One, BlendMode.None().alphaSrcFactor)
        assertEquals(BlendMode.Factor.Zero, BlendMode.None().alphaDstFactor)
        assertEquals(BlendMode.Equation.Add, BlendMode.None().alphaEquation)

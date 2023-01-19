package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.BlendMode.*

sealed trait BlendModeTrait

final case class BlendMode(
    val colorSrcFactor: Factor,
    val colorDstFactor: Factor,
    val colorEquation: Equation,
    val alphaSrcFactor: Factor,
    val alphaDstFactor: Factor,
    val alphaEquation: Equation
):

    private[sfml] final def blendMode(implicit z: Zone): Ptr[sfBlendMode] =
        val blendMode = alloc[sfBlendMode]()

        blendMode._1 = colorSrcFactor.ordinal
        blendMode._2 = colorDstFactor.ordinal
        blendMode._3 = colorEquation.ordinal
        blendMode._4 = alphaSrcFactor.ordinal
        blendMode._5 = alphaDstFactor.ordinal
        blendMode._6 = alphaEquation.ordinal
        blendMode

object BlendMode:
    def apply(): BlendMode = BlendMode(
        Factor.SrcAlpha,
        Factor.OneMinusSrcAlpha,
        Equation.Add,
        Factor.One,
        Factor.Zero,
        Equation.Add
    )

    def apply(sourceFactor: Factor, destinationFactor: Factor): BlendMode =
        BlendMode(
            sourceFactor,
            destinationFactor,
            Equation.Add,
            sourceFactor,
            destinationFactor,
            Equation.Add
        )

    def apply(sourceFactor: Factor, destinationFactor: Factor, blendEquation: Equation): BlendMode =
        BlendMode(
            sourceFactor,
            destinationFactor,
            blendEquation,
            sourceFactor,
            destinationFactor,
            blendEquation
        )

    def Alpha(): BlendMode = BlendMode(
        Factor.SrcAlpha,
        Factor.OneMinusSrcAlpha,
        Equation.Add,
        Factor.One,
        Factor.OneMinusSrcAlpha,
        Equation.Add
    )

    def Add(): BlendMode = BlendMode(
        Factor.SrcAlpha,
        Factor.One,
        Equation.Add,
        Factor.One,
        Factor.One,
        Equation.Add
    )

    def Multiply(): BlendMode = BlendMode(
        Factor.DstColor,
        Factor.Zero
    )

    def None(): BlendMode = BlendMode(
        Factor.One,
        Factor.Zero
    )

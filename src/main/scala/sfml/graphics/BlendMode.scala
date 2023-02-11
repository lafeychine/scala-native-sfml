package sfml
package graphics

import scalanative.unsafe.*

import internal.graphics.BlendMode.*

/** Blending modes for drawing.
  *
  * [[sfml.graphics.BlendMode]] is a class that represents a blend mode.
  *
  * A blend mode determines how the colors of an object you draw are mixed with the colors that are already in the buffer.
  *
  * The class is composed of 6 components, each of which has its own public member variable:
  *   - Color Source Factor (`colorSrcFactor`)
  *   - Color Destination Factor (`colorDstFactor`)
  *   - Color Blend Equation (`colorEquation`)
  *   - Alpha Source Factor (`alphaSrcFactor`)
  *   - Alpha Destination Factor (`alphaDstFactor`)
  *   - Alpha Blend Equation (`alphaEquation`)
  *
  * The source factor specifies how the pixel you are drawing contributes to the final color. The destination factor specifies how
  * the pixel already in the buffer contributes to the final color.
  *
  * The color channels RGB (red, green, blue; simply referred to as color) and A (alpha; the transparency) can be treated
  * separately. This separation can be useful for specific blend modes, but most often you won't need it and will simply treat the
  * color as a single unit.
  *
  * The blend factors and equations correspond to their OpenGL equivalents. In general, the color of the resulting pixel is
  * calculated according to the following formula (`src` is the color of the source pixel, `dst` the color of the destination pixel,
  * the other variables correspond to the public members, with the equations being + or - operators):
  * ```scala sc:nocompile
  * dst.rgb = colorSrcFactor * src.rgb (colorEquation) colorDstFactor * dst.rgb
  * dst.a   = alphaSrcFactor * src.a   (alphaEquation) alphaDstFactor * dst.a
  * ```
  *
  * All factors and colors are represented as floating point numbers between 0 and 1. Where necessary, the result is clamped to fit
  * in that range.
  *
  * The most common blending modes are defined as constants:
  * ```scala
  * val alphaBlending = BlendMode.Alpha()
  * val additiveBlending = BlendMode.Add()
  * val multiplicativeBlending = BlendMode.Multiply()
  * val noBlending = BlendMode.None()
  * ```
  *
  * In SFML, a blend mode can be specified every time you draw a [[sfml.graphics.Drawable]] object to a render target. It is part of
  * the [[sfml.graphics.RenderStates]] compound that is passed to the member function [[sfml.graphics.RenderTarget#draw]].
  *
  * @param colorSrcFactor
  *   Source blending factor for the color channels.
  *
  * @param colorDstFactor
  *   Destination blending factor for the color channels.
  *
  * @param colorEquation
  *   Blending equation for the color channels.
  *
  * @param alphaSrcFactor
  *   Source blending factor for the alpha channel.
  *
  * @param alphaDstFactor
  *   Destination blending factor for the alpha channel.
  *
  * @param alphaEquation
  *   Blending equation for the alpha channel.
  *
  * @see
  *   [`RenderStates`](sfml.graphics.RenderStates), [`RenderTarget`](sfml.graphics.RenderTarget)
  */
final case class BlendMode(
    val colorSrcFactor: BlendMode.Factor,
    val colorDstFactor: BlendMode.Factor,
    val colorEquation: BlendMode.Equation,
    val alphaSrcFactor: BlendMode.Factor,
    val alphaDstFactor: BlendMode.Factor,
    val alphaEquation: BlendMode.Equation
):

    private[sfml] inline def toNativeBlendMode(using Zone): Ptr[sfBlendMode] =
        val blendMode = alloc[sfBlendMode]()

        blendMode._1 = colorSrcFactor.ordinal
        blendMode._2 = colorDstFactor.ordinal
        blendMode._3 = colorEquation.ordinal
        blendMode._4 = alphaSrcFactor.ordinal
        blendMode._5 = alphaDstFactor.ordinal
        blendMode._6 = alphaEquation.ordinal
        blendMode

object BlendMode:
    /** Enumeration of blending factors.
      *
      * <!-- TODO: OpenGL equivalents -->
      */
    enum Factor:
        /** (0, 0, 0, 0) */
        case Zero

        /** (1, 1, 1, 1) */
        case One

        /** (src.r, src.g, src.b, src.a) */
        case SrcColor

        /** (1, 1, 1, 1) - (src.r, src.g, src.b, src.a) */
        case OneMinusSrcColor

        /** (dst.r, dst.g, dst.b, dst.a) */
        case DstColor

        /** (1, 1, 1, 1) - (dst.r, dst.g, dst.b, dst.a) */
        case OneMinusDstColor

        /** (src.a, src.a, src.a, src.a) */
        case SrcAlpha

        /** (1, 1, 1, 1) - (src.a, src.a, src.a, src.a) */
        case OneMinusSrcAlpha

        /** (dst.a, dst.a, dst.a, dst.a) */
        case DstAlpha

        /** (1, 1, 1, 1) - (dst.a, dst.a, dst.a, dst.a) */
        case OneMinusDstAlpha

    /** Enumeration of blending equations.
      *
      * <!-- TODO: OpenGL equivalents -->
      */
    enum Equation:
        /** Pixel = Src * SrcFactor + Dst * DstFactor. */
        case Add

        /** Pixel = Src * SrcFactor - Dst * DstFactor. */
        case Subtract

        /** Pixel = Dst * DstFactor - Src * SrcFactor. */
        case ReverseSubtract

    /** Default constructor.
      *
      * Constructs a blending mode that does alpha blending.
      */
    def apply(): BlendMode =
        BlendMode(Factor.SrcAlpha, Factor.OneMinusSrcAlpha, Equation.Add, Factor.One, Factor.OneMinusSrcAlpha, Equation.Add)

    /** Construct the blend mode given the factors and equation.
      *
      * This constructor uses the same factors for both the color and alpha components. It also defaults to the Add equation.
      *
      * @param sourceFactor
      *   Specifies how to compute the source factor for the color and alpha channels.
      *
      * @param destinationFactor
      *   Specifies how to compute the destination factor for the color and alpha channels.
      */
    def apply(sourceFactor: Factor, destinationFactor: Factor): BlendMode =
        BlendMode(sourceFactor, destinationFactor, Equation.Add, sourceFactor, destinationFactor, Equation.Add)

    /** Construct the blend mode given the factors and equation.
      *
      * This constructor uses the same factors for both the color and alpha components. It also defaults to the Add equation.
      *
      * @param sourceFactor
      *   Specifies how to compute the source factor for the color and alpha channels.
      *
      * @param destinationFactor
      *   Specifies how to compute the destination factor for the color and alpha channels.
      *
      * @param blendEquation
      *   Specifies how to combine the source and destination colors and alpha.
      */
    def apply(sourceFactor: Factor, destinationFactor: Factor, blendEquation: Equation): BlendMode =
        BlendMode(sourceFactor, destinationFactor, blendEquation, sourceFactor, destinationFactor, blendEquation)

    def Alpha(): BlendMode =
        BlendMode(Factor.SrcAlpha, Factor.OneMinusSrcAlpha, Equation.Add, Factor.One, Factor.OneMinusSrcAlpha, Equation.Add)

    def Add(): BlendMode =
        BlendMode(Factor.SrcAlpha, Factor.One, Equation.Add, Factor.One, Factor.One, Equation.Add)

    def Multiply(): BlendMode =
        BlendMode(Factor.DstColor, Factor.Zero)

    def None(): BlendMode =
        BlendMode(Factor.One, Factor.Zero)

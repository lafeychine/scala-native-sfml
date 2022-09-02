package sfml
package window

import scalanative.unsafe.*
import scalanative.unsigned.UnsignedRichInt

import internal.Type.booleanToSfBool
import internal.window.ContextSettings.*

class ContextSettings(
    val depth: Int = 0,
    val stencil: Int = 0,
    val antialiasing: Int = 0,
    val major: Int = 1,
    val minor: Int = 1,
    val attributeFlags: Int = 0,
    val sRgbCapable: Boolean = false
) extends StackResource[Ptr[sfContextSettings]]:

    private[sfml] def bind(implicit z: Zone) =
        val contextSettings = alloc[sfContextSettings]()

        contextSettings._1 = depth.toUInt
        contextSettings._2 = stencil.toUInt
        contextSettings._3 = antialiasing.toUInt
        contextSettings._4 = major.toUInt
        contextSettings._5 = minor.toUInt
        contextSettings._6 = attributeFlags.toUInt
        contextSettings._7 = sRgbCapable
        return contextSettings

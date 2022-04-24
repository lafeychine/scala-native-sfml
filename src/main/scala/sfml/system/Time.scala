package sfml
package system

import scalanative.unsafe.*

import internal.system.Time.*

case class Time(var amount: Long) extends SFMLBind[sfTime]:

    private[sfml] def bind(implicit z: Zone) =
        val time = alloc[sfTime]()

        time._1 = amount
        return time

object Time:
    def Zero(): Time = Time(0)

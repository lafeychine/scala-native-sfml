package sfml
package system

import scalanative.unsafe.*
import scala.util.Using

import internal.system.String.*

import stdlib.Locale

type ScalaString = java.lang.String

class String private[sfml] (private[sfml] val string: Ptr[sfString]) extends Resource:

    def this(ansiString: ScalaString) =
        this(Resource[sfString] { (r: Ptr[sfString]) =>
            Zone { implicit z =>
                Using.resource(Locale()) { locale =>
                    ctor(r, toCString(ansiString), locale.locale);
                }
            };
        })

    final override def close(): Unit =
        dtor(!(string.asInstanceOf[Ptr[Ptr[Byte]]]))
        Resource.close(string)
